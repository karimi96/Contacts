package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String DB_Name = "MyContacts.db";
    private static final int version = 1 ;

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


    public void add_Contact(String namee,String phonee){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",namee );
        cv.put("phone",phonee );
        long result = db.insert("Contacts",null,cv);
//        if (result == -1){
//            Toast.makeText(context, "Faild", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Add Seccesfully", Toast.LENGTH_SHORT).show();
//        }

    }
    public Cursor show_all_data(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = " SELECT * FROM Contacts";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query,null);
        }
        return cursor;
    }


    public void update_data(String nam ,String ph,int pos){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",nam);
        values.put("phone",ph);
        database.update("Contacts",values,"cID=" + pos,null);

    }

    public boolean delet_item_cursor(int pos){
        SQLiteDatabase database = this.getWritableDatabase();
        database.rawQuery("delete name from Contacts where cID="+pos,null);
        return true;
    }


    public boolean delet_item(int pos){
        SQLiteDatabase database = this.getWritableDatabase();
//        String query = "delete name from Contacts where cID =" + pos;
//        Cursor cursor = database.rawQuery(query,null);
        long result = database.delete("Contacts","cID=?"+ pos,null );
       if(result == 0)
        return false;
       else
           return true;
    }







//    public String getname(int pos){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select name from Contacts",null);
//        cursor.moveToPosition(pos);
//        String nam = cursor.getString(0);
//        return nam ;
//    }

//    public int getcount(){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select name from Contacts",null);
//        return cursor.getCount() ;
//    }



//    public String getPhone(int pos){
//        SQLiteDatabase mDB= this.getReadableDatabase();
//        Cursor cursor = mDB.rawQuery("select phone from Contacts",null);
//        cursor.moveToPosition(pos);
//        String nam = cursor.getString(0);
//        return nam ;
//    }

}
