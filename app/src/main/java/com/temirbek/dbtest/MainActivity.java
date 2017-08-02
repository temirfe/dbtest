package com.temirbek.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHandler dbHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        //new UpdateTask().execute();
        //new AddTask().execute();
        new DeleteTask().execute(4);
        new ReadTask().execute();
    }

    private class UpdateTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            if(dbHandler==null){dbHandler = new DatabaseHandler(MainActivity.this);}
            if(db==null || !db.isOpen()){db = dbHandler.getWritableDatabase();}

            Contact cn=dbHandler.getContact(4);
            cn.setName("Aiganym");
            dbHandler.updateContact(cn,db);

            return null;
        }
    }

    private class AddTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            if(dbHandler==null){dbHandler = new DatabaseHandler(MainActivity.this);}
            if(db==null || !db.isOpen()){db = dbHandler.getWritableDatabase();}

            dbHandler.addContact(new Contact("Sophia", "05434544545"), db);

            return null;
        }
    }

    private class ReadTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            if(dbHandler==null){dbHandler = new DatabaseHandler(MainActivity.this);}
            if(db==null || !db.isOpen()){db = dbHandler.getWritableDatabase();}

            List<Contact> contacts = dbHandler.getAllContacts(db);

            for (Contact cn : contacts) {
                String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
                Log.e("Name: ", log);
            }

            return null;
        }
    }

    private class DeleteTask extends AsyncTask<Integer, Void, Void> {
        protected Void doInBackground(Integer... params) {
            if(dbHandler==null){dbHandler = new DatabaseHandler(MainActivity.this);}
            if(db==null || !db.isOpen()){db = dbHandler.getWritableDatabase();}


            Contact cn=dbHandler.getContact(params[0]);
            dbHandler.deleteContact(cn,db);

            return null;
        }
    }

    private class CountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            if(dbHandler==null){dbHandler = new DatabaseHandler(MainActivity.this);}
            if(db==null || !db.isOpen()){db = dbHandler.getWritableDatabase();}

            int coun=dbHandler.getContactsCount(db);
            Log.e("count: ", coun+"");

            return null;
        }
    }
}
