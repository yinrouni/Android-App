package com.cs5520.numad20s_rouniyin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LinkCollectorActivity extends AppCompatActivity {
    public void redirectToLinkCollector1(View view){
        Intent intent = new Intent(this, LinkCollectorActivityI.class);
        startActivity(intent);
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, LinkCollectionFragment.newInstance()).commitNow();
    }

    public void redirectToLinkCollector2(View view){
        Intent intent = new Intent(this, LinkCollectorActivityII.class);
        startActivity(intent);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);
    }
}
