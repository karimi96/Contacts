package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.adapter.AdapteContacts;
import com.example.contacts.sqlite.MyDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AdapteContacts adapteContacts;
    RecyclerView recyclerView;
    ArrayList<Contact> arrayList;
    FloatingActionButton floatingActionButton;
    Dialog dialog;
    ArrayList<String> arrayName;
    ArrayList<String> arrayPhone;
    SQLiteDatabase db;
    MyDataBase myDataBase;
    Toolbar toolbar;

    String name;
    String phone;

    public TextInputEditText dName;
    public TextInputEditText dPhone;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.tool_bar,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //<editor-fold desc="ّFor ToolBar">
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //</editor-fold>

            init_Recycler();

        dialog_show();
        dName = dialog.findViewById(R.id.et_name);
        dPhone = dialog.findViewById(R.id.et_phone);


        TextView save = dialog.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = dName.getText().toString();
                phone = dPhone.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){

                    Toast.makeText(MainActivity.this, "تمامی فیلدها باید تکمیل شود", Toast.LENGTH_LONG).show();

                }else {
                    save.setBackgroundColor(getResources().getColor(R.color.teal_700));


                    myDataBase.add_Contact_new(new Contact(name,phone,R.drawable.ic_baseline_person_24));
//                    MyDataBase dataBase = new MyDataBase(MainActivity.this);
//                    dataBase.add_Contact(name,phone);

                    init_Recycler();
                    Toast.makeText(MainActivity.this, name+ "  با موفقیت ثبت شد ", Toast.LENGTH_LONG).show();
                    dName.setText("");
                    dPhone.setText("");
                    dialog.dismiss();
                }

            }
        });


        TextView cancel = dialog.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(MainActivity.this," کنسل شد" , Toast.LENGTH_LONG).show();

                dialog.dismiss();

            }
        });


        floatingActionButton = findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.show();

            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void init_Recycler(){
        myDataBase = new MyDataBase(MainActivity.this);

        arrayName = new ArrayList<>();
        arrayPhone = new ArrayList<>();
//        get_all_data();
        recyclerView = findViewById(R.id.rcy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapteContacts = new AdapteContacts(arrayName,arrayPhone,R.drawable.ic_baseline_person_24,MainActivity.this);
//        for (int i = 0; i < myDataBase.getcount(); i++) {
//            arrayList = new ArrayList<>();
//            arrayList.add(new Contact(arrayName,arrayPhone,R.drawable.ic_baseline_person_24));
//            adapteContacts = new AdapteContacts(arrayList,MainActivity.this);
//        Cursor cursor = myDataBase.show_all_data();
//        for (int i = 0; i <cursor.getCount(); i++) {
//            arrayList = new ArrayList<>();
//            arrayList.add(new  Contact(name,phone,R.drawable.ic_baseline_person_24));
//
//        }
        adapteContacts = new AdapteContacts(myDataBase.peopleList(),myDataBase,R.drawable.girls,MainActivity.this,this);
            recyclerView.setAdapter(adapteContacts);


    }

    public void dialog_show(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.sign_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_desin));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//        EditText dName = dialog.findViewById(R.id.et_name);
//        EditText dPhone = dialog.findViewById(R.id.et_phone);



    }


    void get_all_data() {
        Cursor cursor = myDataBase.show_all_data();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No date", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayName.add(cursor.getString(1));
                arrayPhone.add(cursor.getString(2));
            }
        }

    }




//    public void full_name(){
//        for (int i = 0; i < myDataBase.getcount(); i++) {
//            arrayName = new ArrayList<>();
//            arrayName.add(myDataBase.getname(i));
//        }
//    }
//
//
//    public void full_phone(){
//        for (int i = 0; i < myDataBase.getcount(); i++) {
//            arrayPhone = new ArrayList<>();
//            arrayPhone.add(myDataBase.getPhone(i));
//
//        }
//    }


    String deletedMovie = null ;
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder,  RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped( RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            arrayList=new ArrayList<>();


            switch (direction){
                case ItemTouchHelper.LEFT:
//                    deletedMovie = arrposition);
                    arrayList.remove(position);
//                    adapteContacts.notifyItemRemoved(position);
                    Snackbar.make(recyclerView,deletedMovie,Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick(View v) {
                                    arrayName.add(position ,deletedMovie);
                                    adapteContacts.notifyItemInserted(position);
                                }
                            }).show();
                    break;

                case ItemTouchHelper.RIGHT:

//                 String phonnumber = myDataBase.getPhone(10);
//                Intent call = new Intent(Intent.ACTION_DIAL);
//                call.setData(Uri.parse("tel:" + phonnumber));
//                startActivity(call);

                    break;
            }
        }

//        @Override
//        public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive){
//            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_700))
//                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_person_24)
//                    .addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_700))
//                    .addSwipeRightActionIcon(R.drawable.ic_baseline_person_24)
////                    .addSwipeRightLabel(getString())
//                    .setSwipeRightLabelColor(Color.WHITE)
////                    .addSwipeLeftLabel(getString(R.string.action_archive))
//                    .setSwipeLeftLabelColor(Color.WHITE)
//                    .create()
//                    .decorate();
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }



//        public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive){
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.purple_700))
//                    .addActionIcon(R.drawable.ic_baseline_person_24)
//                    .create()
//                    .decorate();
//        }





    };



















}



































