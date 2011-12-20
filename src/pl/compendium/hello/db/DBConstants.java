package pl.compendium.hello.db;

import android.provider.BaseColumns;

public interface DBConstants {
    String DB_NAME = "sessions.db";
    int DB_VERSION = 1;

    String TWEETS_TABLE_NAME = "tweets";

    String ID_COLUMN_NAME = BaseColumns._ID;
    String MSG_COLUMN_NAME = "message";
    String AUTHOR_COLUMN_NAME = "author";
}
