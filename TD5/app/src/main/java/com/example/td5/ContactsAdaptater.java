package com.example.td5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactsAdaptater extends RecyclerView.Adapter<ContactsAdaptater.ViewHolder> {

    private final List<Contact> mContact;
    private Context Context;

    public ContactsAdaptater(List<Contact> mContact, Context context) {
        this.mContact = mContact;
        this.Context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact,parent,false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContact.get(position);

        TextView firstnameTextView= holder.firstnameTextView;
        firstnameTextView.setText(contact.getPrenom());

        TextView lastnameTextView= holder.lastnameTextView;
        lastnameTextView.setText(contact.getNom());

        ImageView urlImageView = holder.urlImageView;
        Picasso.get().load(contact.getURL()).into(urlImageView);
    }

    @Override
    public int getItemCount() {
        return mContact.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView firstnameTextView;
        public TextView lastnameTextView;
        public ImageView urlImageView;

        public ViewHolder(View itemView){
            super(itemView);

            firstnameTextView = (TextView) itemView.findViewById(R.id.textView);
            lastnameTextView = (TextView) itemView.findViewById(R.id.textView2);
            urlImageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
