package pl.compendium.hello.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.inject.Inject;
import pl.compendium.hello.db.DBConstants;
import pl.compendium.hello.db.DBUtil;
import pl.compendium.hello.twitter.model.Tweet;

public class TweetsRepository {

    DBUtil dbUtil;
    SQLiteDatabase db;

    @Inject
    public TweetsRepository(DBUtil dbUtil) {
        this.dbUtil = dbUtil;

        db = dbUtil.getWritableDatabase();
    }

    public Cursor findAll() {
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
    
    public void save(Tweet tweet) {
        db.beginTransaction();

        ContentValues values = new ContentValues(2);
        values.put(DBConstants.AUTHOR_COLUMN_NAME, tweet.author);
        values.put(DBConstants.MSG_COLUMN_NAME, tweet.message);

//        values.putNull(key);

        db.insert(DBConstants.TWEETS_TABLE_NAME,
                null, // "null column hack"
                values);
        
        db.endTransaction();
    }
}
