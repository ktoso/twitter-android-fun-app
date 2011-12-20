package pl.compendium.hello.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import pl.compendium.hello.R;
import pl.compendium.hello.activity.MainActivity;
import pl.compendium.hello.intent.MyIntents;

public class TweetCountAppWidgetProvider extends AppWidgetProvider {

    private int tweetNumber = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // Provider obsluguje WIELE (N) widżetów!
        final int N = appWidgetIds.length;

        // aktualizujemy każdy
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            populateView(context, appWidgetManager, appWidgetId);
        }
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(MyIntents.TWEET_COUNT_ACTION)) {

            Bundle extras = intent.getExtras();
            this.tweetNumber = extras.getInt("count", 0);

            // force update !
            AppWidgetManager mngr = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = mngr.getAppWidgetIds(intent.getComponent());

            onUpdate(context, mngr, appWidgetIds);
        }
    }


    private void populateView(Context context, AppWidgetManager appWidgetManager,
                              int appWidgetId) {
        // Przygotowujemy intent do odpalenia "on click"
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // rejestrujemy onClickListener'a troszke inaczej:
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setOnClickPendingIntent(R.id.container, pendingIntent);
        views.setTextViewText(R.id.tweets_num_text_view, String.valueOf(tweetNumber));

        // aktualizujemy widok widzetu (prosimy menagera o to)
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}