package com.example.td5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RecyclerView rvContact = (RecyclerView) findViewById(R.id.rvContacts);

        contacts.add(new Contact("Piou", "Landrieux","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Greg", "Lorgniet","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Jean", "Marmoud","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Antoine", "Landrieu","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Martin", "grandpre","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("100", "duytsche","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Garbrielle", "Radenne","https://i.imgur.com/A6tMJcD.jpg"));
        contacts.add(new Contact("Tete", "deBeton","https://i.imgur.com/A6tMJcD.jpg"));

        ContactsAdaptater adaptater = new ContactsAdaptater(contacts,this);
        rvContact.setAdapter(adaptater);
        rvContact.setLayoutManager(new LinearLayoutManager(this));

    }
}