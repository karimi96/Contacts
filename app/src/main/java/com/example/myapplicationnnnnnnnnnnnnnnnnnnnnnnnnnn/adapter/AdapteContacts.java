package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapteContacts extends RecyclerView.Adapter<AdapteContacts.ViewHolder> {
    ArrayList<Contact> arrayList;
    Context context;

    public AdapteContacts(ArrayList<Contact> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
//    public AdapteContacts( Context context) {
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
        holder.title.setText(contact.getTitle());
        holder.phoen.setText(contact.getDis());
        holder.pic.setImageResource(contact.getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
