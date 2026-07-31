package com.roamexplore.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.roamexplore.R;

/* JADX INFO: loaded from: classes.dex */
public class AppWidgetProvider extends android.appwidget.AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i : iArr) {
            Intent intent = new Intent(context, (Class<?>) MainActivity.class);
            intent.setFlags(335544320);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget_layout);
            remoteViews.setOnClickPendingIntent(R.mipmap.ic_launcher, activity);
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }
}
