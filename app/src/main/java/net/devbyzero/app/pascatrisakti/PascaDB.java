package net.devbyzero.app.pascatrisakti;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import net.devbyzero.app.pascatrisakti.data.News;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.CallLog;

import java.util.ArrayList;

/**
 * Created by anta40 on 16-May-17.
 */

public class PascaDB extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "pasca.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NEWS = "tbl_news";
    private static final String TABLE_ABOUT = "tbl_about";
    private static final String NEWS_ID = "news_id";
    private static final String NEWS_TITLE = "news_title";
    private static final String NEWS_CONTENT = "news_content";
    private static final String CONTENT_VALUE = "content_value";
    private static final String CONTENT_NAME = "content_name";

    public PascaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<News> getNews(){
        ArrayList<News> result = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {NEWS_ID, NEWS_TITLE, NEWS_CONTENT};
        Cursor cursor = db.query(TABLE_NEWS, columns, null, null, null, null, null);

        while (cursor.moveToNext()){
            News news = new News();
            news.setTitle(cursor.getString(cursor.getColumnIndex(NEWS_TITLE)));
         //   news.setContent(cursor.getString(cursor.getColumnIndex(NEWS_CONTENT)));
            result.add(news);
        }
        return result;
    }

    public String getContentInfo(String content_name){
        String value = "";
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {CONTENT_NAME, CONTENT_VALUE};
        String whereClause = "content_name = ?";
        String[] whereArgs = new String[] {content_name};
        Cursor cursor = db.query(TABLE_ABOUT, columns, whereClause, whereArgs, null, null, null);

        while (cursor.moveToNext()) {
            value = cursor.getString(cursor.getColumnIndex(CONTENT_VALUE));
        }
        return value;
    }

}
