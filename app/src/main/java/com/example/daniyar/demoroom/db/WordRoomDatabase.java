package com.example.daniyar.demoroom.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.daniyar.demoroom.Dao.WordDao;
import com.example.daniyar.demoroom.Entity.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase{

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase Instance;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(Instance).execute();
                }
            };

    static WordRoomDatabase getDatabase(final Context context) {
        if (Instance == null) {
            synchronized (WordRoomDatabase.class) {
                if (Instance == null) {
                    Instance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return Instance;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase wordDao) {
            mDao = wordDao.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Name");
            mDao.insert(word);
            word = new Word("Daniyar");
            mDao.insert(word);
            return null;
        }
    }

}
