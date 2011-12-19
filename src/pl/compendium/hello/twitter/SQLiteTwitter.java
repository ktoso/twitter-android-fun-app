package pl.compendium.hello.twitter;

import android.database.Cursor;
import com.google.inject.Inject;
import pl.compendium.hello.db.dao.TweetsRepository;
import pl.compendium.hello.twitter.model.Tweet;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTwitter implements Twitter, Closeable {

    private static final String TAG = SQLiteTwitter.class.getSimpleName();
    
    TweetsRepository tweetsRepository;

    @Inject
    public SQLiteTwitter(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    public List<Tweet> publicTimeline() {
        Cursor all = publicTimelineCursor();

        int tweetCount = all.getCount();

        List<Tweet> tweets = new ArrayList<Tweet>(tweetCount);
        do {
            int id = all.getInt(0);
            String msg = all.getString(1);
            String username = all.getString(2);

            tweets.add(new Tweet(id, msg, username));
        } while (all.moveToNext());

        return tweets;
    }

    @Override
    public void post(Tweet tweet) {
        tweetsRepository.save(tweet);
    }

    public void remove(Tweet tweet) {
        tweetsRepository.delete(tweet);
    }

    public Cursor publicTimelineCursor() {
        Cursor all = tweetsRepository.findAll();
        return all;
    }

    @Override
    public void close() throws IOException {
        tweetsRepository.close();
    }
}
