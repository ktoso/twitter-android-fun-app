package pl.compendium.hello.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import pl.compendium.hello.activity.MainActivity;
import pl.compendium.hello.guice.annotation.Author;
import pl.compendium.hello.guice.annotation.Slow;
import pl.compendium.hello.twitter.SlowTwitter;
import pl.compendium.hello.twitter.Twitter;
import roboguice.inject.SharedPreferencesName;

public class TwitterModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Twitter.class).annotatedWith(Slow.class).to(SlowTwitter.class);

        bindConstant().annotatedWith(Names.named("AUTHOR")).to("ktoso");
        bindConstant().annotatedWith(Author.class).to("ktoso");

        requestStaticInjection(MainActivity.class); // wow!

        // need to bind the name explicitly
        bindConstant().annotatedWith(SharedPreferencesName.class).to("pl.compendium.hello");
    }

}
