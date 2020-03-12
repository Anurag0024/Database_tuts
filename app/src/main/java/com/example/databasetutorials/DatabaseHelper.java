package com.example.databasetutorials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Student.db";
    public static final String TABLE_NAME ="Student_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "MARKS";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,ID INTEGER,USERNAME TEXT,PASSWORD INTEGER,MARKS INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }



    public boolean insertDATA(String id, String username, String password, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        // to check whether data id entered or notin database
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

///this function is for read data for database baali upar ka same aayega//

    public Cursor getALLData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select*from "+TABLE_NAME,null);
        return res;
    }

    // this function is for the updation of the data//
    public boolean updateData(String id,String username,String password,String marks)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,marks);
        int result = db.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        if (result>0)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"ID=?",new String[]{id});
        return i;
    }

}
