package pl.compendium.hello.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static pl.compendium.hello.db.DBConstants.*;

public class DBUtil extends SQLiteOpenHelper {

    private static final String TAG = DBUtil.class.getSimpleName();

    public DBUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "Creating database (version: " + DB_VERSION + ")...");

        sqLiteDatabase.execSQL("create table " + TWEETS_TABLE_NAME + " (" +
                ID_COLUMN_NAME + " integer primary key" +
                ", " + MSG_COLUMN_NAME + " text" +
                ", " + AUTHOR_COLUMN_NAME + " text" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // TODO Add migrations here

        Log.i(TAG, "Upgrading database from version [" + oldVersion + "], to [" + newVersion + "]...");

        sqLiteDatabase.execSQL("drop table if exists " + TWEETS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
