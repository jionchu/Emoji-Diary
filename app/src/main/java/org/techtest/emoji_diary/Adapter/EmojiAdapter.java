package org.techtest.emoji_diary.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.techtest.emoji_diary.R;

import java.util.ArrayList;
import java.util.Map;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder> {

    private Map<Integer,ArrayList<Integer>> emojiList;
    private LayoutInflater inflater;

    public static class EmojiViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivEmoji;
        private TextView tvCount;

        public EmojiViewHolder(View itemView) {
            super(itemView);
            ivEmoji = itemView.findViewById(R.id.emoji_image);
            tvCount = itemView.findViewById(R.id.txt_count);
        }

    }

    public EmojiAdapter(Context context, Map<Integer,ArrayList<Integer>> emoji_list) {
        this.inflater = LayoutInflater.from(context);
        this.emojiList = emoji_list;
    }

    @NonNull
    @Override
    public EmojiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.emoji_item, null, false);
        return new EmojiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiViewHolder diaryViewHolder, int position) {
        int emoji = position+1;
        int count;
        if(emojiList.get(emoji)!=null)
            count = emojiList.get(emoji).size();
        else
            count = 0;

        switch (emoji) {
            case 1:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji1);
                break;
            case 2:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji2);
                break;
            case 3:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji3);
                break;
            case 4:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji4);
                break;
            case 5:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji5);
                break;
            case 6:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji6);
                break;
            case 7:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji7);
                break;
            case 8:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji8);
                break;
            case 9:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji9);
                break;
            case 10:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji10);
                break;
            case 11:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji11);
                break;
            case 12:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji12);
                break;
            case 13:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji13);
                break;
            case 14:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji14);
                break;
            case 15:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji15);
                break;
            case 16:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji16);
                break;
            case 17:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji17);
                break;
            case 18:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji18);
                break;
            case 19:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji19);
                break;
            case 20:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji20);
                break;
            case 21:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji21);
                break;
            case 22:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji22);
                break;
            case 23:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji23);
                break;
            case 24:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji24);
                break;
            case 25:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji25);
                break;
            case 26:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji26);
                break;
            case 27:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji27);
                break;
            case 28:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji28);
                break;
            case 29:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji29);
                break;
            case 30:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji30);
                break;
            case 31:
                diaryViewHolder.ivEmoji.setImageResource(R.drawable.emoji31);
                break;
        }

        diaryViewHolder.tvCount.setText("("+count+")");

    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }
}