package pl.compendium.hello.twitter;

import android.util.Log;
import pl.compendium.hello.twitter.model.Tweet;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SlowTwitter extends FastTwitter implements Twitter {

    private static final String TAG = SlowTwitter.class.getSimpleName();

    public SlowTwitter() {
    }

    @Override
    public List<Tweet> publicTimeline() {
        beSlow();

        return super.publicTimeline();
    }

    @Override
    public void post(Tweet tweet) {
        beSlow();

        super.post(tweet);
    }

    private void beSlow() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            Log.e(TAG, "Exception while acting slow...", e);
        }
    }
}
