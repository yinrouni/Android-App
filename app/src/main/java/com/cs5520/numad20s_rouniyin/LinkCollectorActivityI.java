package com.cs5520.numad20s_rouniyin;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;

import java.util.ArrayList;

public class LinkCollectorActivityI extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector_i);
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
            }

            public void popUpInput() {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(LinkCollectorActivityI.this);
                LayoutInflater inflater = LayoutInflater.from(LinkCollectorActivityI.this);
                final View inputView = inflater.inflate(R.layout.input_dialog, null);
                builder.setView(inputView);

                final AlertDialog alertDialog = builder.create();

                alertDialog.show();
                Button addButton = inputView.findViewById(R.id.add);
                Button cancelButton = inputView.findViewById(R.id.cancel);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText inputName = inputView.findViewById(R.id.inputName);
                        EditText inputURL = inputView.findViewById(R.id.inputURL);

                        String name = inputName.getText().toString();
                        String URL = inputURL.getText().toString();
                        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(URL)){
                            Snackbar.make(v, "Name or URL can not be empty.",
                                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }

                        else {
                            listItems.add(new LinkItem(name, URL).toString());
                            adapter.notifyDataSetChanged();
                            Snackbar.make(v, "Added to the list.",
                                    Snackbar.LENGTH_LONG).setAction("Action",
                                    null).show();
                            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String nu = (String) parent.getAdapter().getItem(position);

                                    String url = nu.split("\n")[1];
                                    openWebPage(url);

                                }
                            });

                        }


                    }
                });

            }
        });
    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Snackbar.make(myListView, "Can't open the link.",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
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


}
