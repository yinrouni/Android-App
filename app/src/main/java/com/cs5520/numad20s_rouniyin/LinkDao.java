package com.cs5520.numad20s_rouniyin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LinkDao {
    @Insert
    void insertLink(LinkItem link);

    @Query("DELETE FROM links")
    void clearLinks();

    @Query("SELECT * FROM links")
    LiveData<List<LinkItem>> getAllLinks();


}
