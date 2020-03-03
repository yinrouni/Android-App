package com.cs5520.numad20s_rouniyin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cs5520.numad20s_rouniyin.ui.linkcollection.LinkCollectionFragment;

public class LinkCollectorActivityII extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_collection_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LinkCollectionFragment.newInstance())
                    .commitNow();
        }
    }
}
