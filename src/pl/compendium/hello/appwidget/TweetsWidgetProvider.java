package pl.compendium.hello.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class TweetsWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            populateView(context, appWidgetManager, appWidgetId);
        }
    }

    private void populateView(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

    }
}
