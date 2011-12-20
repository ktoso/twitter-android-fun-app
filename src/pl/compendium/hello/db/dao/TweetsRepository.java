package pl.compendium.hello.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.inject.Inject;
import pl.compendium.hello.db.DBConstants;
import pl.compendium.hello.db.DBUtil;
import pl.compendium.hello.twitter.model.Tweet;

import java.io.Closeable;
import java.io.IOException;

public class TweetsRepository implements Closeable {

    DBUtil dbUtil;
    SQLiteDatabase db;

    @Inject
    public TweetsRepository(DBUtil dbUtil) {
        this.dbUtil = dbUtil;

        db = dbUtil.getWritableDatabase();
    }

    public synchronized Cursor findAll() {
        return db.query(DBConstants.TWEETS_TABLE_NAME,
//                new String[]{
//                        DBConstants.ID_COLUMN_NAME,
//                        DBConstants.MSG_COLUMN_NAME,
//                        DBConstants.AUTHOR_COLUMN_NAME
//                }
                        null // oznacza "wszystkie"
                ,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public synchronized void save(Tweet tweet) {
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues(2);
            values.put(DBConstants.AUTHOR_COLUMN_NAME, tweet.author);
            values.put(DBConstants.MSG_COLUMN_NAME, tweet.message);

//          values.putNull(key);

            db.insert(DBConstants.TWEETS_TABLE_NAME,
                      null, // "null column hack"
                      values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public synchronized void delete(Tweet tweet) {
        db.beginTransaction();

        try {
            db.delete(DBConstants.TWEETS_TABLE_NAME,
                      DBConstants.ID_COLUMN_NAME + " = ?",
                      new String[] { String.valueOf(tweet._id) }
            );

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void close() throws IOException {
           db.close();
    }
}
