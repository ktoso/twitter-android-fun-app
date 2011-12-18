package pl.compendium.hello.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.google.inject.Inject;
import pl.compendium.hello.R;
import pl.compendium.hello.guice.annotation.Author;
import pl.compendium.hello.guice.annotation.Slow;
import pl.compendium.hello.twitter.Twitter;
import pl.compendium.hello.twitter.model.Tweet;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.Ln;

import java.util.List;

public class MainActivity extends RoboActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    @Author
    String author;
    
    @Inject
    SharedPreferences sharedPreferences; 

    @Inject
    @Slow
    Twitter twitter;

    @InjectView(R.id.msg)
    EditText msg;

    @InjectView(R.id.tweets)
    ListView tweets;

    @InjectView(R.id.send)
    Button send;

    @InjectView(R.id.open_references)
    Button openPreferences;

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

        tweet("Hello World");
        tweet("Hello Warszawa");
        tweet("Hello Poznan");
        tweet("Hello Wroclaw");

        visibleTweets = twitter.publicTimeline();

        for (Tweet tweet : visibleTweets) {
            Ln.i(tweet.toString());
        }

        openPreferences.setOnClickListener(new OpenPrefsActivityOnClick());

        send.setOnClickListener(this);

        //noinspection unchecked
        tweetsAdapter = new TweetAdapter();
        tweets.setAdapter(tweetsAdapter);
        tweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, view.toString() + " was clicked!");
            }
        });
    }

    @Override
    public void onClick(View view) {
        String message = this.msg.getText().toString();

        Ln.i("Creating post: %s", message); // todo chcemy progress bar
        tweet(message);

        visibleTweets = twitter.publicTimeline();
//        tweetsAdapter.notifyDataSetChanged(); // todo this is helpful :-)
    }

    private void tweet(String tweet) {
        author = sharedPreferences.getString(getString(R.string.pk_username), "");
        
        twitter.post(new Tweet(tweet, author));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_show_preferences: {
                new OpenPrefsActivityOnClick().onClick(null);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    class OpenPrefsActivityOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PrefsActivity.class);
            startActivity(intent);
        }
    }

}
