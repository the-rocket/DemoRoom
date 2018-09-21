package com.example.daniyar.demoroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.daniyar.demoroom.Entity.Word;
import com.example.daniyar.demoroom.ViewModel.WordViewModel;
import com.example.daniyar.demoroom.adapter.WordListAdapter;

import java.util.List;

public class MainActivity extends CoreActivity {

    RecyclerView recyclerView;
    WordListAdapter adapter;
    WordViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.d("asd", "asdas");
        initViewModel();
        initRecycler();
        initObservers();

    }

    void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(WordViewModel.class);
    }

    void initRecycler() {
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initObservers() {
        viewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                adapter.setWords(words);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("asd", viewModel.getAllWords().getValue().get(0).toString());
        //adapter.setWords(viewModel.getAllWords().getValue());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected WordViewModel getViewModel() {
        return viewModel;
    }
}
