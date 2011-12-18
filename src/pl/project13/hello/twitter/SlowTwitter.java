package pl.project13.hello.twitter;

import android.util.Log;
import pl.project13.hello.twitter.model.Tweet;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Singleton
public class SlowTwitter extends FastTwitter implements Twitter {

    private static final String TAG = SlowTwitter.class.getSimpleName();

    private String user;

    public SlowTwitter() {
    }

    public SlowTwitter(String username) {
        user = username;
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
