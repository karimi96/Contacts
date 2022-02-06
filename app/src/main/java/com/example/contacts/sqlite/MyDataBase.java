package com.example.contacts.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.contacts.activitys.Contact;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DB_Name = "MyContacts.db";
    private static final int version = 1;
    Context context;

    public MyDataBase(Context context) {
        super(context, DB_Name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cQuery = "CREATE TABLE Contacts (cID INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , phone VARCHAR , image INTEGER);";
        db.execSQL(cQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String contacts = "DROP TABLE IF EXISTS Contacts";
        db.execSQL(contacts);
    }


    //    Use With Contact Class
    public void add_Contact_new(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", contact.getTitle());
        cv.put("phone", contact.getDis());
        long result = db.insert("Contacts", null, cv);
//        if (result == -1){
//            Toast.makeText(context, "Faild", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Add Seccesfully", Toast.LENGTH_SHORT).show();
//        }
        db.close();
    }



    public void update_data(Contact contact, int pos) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getTitle());
        values.put("phone", contact.getDis());
        database.update("Contacts", values, "cID=" + pos, null);
    }


    public ArrayList<Contact> peopleList() {
        String query;
        //regular query
        query = "SELECT  * FROM Contacts";
        ArrayList<Contact> personLinkedList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Contact contact;

        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();

                contact.setId(cursor.getInt(cursor.getColumnIndex("cID")));
                contact.setTitle(cursor.getString(cursor.getColumnIndex("name")));
                contact.setDis(cursor.getString(cursor.getColumnIndex("phone")));
                personLinkedList.add(contact);
            } while (cursor.moveToNext());
        }
//        db.close();
        return personLinkedList;
    }


    public void deletdelet(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Contacts where cID =" + id);
    }


    public String getPhone(int pos) {
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select phone from Contacts", null);
        cursor.moveToPosition(pos);
        String nam = cursor.getString(0);
        Log.e("qqqq", "getPhone: get phone" + nam);
        return nam;
    }
}
