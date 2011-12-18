package pl.project13.hello.twitter;

import com.google.inject.ImplementedBy;
import pl.project13.hello.twitter.model.Tweet;

import java.util.List;

@ImplementedBy(FastTwitter.class)
public interface Twitter {

    List<Tweet> publicTimeline();

    void post(Tweet tweet);

}
