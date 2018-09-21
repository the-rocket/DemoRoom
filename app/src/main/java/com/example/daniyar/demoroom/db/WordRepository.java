package com.example.daniyar.demoroom.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.daniyar.demoroom.Dao.WordDao;
import com.example.daniyar.demoroom.Entity.Word;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>>  allWords;


    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = db.wordDao().getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(wordDao).execute(word);
    }

    public void deleteAll() {
        wordDao.deleteAll();
    }


    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;
        insertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }
        @Override
        protected Void doInBackground(final Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }


}
