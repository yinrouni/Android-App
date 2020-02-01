package com.cs5520.numad20s_rouniyin;

class LinkItem {
    private String name;
    private String url;

    public LinkItem(String name, String url){
        this.name= name;
        this.url = url;

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
