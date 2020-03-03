package com.cs5520.numad20s_rouniyin.ui.linkcollection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs5520.numad20s_rouniyin.LinkItem;
import com.cs5520.numad20s_rouniyin.LinkRepository;

import java.util.List;

public class LinkCollectionViewModel extends AndroidViewModel {

    private LinkRepository repository;
    private LiveData<List<LinkItem>> allLinks;

    public LinkCollectionViewModel(@NonNull Application application) {
        super(application);
        repository = new LinkRepository(application);
        allLinks = repository.getAllLinks();

    }

    public void insertLink(LinkItem link){
        repository.insertLink(link);
    }

    public void clearLink(){
        repository.clearLink();
    }

    LiveData<List<LinkItem>> getAllLinks(){
        return allLinks;
    }

}
