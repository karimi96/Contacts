package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.Contact;
import com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.adapter.DialogCantact;

import java.util.ArrayList;

public class Sqlite extends SQLiteOpenHelper {
     ArrayList arrayList;
    private static final String DatabaseName = "contacts.db";
    private static final int Version = 1 ;
//    private static final String TableName = "Contacts";
//    private static final String dID = "contactID";
//    private static final String dName = "name";
//    private static final String dPhone = "phone";


    public Sqlite(Context context) {
        super(context, DatabaseName, null, Version );
        Log.i("roghy", "database Created ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String cQuery = "CREATE TABLE Contacts (cID INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , phone VARCHAR , image INTEGER)";
        db.execSQL(cQuery);

//           String query = "CREATE TABLE Student (id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , phone VARCHAR );";
//            db.execSQL(query);
//                Log.i("roghy", "Table created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String student = "DROP TABLE IF EXISTS Student";
//        db.execSQL(student);
        String contact = "DROP TABLE IF EXISTS Contacts";
        db.execSQL(contact);

    }


//    public void insertContact(DialogCantact dc){
//        SQLiteDatabase dtb = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(dName,dc.name);
//        contentValues.put(dPhone,dc.phone);
//        dtb.insert(TableName,null,contentValues);
//        dtb.close();
//    }


//    public boolean insertStudnet(String name ,String phone){
//        SQLiteDatabase database = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name" , name);
//        contentValues.put("phone" , phone);
//        database.insert("Student",null,contentValues);
//        database.close();
//      return true;
//
//    }
//    public String getNames_stu(int pos){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select name from Student",null);
//        cursor.moveToPosition(pos);
//        String name = cursor.getString(0);
//        return name ;
//    }
//    public ArrayList<String> getnameStu2222() {
//        arrayList = new ArrayList();
//        SQLiteDatabase mDB = this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select name from Student  ", null);
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToPosition(i);
//            arrayList.add(cursor.getString(0));
//        }
//        return arrayList;
//    }
//    public int getNames_Count_stu(){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select name from Student",null);
//        return cursor.getCount() ;
//    }
//
//    public String getPhone_stu(int pos){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select phone from Student",null);
//        cursor.moveToPosition(pos);
//        String phone = cursor.getString(0);
//        return phone ;
//    }





    public boolean insertContacts(String name ,String phone){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , name);
        contentValues.put("phone" , phone);
        database.insert("Contacts",null,contentValues);
        database.close();
      return true;
    }



    public String getNames(int pos){
        SQLiteDatabase mDB= this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select name from Contacts",null);
        cursor.moveToPosition(pos);
        String name = cursor.getString(0);
        return name ;
    }

    public ArrayList<String> getNames2() {
        arrayList = new ArrayList();
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select name from Contacts  ", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            arrayList.add(cursor.getString(0));
        }
        return arrayList;
    }


    public int getCounts_name(){
        SQLiteDatabase mDB= this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select name from Contacts",null);
        return cursor.getCount() ;
    }


    public String getPhone(int pos){
        SQLiteDatabase mDB= this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select phone from Contacts",null);
        cursor.moveToPosition(pos);
        String phone = cursor.getString(0);
        return phone ;
    }

    public ArrayList<String> getPhone2() {
        arrayList = new ArrayList();
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select phone from Contacts  ", null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            arrayList.add(cursor.getString(0));
        }
        return arrayList;
    }







}
