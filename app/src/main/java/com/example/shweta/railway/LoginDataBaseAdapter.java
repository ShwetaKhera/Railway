package com.example.shweta.railway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Objects;

public class LoginDataBaseAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login_3.db";
    private static final String CREATE_TABLE_LOGIN = "create table " + "LOGIN_3" + "( "
            + "ID" + "  integer primary key autoincrement," + "NAME text,"
            + "USERNAME  text,PASSWORD text); ";
    private static final String CREATE_TABLE_TRAINS = "create table " + "TRAINS" + "( "
            + "TRAIN_NO text,TRAIN_NAME  text,DATE text,TICKETS_LEFT integer,TICKET_FARE integer,STATIONS text); ";

    private static final String CREATE_TABLE_RECORDS = "create table " + "RECORDS" + "( "
            + "TRAIN_NO text,TRAIN_NAME text,DATE text,NO_OF_TICKETS integer,AMOUNT_PAID integer); ";

    private static final String CREATE_TABLE_FOOD = "create table " + "FOOD" + "( "
            + "ITEM text,AMOUNT integer); ";


    static SQLiteDatabase db;

    public LoginDataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(CREATE_TABLE_LOGIN);
        _db.execSQL(CREATE_TABLE_TRAINS);
        _db.execSQL(CREATE_TABLE_RECORDS);
        _db.execSQL(CREATE_TABLE_FOOD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        onCreate(_db);
    }


    public LoginDataBaseAdapter open() throws SQLException {
        db = this.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    void insertEntry(String name, String userName, String password) {

        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        db.insert("LOGIN_3", null, newValues);
    }

    public int deleteEntry(String username) {

        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN3", where, new String[] { username });
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN_3", null, " USERNAME=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public String getLast_name(String userName){
        Cursor cursor = db.query("LOGIN_3", null, " USERNAME=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("NAME"));
        cursor.close();
        return name;
    }
    public String getUsername(String password){
        Cursor cursor = db.query("LOGIN_3", null, " PASSWORD=?",
                new String[] { password }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
        cursor.close();
        return name;
    }
    public void updateEntry(String name,String userName, String password) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("NAME",name);
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN_3", updatedValues, where, new String[] { userName });
    }

    public void insertEntry_train(String Train_no,String Train_name, String Date,int tickets_left,int fare,String stations) {
        ContentValues newValues = new ContentValues();
        newValues.put("TRAIN_NO",Train_no);
        newValues.put("TRAIN_NAME", Train_name);
        newValues.put("DATE", Date);
        newValues.put("TICKETS_LEFT",tickets_left);
        newValues.put("TICKET_FARE",fare);
        newValues.put("STATIONS",stations);
        db.insert("TRAINS", null, newValues);
    }

    public int deleteEntry_Train(String Train_name) {

        String where = "TRAIN_NAME=?";
        int numberOFEntriesDeleted = db.delete("TRAINS", where, new String[] { Train_name });
        return numberOFEntriesDeleted;
    }

    public String Train_no(String Train_name) {
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NAME=?", new String[] { Train_name }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();
        while (!Objects.equals(Train_name, cursor.getString(1)))
        {
            cursor.moveToNext();
        }

        String Train_no = cursor.getString(cursor.getColumnIndex("TRAIN_NO"));
        cursor.close();
        return Train_no;
    }

    public String Train_name(String Train_no) {
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NO=?", new String[] {Train_no}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();
        while (!Objects.equals(Train_no, cursor.getString(0)))
        {
            cursor.moveToNext();
        }

        String Train_name = cursor.getString(cursor.getColumnIndex("TRAIN_NAME"));
        cursor.close();
        return Train_name;
    }

    public String Date(String Train_no) {
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NO=?", new String[] {(Train_no)}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();
        while (!Objects.equals(Train_no, cursor.getString(0)))
        {
            cursor.moveToNext();
        }

        String date = cursor.getString(cursor.getColumnIndex("DATE"));
        cursor.close();
        return date;
    }
    public int getFare(String Train_no){
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NO=?", new String[]{ (Train_no) }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        int fair;
        cursor.moveToFirst();
        while (!Objects.equals(Train_no, cursor.getString(0)))
        {
            cursor.moveToNext();
        }
                fair = cursor.getInt(cursor.getColumnIndex("TICKET_FARE"));
                cursor.close();
                return fair;

    }

    public int Tickets_left(String Train_no){
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NO=?", new String[]{ (Train_no) }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        while (!Objects.equals(Train_no, cursor.getString(0)))
        {
            cursor.moveToNext();
        }
        int TL = cursor.getInt(cursor.getColumnIndex("TICKETS_LEFT"));
        cursor.close();
        return TL;
    }

    public String Stations(String Train_no){
        Cursor cursor = db.query("TRAINS", null, " TRAIN_NO=?", new String[]{ (Train_no) }, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();
        while (!Objects.equals(Train_no, cursor.getString(0)))
        {
            cursor.moveToNext();
        }
        String stations = cursor.getString(cursor.getColumnIndex("STATIONS"));
        cursor.close();
        return stations;
    }
    public void updateEntry_Train(String Train_no, String Train_name, String Date, int tickets_left,int fare,String stations) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("TRAIN_NO", Train_no);
        updatedValues.put("TRAIN_NAME", Train_name);
        updatedValues.put("DATE", Date);
        updatedValues.put("TICKETS_LEFT",tickets_left);
        updatedValues.put("TICKET_FARE",fare);
        updatedValues.put("STATIONS",stations);
        String where = "  TRAIN_NAME = ?";

        db.update("TRAINS", updatedValues, where, new String[] { Train_name });
    }

    public void insertEntry_Records(String Train_no, String Train_name, String Date, int tickets,int amount) {

        ContentValues newValues = new ContentValues();
        newValues.put("TRAIN_NO",Train_no);
        newValues.put("TRAIN_NAME", Train_name);
        newValues.put("DATE", Date);
        newValues.put("NO_OF_TICKETS", tickets);
        newValues.put("AMOUNT_PAID", amount);
        db.insert("RECORDS", null, newValues);
    }
    public void insertEntry_FOOD(String item,int amount) {

        ContentValues newValues = new ContentValues();
        newValues.put("ITEM", item);
        newValues.put("AMOUNT", amount);
        db.insert("FOOD", null, newValues);
    }
}