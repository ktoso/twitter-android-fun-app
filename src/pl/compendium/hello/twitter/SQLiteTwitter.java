package pl.compendium.hello.twitter;

import android.database.Cursor;
import android.util.Log;
import pl.compendium.hello.db.dao.TweetsRepository;
import pl.compendium.hello.twitter.model.Tweet;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTwitter implements Twitter {

    private static final String TAG = SQLiteTwitter.class.getSimpleName();
    
    @Inject
    TweetsRepository tweetsRepository;
    
    @Inject
    public void init(){
        Log.i(TAG, "Creating SQLiteTwitter instance...");
    }

    @Override
    public List<Tweet> publicTimeline() {
        Cursor all = publicTimelineCursor();

        int tweetCount = all.getCount();

        List<Tweet> tweets = new ArrayList<Tweet>(tweetCount);
        do {
            String msg = all.getString(1);
            String username = all.getString(2);

            tweets.add(new Tweet(msg, username));
        } while (all.moveToNext());

        return tweets;
    }

    @Override
    public void post(Tweet tweet) {
        tweetsRepository.save(tweet);
    }

    public Cursor publicTimelineCursor() {
        Cursor all = tweetsRepository.findAll();
        return all;
    }
}
