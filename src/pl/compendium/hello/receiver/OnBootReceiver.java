package pl.compendium.hello.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import pl.compendium.hello.service.TweetsCountService;

public class OnBootReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, TweetsCountService.class));
    }
}
