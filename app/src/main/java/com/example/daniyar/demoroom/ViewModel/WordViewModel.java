package com.example.daniyar.demoroom.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.daniyar.demoroom.Entity.Word;
import com.example.daniyar.demoroom.db.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWords() {
        return wordRepository.getAllWords();
    }

    public void insert(Word word) {
        wordRepository.insert(word);
    }


}
