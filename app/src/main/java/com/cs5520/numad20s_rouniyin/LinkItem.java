package com.cs5520.numad20s_rouniyin;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "links")
public
class LinkItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "linkId")
    private int id;

    @ColumnInfo(name = "linkName")
    @NonNull
    private String name;

    @ColumnInfo(name = "linkUrl")
    @NonNull
    private String url;

    public LinkItem(String name, String url){
        this.name= name;
        this.url = url;

    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public String toString(){
        return getName() + "\n" + getUrl();
    }
}
