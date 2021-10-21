package com.example.contacts.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.activitys.Contact;
import com.example.contacts.activitys.MainActivity;
import com.example.contacts.R;
import com.example.contacts.sqlite.MyDataBase;
import com.google.android.material.textfield.TextInputEditText;

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
    Dialog dialog;
    TextInputEditText dname;
    TextInputEditText dphone;
    TextView tv_save;
    TextView tv_cancel;


    public AdapteContacts() {

    }

    public AdapteContacts(ArrayList<Contact> arrayList,MyDataBase db, int picture, Context context,MainActivity mainActivity) {
        this.arrayList = arrayList;
        this.picture = picture;
        this.context = context;
//        this.mainActivity = mainActivity;
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
        holder.title.setText(contact.getTitle() );
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
//                            public void onClick(DialogInterface dialogInterface, int i

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
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.sign_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dname = dialog.findViewById(R.id.et_name);
                dphone = dialog.findViewById(R.id.et_phone);
                tv_save = dialog.findViewById(R.id.btn_save);
                tv_cancel = dialog.findViewById(R.id.btn_cancel);

                dname.setText(contact.getTitle());
                dphone.setText(contact.getDis());
                dialog.show();
                tv_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String new_name = dname.getText().toString();
                        String new_phone = dphone.getText().toString();
                        db.update_data(new Contact(new_name,new_phone,R.drawable.girls),contact.getId());
                        arrayList.get(position).setTitle(new_name);
                        arrayList.get(position).setDis(new_phone);
                        notifyItemChanged(position);
                        dialog.dismiss();

                    }

                });
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(context, "داده ای ذخیره نشد", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }

        });

        //<editor-fold desc=" Second ways ">
//        holder.title.setText(String.valueOf(arrayList_name.get(position)));
//        holder.phoen.setText(String.valueOf(arrayList_phone.get(position)));
//        holder.pic.setImageResource(picture);
        //</editor-fold>

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

    public Contact removeItem(int position) {
        Contact item = null;

        item = arrayList.get(position);
        arrayList.remove(position);
        notifyItemRemoved(position);
        return item;
    }


}
