/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").  
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
 */

package org.opensolaris.opengrok.analysis.dtrace;
import org.opensolaris.opengrok.analysis.JFlexXref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.opensolaris.opengrok.web.Util;

%%
%public
%class DTraceXref
%extends JFlexXref
%unicode
%ignorecase
%int
%{
    /* Must match WhiteSpace regex */
    private final static String WHITE_SPACE = " [ \t\f\r]+";

    @Override
    protected int getLineNumber() { return yyline; }

    @Override
    protected void setLineNumber(int x) { yyline = x; }
%}

WhiteSpace = [ \t\f]+
EOL = \r|\n|\r\n
Identifier = [a-zA-Z_] [a-zA-Z0-9_]+

Number = ([0-9]+ | 0[xX][0-9a-fA-F]+)

URIChar = [\?\+\%\&\:\/\.\@\_\;\=\$\,\-\!\~\*\\]
FNameChar = [a-zA-Z0-9_\-\.]
File = [a-zA-Z]{FNameChar}* "." ([dh]|"hpp")
Path = "/"? [a-zA-Z]{FNameChar}* ("/" [a-zA-Z]{FNameChar}*[a-zA-Z0-9])+

%state  STRING COMMENT

%%

<YYINITIAL> {
{Identifier} {
    String id = yytext();
    writeSymbol(id, Consts.kwd, yyline);
}

"#" {WhiteSpace}* "include" {WhiteSpace}* "<" ({File}|{Path}|{Identifier}) ">" {
    Matcher match = Pattern.compile("(#.*)(include)(.*)<(.*)>").matcher(yytext());
    if (match.matches()) {
        out.write(match.group(1));
        writeSymbol(match.group(2), Consts.kwd, yyline);
        out.write(match.group(3));
        out.write("&lt;");
        String path = match.group(4);
        out.write(Util.breadcrumbPath(urlPrefix + "path=", path));
        out.write("&gt;");
    }
}

{Number}    { out.write("<span class=\"n\">"); out.write(yytext()); out.write("</span>"); }

\\\"    { out.write(yytext()); }
\"      { out.write("<span class=\"s\">\""); yypush(STRING, "</span>"); }
"/*"    { out.write("<span class=\"c\">/*"); yypush(COMMENT, "</span>"); }
}

<STRING> {
\" {WhiteSpace} \"  { out.write(yytext()); }
\"      { out.write(yytext()); yypop(); }
}

<COMMENT> {
"*/"    { out.write(yytext()); yypop(); }
}

<YYINITIAL, STRING, COMMENT> {
"&"             { out.write("&amp;"); }
"<"             { out.write("&lt;"); }
">"             { out.write("&gt;"); }
{EOL}           { startNewLine(); }
{WhiteSpace}    { out.write(yytext()); }
[!-~]           { out.write(yytext()); }
[^\n]           { writeUnicodeChar(yycharat(0)); }
}

<STRING, COMMENT> {
{Path} { out.write(Util.breadcrumbPath(urlPrefix + "path=", yytext(), '/')); }

{File} {
    String path = yytext();
    out.write("<a href=\"" + urlPrefix + "path=");
    out.write(path);
    appendProject();
    out.write("\">");
    out.write(path);
    out.write("</a>");
}

("http" | "https" | "ftp") "://" ({FNameChar}|{URIChar})+[a-zA-Z0-9/] {
    appendLink(yytext());
}

{FNameChar}+ "@" {FNameChar}+ "." {FNameChar}+ {
    writeEMailAddress(yytext());
}
}