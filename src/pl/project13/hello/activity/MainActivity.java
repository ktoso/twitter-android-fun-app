package pl.project13.hello.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.inject.Inject;
import pl.project13.hello.R;
import pl.project13.hello.guice.annotation.Author;
import pl.project13.hello.guice.annotation.Slow;
import pl.project13.hello.twitter.Twitter;
import pl.project13.hello.twitter.model.Tweet;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.Ln;

import java.util.List;

public class MainActivity extends RoboActivity implements View.OnClickListener {

    @Inject
    @Author
    String AUTHOR;

    @Inject
    @Slow
    Twitter twitter;

    @InjectView(R.id.msg)
    EditText msg;

    @InjectView(R.id.tweets)
    ListView tweets;

    @InjectView(R.id.send)
    Button send;

    @InjectView(R.id.hello)
    TextView hello;

    @Inject
    LayoutInflater inflater;

    ArrayAdapter tweetsAdapter;
    List<Tweet> visibleTweets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        twitter.post(new Tweet("Hello World", AUTHOR));
        twitter.post(new Tweet("Hello Warszawa", AUTHOR));
        twitter.post(new Tweet("Hello Poznan", AUTHOR));
        twitter.post(new Tweet("Hello Wroclaw", AUTHOR));

        visibleTweets = twitter.publicTimeline();

        for (Tweet tweet : visibleTweets) {
            Ln.i(tweet.toString());
        }

        send.setOnClickListener(this);

        //noinspection unchecked
        tweetsAdapter = new TweetAdapter();
        tweets.setAdapter(tweetsAdapter);
    }

    @Override
    public void onClick(View view) {
        Tweet tweet = new Tweet(msg.getText().toString(), AUTHOR);

        Ln.i("Creating post: %s", tweet);
        twitter.post(tweet);

        visibleTweets = twitter.publicTimeline();
//        tweetsAdapter.notifyDataSetChanged(); // todo this is needed :-)
    }

    private class TweetAdapter extends ArrayAdapter<Tweet> {
        public TweetAdapter() {
            super(MainActivity.this, R.layout.tweet, R.id.tweet_message, MainActivity.this.visibleTweets);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row;

            if (null == convertView) {
                row = inflater.inflate(R.layout.tweet, null);
            } else {
                row = convertView;
            }

            TextView tv = (TextView) row.findViewById(R.id.tweet_message);

            Tweet theTweet = getItem(position);
            tv.setText(theTweet.toString());

            return row;
        }
    }
}
