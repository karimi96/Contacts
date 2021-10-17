package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.Contact;
import com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn.R;

import java.util.ArrayList;

public class AdapteContacts extends RecyclerView.Adapter<AdapteContacts.ViewHolder> {
//    ArrayList<Contact> arrayList;
    ArrayList<String> arrayList_name;
    ArrayList<String> arrayList_phone;
    int picture;
    Context context;

//    public AdapteContacts(ArrayList<Contact> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//    }

    public AdapteContacts(ArrayList<String> arrayList_name, ArrayList<String> arrayList_phone, int picture, Context context) {
        this.arrayList_name = arrayList_name;
        this.arrayList_phone = arrayList_phone;
        this.picture = picture;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_contacts,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapteContacts.ViewHolder holder, int position) {
//        Contact contact = arrayList.get(position);
//        holder.title.setText(String.valueOf(contact.getTit()) );
//        holder.phoen.setText(String.valueOf(contact.getPh()));
//        holder.pic.setImageResource(contact.getImage());

        //<editor-fold desc=" Second ways ">
        holder.title.setText(String.valueOf(arrayList_name.get(position)));
        holder.phoen.setText(String.valueOf(arrayList_phone.get(position)));
        holder.pic.setImageResource(picture);
        //</editor-fold>

    }

    @Override
    public int getItemCount() {
        return arrayList_name.size();
//        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title ;
        TextView phoen ;
        ImageView pic;
        public ViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            phoen = itemView.findViewById(R.id.tv_phone);
            pic = itemView.findViewById(R.id.img_contact);
        }
    }
}
