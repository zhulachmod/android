package net.zhulachmad.tugasapi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final int DATABASE_VERSION = 1;

     /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAMA + " TEXT, "+ KEY_EMAIL + " TEXT,"+ KEY_PASSWORD +" );";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_USERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long addUserDetail(String nama, String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);

        // insert row in students table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                userModel.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                userModel.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
                // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public int updateUser(int id, String nama, String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        // update row in students table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }



    public Boolean UserLogin(String email, String password) {
        String [] columns ={ KEY_ID };

        SQLiteDatabase db=  this.getReadableDatabase();
        String selection = KEY_EMAIL+ " = ?" + "AND" + KEY_PASSWORD + " = ?";
        String[] selectionArgs ={email, password};

        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs,
                null,null,null);

        int cursorcount =cursor.getCount();

        cursor.close();
        db.close();
        if(cursorcount > 0){
            return  true;
        }
        return  false;

    }


    public String caripass(String email,String password){

        SQLiteDatabase db =this.getReadableDatabase();
        String query ="SELECT "+KEY_EMAIL+ ","+KEY_PASSWORD+" FROM "+TABLE_USER;
        Cursor cursors = db.rawQuery(query,null);

        String a,b;
        b="Akun tidak ditemukan";
        if(cursors.moveToFirst()){
            do{
                a=cursors.getString(0);
                if(a.equals(email) && (a.equals(password))){
                    b=cursors.getString(0);
                    break;
                }
            }
            while (cursors.moveToNext());
        }
        return b;
    }

}