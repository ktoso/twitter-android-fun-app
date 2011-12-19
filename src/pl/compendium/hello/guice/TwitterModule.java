package pl.compendium.hello.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import pl.compendium.hello.db.DBUtil;
import pl.compendium.hello.guice.annotation.Author;
import pl.compendium.hello.guice.annotation.SQLite;
import pl.compendium.hello.guice.annotation.Slow;
import pl.compendium.hello.guice.provider.DBUtilProvider;
import pl.compendium.hello.twitter.SQLiteTwitter;
import pl.compendium.hello.twitter.SlowTwitter;
import pl.compendium.hello.twitter.Twitter;
import roboguice.inject.SharedPreferencesName;

public class TwitterModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(DBUtil.class).toProvider(DBUtilProvider.class);

        bind(Twitter.class)
                .annotatedWith(Slow.class)
                .to(SlowTwitter.class);

        bind(Twitter.class)
                .annotatedWith(SQLite.class)
                .to(SQLiteTwitter.class);

        bindConstant().annotatedWith(Names.named("AUTHOR")).to("ktoso");
        bindConstant().annotatedWith(Author.class).to("ktoso");



        // need to bind the name explicitly
        bindConstant().annotatedWith(SharedPreferencesName.class).to("pl.compendium.hello");
    }

}
