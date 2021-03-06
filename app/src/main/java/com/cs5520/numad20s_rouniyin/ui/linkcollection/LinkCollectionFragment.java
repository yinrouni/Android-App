package com.cs5520.numad20s_rouniyin.ui.linkcollection;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs5520.numad20s_rouniyin.LinkItem;
import com.cs5520.numad20s_rouniyin.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LinkCollectionFragment extends Fragment {

    private LinkCollectionViewModel mViewModel;
    private LinkListAdapter adapter;

    private EditText linkName;
    private EditText linkUrl;

    public static LinkCollectionFragment newInstance() {
        return new LinkCollectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.link_collection_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LinkCollectionViewModel.class);

        linkUrl = getView().findViewById(R.id.linkURL);
        linkName = getView().findViewById(R.id.linkName);


        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;

        adapter = new LinkListAdapter(R.layout.link_list_item);
        adapter.setOnItemClickListener(new LinkListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               String url =  adapter.getLinkList().get(position).getUrl();
               openWeb(url);
            }
        });
        recyclerView = getView().findViewById(R.id.link_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    private void openWeb(String url)  {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        else{

            Snackbar.make(getView(),"Can't open the link.",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    private void observerSetup() {
        mViewModel.getAllLinks().observe(this, new Observer<List<LinkItem>>() {
            @Override
            public void onChanged(final List<LinkItem> linkItems) {
                adapter.setLinkList(linkItems);
            }
        });
    }

    private void clearFields(){
        linkName.setText("");
        linkUrl.setText("");
    }

    private void listenerSetup() {
        Button addButton = getView().findViewById(R.id.addLink);
        Button clearButton = getView().findViewById(R.id.clearLink);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = linkName.getText().toString();
                String url = linkUrl.getText().toString();

                if(!name.equals("") && !url.equals("")){
                    LinkItem link = new LinkItem(name, url);
                    mViewModel.insertLink(link);
                    clearFields();
                    Snackbar.make(v, "Added to the list",
                            Snackbar.LENGTH_LONG).setAction("Action",
                            null).show();
                }
                else {
                    Snackbar.make(v, "Incomplete information",
                            Snackbar.LENGTH_LONG).setAction("Action",
                            null).show();
                }

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.clearLink();
                clearFields();
            }
        });

    }

}
