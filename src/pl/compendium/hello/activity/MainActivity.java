package pl.compendium.hello.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import pl.compendium.hello.R;
import pl.compendium.hello.db.DBConstants;
import pl.compendium.hello.guice.annotation.Author;
import pl.compendium.hello.twitter.SQLiteTwitter;
import pl.compendium.hello.twitter.model.Tweet;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.Ln;

public class MainActivity extends RoboActivity implements View.OnClickListener {

    @Inject
    @Author
    String AUTHOR;

    @Inject
    SQLiteTwitter twitter;

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

    private SimpleCursorAdapter tweetsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        send.setOnClickListener(this);

        // load tweets from database
        Cursor allTweets = twitter.publicTimelineCursor();

        tweetsAdapter = new SimpleCursorAdapter(this,
                                                R.layout.tweet,
                                                allTweets,
                                                new String[]{
                                                        DBConstants.AUTHOR_COLUMN_NAME,
                                                        DBConstants.MSG_COLUMN_NAME
                                                },
                                                new int[]{
                                                        R.id.tweet_author,
                                                        R.id.tweet_message
                                                });
        tweets.setAdapter(tweetsAdapter);
    }

    @Override
    public void onClick(View view) {
        Tweet tweet = new Tweet(msg.getText().toString(), AUTHOR);

        Ln.i("Creating tweet: %s", tweet);
        twitter.post(tweet);

        tweetsAdapter.notifyDataSetChanged();
    }

}
