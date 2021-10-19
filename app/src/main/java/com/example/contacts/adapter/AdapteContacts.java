package com.example.contacts.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.Contact;
import com.example.contacts.MainActivity;
import com.example.contacts.R;
import com.example.contacts.sqlite.MyDataBase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapteContacts extends RecyclerView.Adapter<AdapteContacts.ViewHolder> {
    ArrayList<Contact> arrayList;
//    ArrayList<String> arrayList_name;
//    ArrayList<String> arrayList_phone;
    int picture;
    Context context;
    MainActivity mainActivity;
    MyDataBase db;


    public AdapteContacts(ArrayList<Contact> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public AdapteContacts(ArrayList<Contact> arrayList,MyDataBase db, int picture, Context context,MainActivity mainActivity) {
        this.arrayList = arrayList;
        this.picture = picture;
        this.context = context;
        this.mainActivity = mainActivity;
        this.db = db;

    }
    //    public AdapteContacts(ArrayList<String> arrayList_name, ArrayList<String> arrayList_phone, int picture, Context context) {
//        this.arrayList_name = arrayList_name;
//        this.arrayList_phone = arrayList_phone;
//        this.picture = picture;
//        this.context = context;
//    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_contacts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapteContacts.ViewHolder holder, int position) {
        Contact contact = arrayList.get(position);
        holder.title.setText(contact.getId() +"-" +contact.getTitle() );
        holder.phoen.setText(contact.getDis());
        holder.pic.setImageResource(picture);
        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.delet.setImageDrawable(R.drawable.ic_sharp_delete_24);

//                new AlertDialog.Builder(context)
//                        .setTitle("درخواست مجوز")
//                        .setMessage("برای دسترسی به دوربین باید مجوز را تایید کنید")
//                        .setPositiveButton("موافقم", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {

                                db.deletdelet(contact.getId());
                                arrayList.remove(position);
//                              recyclerView.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, arrayList.size());
                                notifyDataSetChanged();
                                Toast.makeText(context, "با موفقیت حذف شد 😉 ", Toast.LENGTH_LONG).show();


//                            }
//                        })
//                        .setNegativeButton("لغو", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                dialogInterface.dismiss();
//
//                            }
//                        })
//                        .create()
//                        .show();

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mainActivity = new MainActivity();
//               mainActivity.setContentView(R.layout.sign_dialog);
//                mainActivity.dialog_show();
//                mainActivity.dName.setText(holder.title.getText());
//                mainActivity.dPhone.setText(holder.title.getText());
                Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
            }
        });



//        MyDataBase myDataBase = new MyDataBase(context);
//        myDataBase.add_Contact_new(contact);


        //<editor-fold desc=" Second ways ">
//        holder.title.setText(String.valueOf(arrayList_name.get(position)));
//        holder.phoen.setText(String.valueOf(arrayList_phone.get(position)));
//        holder.pic.setImageResource(picture);
        //</editor-fold>
//


    }

    @Override
    public int getItemCount() {
//        return arrayList_name.size();
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title ;
        TextView phoen ;
//        ImageView pic;
        CircleImageView pic;
        ImageView delet;
        ImageView edit;
        public ViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            phoen = itemView.findViewById(R.id.tv_phone);
            pic = itemView.findViewById(R.id.circleImage);
            delet = itemView.findViewById(R.id.img_delet);
            edit = itemView.findViewById(R.id.img_edit);

        }
    }


}
