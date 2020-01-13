package org.techtest.emoji_diary.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.techtest.emoji_diary.Diary;
import org.techtest.emoji_diary.MainActivity;
import org.techtest.emoji_diary.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private ArrayList<Diary> diaryArrayList;
    private LayoutInflater inflater;
    private String title,content;
    private Date date;
    private int emoji;

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage,ivFavorite;
        TextView tvTitle,tvDate;

        public DiaryViewHolder(View itemView){
            super(itemView);
            ivImage = itemView.findViewById(R.id.diary_image);
            tvTitle = itemView.findViewById(R.id.diary_title);
            tvDate = itemView.findViewById(R.id.diary_date);
            ivFavorite = itemView.findViewById(R.id.button_heart);
        }

    }

    public DiaryAdapter(Context context,ArrayList<Diary> diary_list){
        this.inflater = LayoutInflater.from(context);
        this.diaryArrayList = diary_list;
        MainActivity.favoriteList = new ArrayList<>();
        MainActivity.emojiList = new HashMap<>();
        for(int i=0;i<31;i++)
            MainActivity.emojiList.put(i+1,new ArrayList<Integer>());
        for(int i=0;i<diaryArrayList.size();i++){
            if(diaryArrayList.get(i).getFavorite()){
                MainActivity.favoriteList.add(i);
            }

            emoji = diaryArrayList.get(i).getImage();

            ArrayList<Integer> list = MainActivity.emojiList.get(emoji);
            if(list==null)
                list = new ArrayList<>();
            list.add(i);

            MainActivity.emojiList.put(emoji,list);
        }
    }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.diary_item,null,false);
        return new DiaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder diaryViewHolder, int position) {
        emoji = diaryArrayList.get(position).getImage();
        title = diaryArrayList.get(position).getTitle();
        date = diaryArrayList.get(position).getDate();
        content = diaryArrayList.get(position).getContent();

        switch (emoji){
            case 1: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji1); break;
            case 2: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji2); break;
            case 3: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji3); break;
            case 4: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji4); break;
            case 5: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji5); break;
            case 6: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji6); break;
            case 7: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji7); break;
            case 8: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji8); break;
            case 9: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji9); break;
            case 10: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji10); break;
            case 11: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji11); break;
            case 12: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji12); break;
            case 13: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji13); break;
            case 14: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji14); break;
            case 15: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji15); break;
            case 16: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji16); break;
            case 17: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji17); break;
            case 18: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji18); break;
            case 19: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji19); break;
            case 20: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji20); break;
            case 21: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji21); break;
            case 22: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji22); break;
            case 23: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji23); break;
            case 24: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji24); break;
            case 25: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji25); break;
            case 26: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji26); break;
            case 27: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji27); break;
            case 28: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji28); break;
            case 29: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji29); break;
            case 30: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji30); break;
            case 31: diaryViewHolder.ivImage.setImageResource(R.drawable.emoji31); break;
        }

        diaryViewHolder.tvTitle.setText(title);
        diaryViewHolder.tvDate.setText((date.getMonth()+1)+"월 "+date.getDate()+"일");
        if(diaryArrayList.get(position).getFavorite()){
            diaryViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite);
        }
        else
            diaryViewHolder.ivFavorite.setImageResource(R.drawable.ic_favorite_border_color);

    }

    @Override
    public int getItemCount() {
        return diaryArrayList.size();
    }
}
