package com.cs5520.numad20s_rouniyin;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LinkRepository {

    private LiveData<List<LinkItem>> allLinks;
    private LinkDao linkDao;

    public LinkRepository(Application application){
        LinkRoomDatabase db;
        db=LinkRoomDatabase.getDatabase(application);
        linkDao = db.linkDao();
        allLinks = linkDao.getAllLinks();
    }

    private static class InsertAsyncTask extends AsyncTask<LinkItem, Void, Void> {

        private LinkDao asyncTaskDao;

        InsertAsyncTask(LinkDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final LinkItem... params) {
            asyncTaskDao.insertLink(params[0]);
            return null;
        }
    }

    private static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {

        private LinkDao asyncTaskDao;

        ClearAsyncTask(LinkDao dao) {
            asyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.clearLinks();
            return null;
        }
    }


    public void insertLink(LinkItem newlink){
        InsertAsyncTask task = new InsertAsyncTask(linkDao);
        task.execute(newlink);

    }

    public void clearLink(){
        ClearAsyncTask task = new ClearAsyncTask(linkDao);
        task.execute();
    }

    public LiveData<List<LinkItem>> getAllLinks(){
        return allLinks;
    }
}
