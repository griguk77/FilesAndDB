package com.mycompany.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private TextView textView;
    private ArrayList<Contact> contactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StringBuilder s = new StringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        textView = findViewById(R.id.db_cont);

        contactArrayList = new ArrayList<>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,
                null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {
            @SuppressLint("Range") String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Contact contact = new Contact();
            contact.setName(name);
            contact.setNumber(phoneNumber);
            contactArrayList.add(contact);
            s.append(String.format("Имя: %s, номер: %s\n", contact.getName(), contact.getNumber()));
            Log.d("name>>",name+"  "+phoneNumber);
        }
        phones.close();
        textView.setText(s);


    }

}