package com.example.contacts.activitys;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;
import com.example.contacts.adapter.AdapteContacts;
import com.example.contacts.sqlite.MyDataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    ImageView imageView_choose_pic;
    public TextInputEditText dName;
    public TextInputEditText dPhone;
    private static final int OPEN_DOCUMENT_CODE = 2;


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
//                    save.setBackgroundColor(getResources().getColor(R.color.teal_700));
                    myDataBase.add_Contact_new(new Contact(name,phone,R.drawable.ic_baseline_person_24));
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
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

//                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, OPEN_DOCUMENT_CODE);

              dialog.show();

            }
        });


        item_Touch_Helper();

    }



    public void init_Recycler(){
        myDataBase = new MyDataBase(MainActivity.this);
        recyclerView = findViewById(R.id.rcy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
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
        imageView_choose_pic = dialog.findViewById(R.id.img_choose_picture);
        imageView_choose_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImagePicker.Companion.with(MainActivity.this)
//                        .crop()
//                        .cropOval()
//                        .maxResultSize(1080,1080,true);
//                        .start();

//                ImagePicker.Companion.with(MainActivity.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .cropOval()	    		//Allow dimmed layer to have a circle inside
//                        .cropFreeStyle()	    //Let the user to resize crop bounds
//                        .galleryOnly()          //We have to define what image provider we want to use
////                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                        .createIntent();

//                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();

//                ActivityResultLauncher<Intent> launcher =
//                        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
//                            if (result.getResultCode() == RESULT_OK) {
//                                Uri uri = result.getData().getData();
//                                // Use the uri to load the image
//                            } else if (result.getResultCode() == ImagePicker.RESULT_ERROR) {
//                                // Use ImagePicker.Companion.getError(result.getData()) to show an error
//                            }
//                        });

//
//                ImagePicker.Companion.with(MainActivity.this)
//                        .crop()
//                        .cropOval()
//                        .maxResultSize(512, 512, true)
//                        .createIntentFromDialog((Function1) (new Function1() {
//                            public Object invoke(Object var1) {
//                                this.invoke((Intent) var1);
//                                return Unit.INSTANCE;
//                            }
//
//                            public final void invoke(@NotNull Intent it) {
//                                Intrinsics.checkNotNullParameter(it, "it");
//                                launcher.launch(it);
//                            }
//                        }));

//                ImagePicker.with(MainActivity.this)
//                        .galleryOnly()	//User can only select image from Gallery
//                        .createIntent();	//Default Request Code is ImagePicker.REQUEST_CODE


            }
        });
    }

    public void item_Touch_Helper(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact;
                int position = viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                                try {
//                                    SmsManager smsManager = SmsManager.getDefault();
//                                    smsManager.sendTextMessage("09121537371" , null , "sghl" , null,null);
                                    String number_for_sms = myDataBase.getPhone(position);
                                    Intent sms = new Intent(Intent.ACTION_VIEW);
                                    sms.putExtra("sms_body","");
                                    sms.putExtra("address",number_for_sms);
                                    sms.setData(Uri.parse("smsto:"));
                                    sms.setType("vnd.android-dir/mms-sms");
                                    startActivity(sms);
                                    adapteContacts.notifyItemChanged(position);
//                                    adapteContacts.removeItem(position);

                                }catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "faild to", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                            }
                        }

//                        Snackbar.make(recyclerView, deletedMovie, Snackbar.LENGTH_LONG)
//                                .setAction("Undo", new View.OnClickListener() {
//                                    @SuppressLint("ResourceAsColor")
//                                    @Override
//                                    public void onClick(View v) {
//                                        arrayName.add(position, deletedMovie);
//                                        adapteContacts.notifyItemInserted(position);
//                                    }
//                                }).show();
                        break;

                    case ItemTouchHelper.RIGHT:

                        String number_for_phone = myDataBase.getPhone(position);
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + number_for_phone));
                        startActivity(call);
                        adapteContacts.notifyItemChanged(position);


                        break;
                }
            }

            @Override
            public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.orang_massage))
                        .addSwipeLeftActionIcon(R.drawable.ic_outline_sms_24)
                        .addSwipeLeftLabel("Message")
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green_call))
                        .addSwipeRightActionIcon(R.drawable.ic_outline_phone_24)
                        .addSwipeRightLabel("Call")
                        .setIconHorizontalMargin(15)
                        .setSwipeRightLabelColor(Color.WHITE)
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }







//    void get_all_data() {
//        Cursor cursor = myDataBase.show_all_data();
//        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "No date", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()) {
//                arrayName.add(cursor.getString(1));
//                arrayPhone.add(cursor.getString(2));
//            }
//        }
//
//    }

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




//
//    ActivityResultLauncher<Intent> launcher =
//            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
//                if (result.getResultCode() == RESULT_OK) {
//                    Uri uri = result.getData().getData();
//                    // Use the uri to load the image
//                    imageView_choose_pic.setImageURI(uri);
//                } else if (result.getResultCode() == ImagePicker.RESULT_ERROR) {
//                    // Use ImagePicker.Companion.getError(result.getData()) to show an error
//                }
//
//            });


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        Uri uri = result.getData().getData();
//        // Use the uri to load the image
//        imageView_choose_pic.setImageURI(uri);


    }
}

