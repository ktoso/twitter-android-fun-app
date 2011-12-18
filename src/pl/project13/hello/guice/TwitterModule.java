package pl.project13.hello.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import pl.project13.hello.activity.MainActivity;
import pl.project13.hello.guice.annotation.Author;
import pl.project13.hello.guice.annotation.Slow;
import pl.project13.hello.twitter.SlowTwitter;
import pl.project13.hello.twitter.Twitter;

public class TwitterModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Twitter.class).annotatedWith(Slow.class).to(SlowTwitter.class);

        bindConstant().annotatedWith(Names.named("AUTHOR")).to("ktoso");
        bindConstant().annotatedWith(Author.class).to("ktoso");

        requestStaticInjection(MainActivity.class); // wow!
    }

}
