package pl.compendium.hello.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;

public class MainActivity extends RoboActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

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
        tweetsAdapter = new SimpleCursorAdapter(this,
                                                R.layout.tweet,
                                                twitter.publicTimelineCursor(),
                                                new String[]{
                                                        DBConstants.AUTHOR_COLUMN_NAME,
                                                        DBConstants.MSG_COLUMN_NAME
                                                },
                                                new int[]{
                                                        R.id.tweet_author,
                                                        R.id.tweet_message
                                                });
        tweets.setAdapter(tweetsAdapter);
        tweets.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                final Tweet removeMe = new Tweet(cursor.getInt(0), null, null);

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Czy chcesz usunąć ten tweet?")
                        .setPositiveButton("Tak!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                twitter.remove(removeMe);
                                reloadTweets();
                            }
                        })
                        .setNegativeButton("Nie, lepiej nie...", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .create()
                        .show();

                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        // prepare
        String message = msg.getText().toString();
        Tweet tweet = new Tweet(message, AUTHOR);

        // post
        Ln.i("Creating tweet: %s", tweet);
        twitter.post(tweet);

        // refresh
        reloadTweets();

        // clear inputs and notify
        msg.setText("");
        Toast.makeText(this, "You've tweeted!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        try {
            tweetsAdapter.getCursor().close();
            twitter.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception while closing twitter", e);
        }
    }

    private void reloadTweets() {
        tweetsAdapter.getCursor().requery();
        tweetsAdapter.notifyDataSetChanged();
    }

}
