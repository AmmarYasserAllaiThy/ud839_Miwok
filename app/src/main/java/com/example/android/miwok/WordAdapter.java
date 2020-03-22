package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;
    private static MediaPlayer mediaPlayer = null;

    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        final Word currentWord = getItem(position);

        ImageView iv = itemView.findViewById(R.id.iv);
        TextView tvE = itemView.findViewById(R.id.tvE);
        TextView tvM = itemView.findViewById(R.id.tvM);

        tvE.setText(currentWord.getDefaultTranslation());
        tvM.setText(currentWord.getMiwokTranslation());

        if (currentWord.hasImage()) {
            iv.setImageResource(currentWord.getImageResourceId());
            iv.setVisibility(View.VISIBLE);
        } else iv.setVisibility(View.GONE);

        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        itemView.findViewById(R.id.text_container).setBackgroundColor(color);

        itemView.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(getContext(), currentWord.getAudioResourceId());
            mediaPlayer.start();
        });

        return itemView;
    }
}