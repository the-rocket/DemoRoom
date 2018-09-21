package com.example.daniyar.demoroom.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniyar.demoroom.Entity.Word;
import com.example.daniyar.demoroom.R;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private List<Word> wordList;


    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WordViewHolder viewHolder, int i) {
        viewHolder.wordItemView.setText(wordList.get(i).getWord());
    }

    public void setWords(List<Word> new_list) {
        wordList = new_list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (wordList != null)
            return wordList.size();
        return 0;
    }
}
