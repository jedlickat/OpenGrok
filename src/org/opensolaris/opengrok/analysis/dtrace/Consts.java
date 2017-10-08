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

import java.util.HashSet;
import java.util.Set;

public class Consts {

    public static final Set<String> kwd = new HashSet<String>();
    
    static {
        /* variable classes */
        kwd.add("self");
        kwd.add("this");
        
        /* actions */
        kwd.add("trace");
        kwd.add("tracemem");
        kwd.add("printf");
        kwd.add("printa");
        kwd.add("stack");
        kwd.add("ustack");
        kwd.add("jstack");
        kwd.add("clear");
        kwd.add("denormalize");
        kwd.add("normalize");
        kwd.add("trunc");
        kwd.add("mod");
        kwd.add("umod");
        kwd.add("func");
        kwd.add("ufunc");
        kwd.add("freopen");
        kwd.add("ftruncate");
        kwd.add("setopt");
        kwd.add("uaddr");
        kwd.add("sym");
        kwd.add("usym");
        
        /* destructive actions */
        kwd.add("stop");
        kwd.add("raise");
        kwd.add("copyout");
        kwd.add("copyoutstr");
        kwd.add("system");
        kwd.add("breakpoint");
        kwd.add("panic");
        kwd.add("chill");
        
        /* special actions */
        kwd.add("exit");
        kwd.add("speculation");
        kwd.add("speculate");
        kwd.add("commit");
        kwd.add("discard");
        
        /* subroutines */
        kwd.add("alloca");
        kwd.add("basename");
        kwd.add("bcopy");
        kwd.add("cleanpath");
        kwd.add("copyin");
        kwd.add("copyinstr");
        kwd.add("copyinto");
        kwd.add("dirname");
        kwd.add("inet_ntoa");
        kwd.add("inet_ntoa6");
        kwd.add("inet_ntop");
        kwd.add("msgdsize");
        kwd.add("msgsize");
        kwd.add("mutex_owned");
        kwd.add("mutex_owner");
        kwd.add("mutex_type_adaptive");
        kwd.add("progenyof");
        kwd.add("rand");
        kwd.add("rw_iswriter");
        kwd.add("rw_write_held");
        kwd.add("strjoin");
        kwd.add("strlen");
        kwd.add("strstr");
        
        /* aggregations */
        kwd.add("count");
        kwd.add("sum");
        kwd.add("avg");
        kwd.add("min");
        kwd.add("max");
        kwd.add("stddev");
        kwd.add("lquantize");
        kwd.add("llquantize");
        kwd.add("quantize");
        
        /* built-in variables */
        kwd.add("arg0");
        kwd.add("arg1");
        kwd.add("arg2");
        kwd.add("arg3");
        kwd.add("arg4");
        kwd.add("arg5");
        kwd.add("arg6");
        kwd.add("arg7");
        kwd.add("arg8");
        kwd.add("arg9");
        kwd.add("args");
        kwd.add("caller");
        kwd.add("ucaller");
        kwd.add("chip");
        kwd.add("cpu");
        kwd.add("curcpu");
        kwd.add("curlwpsinfo");
        kwd.add("curpsinfo");
        kwd.add("curthread");
        kwd.add("cwd");
        kwd.add("epid");
        kwd.add("errno");
        kwd.add("execname");
        kwd.add("gid");
        kwd.add("id");
        kwd.add("ipl");
        kwd.add("lgrp");
        kwd.add("pid");
        kwd.add("ppid");
        kwd.add("probefunc");
        kwd.add("probemod");
        kwd.add("probename");
        kwd.add("probeprov");
        kwd.add("pset");
        kwd.add("root");
        kwd.add("kthreadname");
        kwd.add("uthreadname");
        kwd.add("stackdepth");
        kwd.add("tid");
        kwd.add("timestamp");
        kwd.add("uid");
        kwd.add("uregs");
        kwd.add("vtimestamp");
        kwd.add("walltimestmap");
        kwd.add("zonename");
        kwd.add("zoneid");
    }
}
