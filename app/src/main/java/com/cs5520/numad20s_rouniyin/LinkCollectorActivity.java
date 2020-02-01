package com.cs5520.numad20s_rouniyin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LinkCollectorActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myListView = (ListView) findViewById(R.id.linkList);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        myListView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            View.OnClickListener undoOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItems.remove(listItems.size() -1);
                    adapter.notifyDataSetChanged();
                    Snackbar.make(v, "Item removed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            };

            @Override
            public void onClick(View view) {
               popUpInput();

              // addListItem();
                Snackbar.make(view, "Item added to list", Snackbar.LENGTH_LONG)
                        .setAction("Undo", undoOnClickListener).show();
            }

            public void popUpInput() {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(LinkCollectorActivity.this);
                LayoutInflater inflater = LinkCollectorActivity.this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.input_dialog, null))
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              addListItem();


//                                Snackbar.make(getP, "Item added to list", Snackbar.LENGTH_LONG)
//                                        .setAction("Undo", undoOnClickListener).show();

                            }
                        })
                        .setNegativeButton("Cancel", null);

                builder.create().show();

            }
        });
    }

    private void addListItem() {
        SimpleDateFormat dataformat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
                Locale.US);
        listItems.add(dataformat.format(new Date()));
        adapter.notifyDataSetChanged();


    }

    private String getNewURL(){
        setContentView(R.layout.input_dialog);
        EditText inputName = findViewById(R.id.inputName);
        EditText inputURL = findViewById(R.id.inputURL);

        LinkItem newItem = new LinkItem(inputName.getText().toString(), inputURL.getText().toString());
        return newItem.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    ArrayAdapter<String> adapter;
//    List<String> items = new ArrayList<>();
//    private ListView linkLists;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_link_collector);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        linkLists = (ListView)findViewById(R.id.linkList);
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
//        linkLists.setAdapter(adapter);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                addListItem();
//                // Popup
////                final AlertDialog.Builder builder
////                        = new AlertDialog.Builder(LinkCollectorActivity.this);
////                final LayoutInflater inflater = LinkCollectorActivity.this.getLayoutInflater();
////
////
////                builder.setView(inflater.inflate(R.layout.input_dialog,null))
////                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
////                    final EditText inputName = (EditText)findViewById(R.id.inputName);
////                    final EditText inputURL = (EditText)findViewById(R.id.inputURL);
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        builder.setView(R.layout.input_dialog);
////
////                        setContentView(R.layout.activity_link_collector);
////                        System.out.println(inputName.getText().toString());
////
////                        if (!inputName.getText().toString().equals("")  &&
////                                !inputURL.getText().toString().equals("")){
////
////                            LinkItem item = new LinkItem(inputName.getText().toString(),
////                                    inputURL.getText().toString());
////
////                            items.add("fjhskefe");
////
////                            adapter.notifyDataSetChanged();
////
////                        }
////
////                    }
////                })
////                .setNegativeButton("Cancel", null);
////
////
////
////
////                AlertDialog inputDialog = builder.create();
////                inputDialog.show();
////
////
////
////
////
////
//
//                Snackbar.make(view, "Added to the list", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//    private void addListItem() {
//        SimpleDateFormat dataformat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",
//                Locale.US);
//        items.add(dataformat.format(new Date()));
//        adapter.notifyDataSetChanged();
//
//    }

}
