package pl.compendium.hello.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import pl.compendium.hello.db.DBUtil;
import pl.compendium.hello.guice.CompendiumApplication;

public class DBUtilProvider implements Provider<DBUtil> {

    @Inject
    CompendiumApplication application;

    @Override
    public DBUtil get() {
        return new DBUtil(application);
    }
}
