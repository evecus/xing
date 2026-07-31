package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class PrefOperate {
    public static String getAppKey(Context context) {
        return CooperService.instance().getAppKey(context);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:23:0x0086
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static void loadMetaDataConfig(android.content.Context r6) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.PrefOperate.loadMetaDataConfig(android.content.Context):void");
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        if (str == null || str.equals("")) {
            bv.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
        if (z && str != null && !str.equals("")) {
            cj.a().d(context, str);
            cj.a().b(context, true);
        }
        if (z) {
            return;
        }
        cj.a().d(context, "");
        cj.a().b(context, false);
    }

    public static void setAppChannel(String str) {
        if (str == null || str.equals("")) {
            bv.c().c("[WARNING] The channel you have set is empty");
        }
        CooperService.instance().getHeadObject().l = str;
    }

    public static void setAppKey(String str) {
        CooperService.instance().getHeadObject().e = str;
    }
}
