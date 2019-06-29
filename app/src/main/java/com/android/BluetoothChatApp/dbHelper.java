package com.android.BluetoothChatApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.BluetoothChatApp.HistoryDataContainer.*;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bChatApp.db";
    private static final int DATABASE_VERSION = 1;
    Context mContext;
    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE  IF NOT EXISTS " + HistoryFields.TABLE_NAME + " (" +
                HistoryFields._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HistoryFields.COLUMN_DATE + " INTEGER NOT NULL, " +
                HistoryFields.COLUMN_MESSAGE + " TEXT NOT NULL," +
                HistoryFields.COLUMN_DEVICE + " TEXT NOT NULL); " ;

        db.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryFields.TABLE_NAME);
        onCreate(db);
    }
    public  void InsertData(HistoryData data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HistoryFields.COLUMN_DATE,data.getDate());
        values.put(HistoryFields.COLUMN_MESSAGE,data.getMessage());
        values.put(HistoryFields.COLUMN_DEVICE,data.getDevice());
        db.insert(HistoryFields.TABLE_NAME, null, values);
    }
    public List<HistoryData> getHistoryData(Context context) {
        List<HistoryData> list= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelectAll = "SELECT * FROM " + HistoryFields.TABLE_NAME;
        Cursor res = db.rawQuery(sqlSelectAll, null);

        if (res.getCount() > 0) {
            Log.d("getalllocal", "res.getcount:  " + res.getCount());
        }
        if(res!=null)
        {
            if(res.moveToFirst())
            {
                do{
                    HistoryData data=CursorToHistoryData(res);
                    list.add(data);
                }while(res.moveToNext());
            }
        }
        res.close();
        return  list;
    }

    public HistoryData CursorToHistoryData(Cursor cursor)
    {
        HistoryData data=new HistoryData();
        data.setDate(cursor.getLong(cursor.getColumnIndex(HistoryFields.COLUMN_DATE)));
       data.setDevice(cursor.getString(cursor.getColumnIndex(HistoryFields.COLUMN_DEVICE)));
        data.setMessage(cursor.getString(cursor.getColumnIndex(HistoryFields.COLUMN_MESSAGE)));
       return data;
    }
}
