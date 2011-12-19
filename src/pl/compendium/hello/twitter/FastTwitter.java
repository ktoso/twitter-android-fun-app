package pl.compendium.hello.twitter;

import pl.compendium.hello.db.dao.TweetsRepository;
import pl.compendium.hello.twitter.model.Tweet;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Singleton
public class FastTwitter implements Twitter {
    
    List<Tweet> tweets = newArrayList();

    @Inject
    TweetsRepository tweetsRepository;

    @Override
    public List<Tweet> publicTimeline() {
        return tweets;
    }

    @Override
    public void post(Tweet tweet) {
        tweets.add(tweet);

        tweetsRepository.save(tweet);

        sortTweets();
    }

    private void sortTweets() {
        Collections.sort(tweets, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet tweet, Tweet tweet1) {
                return tweet.date.compareTo(tweet1.date);
            }
        });
    }
}
