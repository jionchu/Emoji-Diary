package org.techtest.emoji_diary.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.techtest.emoji_diary.Adapter.FavoriteAdapter;
import org.techtest.emoji_diary.AddDiaryActivity;
import org.techtest.emoji_diary.MainActivity;
import org.techtest.emoji_diary.R;

import java.util.ArrayList;

public class Fragment_Tab3_2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerTouchListener onTouchListener;
    private ArrayList<Integer> diaryArrayList;
    private TextView tvCount;
    private ImageView ivEmoji;
    private String txtCount;
    private int position;

    public Fragment_Tab3_2() {
        // Required empty public constructor
    }

    public static Fragment_Tab3_2 newInstance(int param1) {
        Fragment_Tab3_2 fragment = new Fragment_Tab3_2();
        Bundle args = new Bundle();
        args.putInt("position", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment__tab3_2, container, false);

        position = getArguments().getInt("position");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.diary_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        diaryArrayList = MainActivity.emojiList.get(position+1);
        tvCount = view.findViewById(R.id.txt_count);
        ivEmoji = view.findViewById(R.id.emoji_image);

        switch (position+1){
            case 1: ivEmoji.setImageResource(R.drawable.emoji1); break;
            case 2: ivEmoji.setImageResource(R.drawable.emoji2); break;
            case 3: ivEmoji.setImageResource(R.drawable.emoji3); break;
            case 4: ivEmoji.setImageResource(R.drawable.emoji4); break;
            case 5: ivEmoji.setImageResource(R.drawable.emoji5); break;
            case 6: ivEmoji.setImageResource(R.drawable.emoji6); break;
            case 7: ivEmoji.setImageResource(R.drawable.emoji7); break;
            case 8: ivEmoji.setImageResource(R.drawable.emoji8); break;
            case 9: ivEmoji.setImageResource(R.drawable.emoji9); break;
            case 10: ivEmoji.setImageResource(R.drawable.emoji10); break;
            case 11: ivEmoji.setImageResource(R.drawable.emoji11); break;
            case 12: ivEmoji.setImageResource(R.drawable.emoji12); break;
            case 13: ivEmoji.setImageResource(R.drawable.emoji13); break;
            case 14: ivEmoji.setImageResource(R.drawable.emoji14); break;
            case 15: ivEmoji.setImageResource(R.drawable.emoji15); break;
            case 16: ivEmoji.setImageResource(R.drawable.emoji16); break;
            case 17: ivEmoji.setImageResource(R.drawable.emoji17); break;
            case 18: ivEmoji.setImageResource(R.drawable.emoji18); break;
            case 19: ivEmoji.setImageResource(R.drawable.emoji19); break;
            case 20: ivEmoji.setImageResource(R.drawable.emoji20); break;
            case 21: ivEmoji.setImageResource(R.drawable.emoji21); break;
            case 22: ivEmoji.setImageResource(R.drawable.emoji22); break;
            case 23: ivEmoji.setImageResource(R.drawable.emoji23); break;
            case 24: ivEmoji.setImageResource(R.drawable.emoji24); break;
            case 25: ivEmoji.setImageResource(R.drawable.emoji25); break;
            case 26: ivEmoji.setImageResource(R.drawable.emoji26); break;
            case 27: ivEmoji.setImageResource(R.drawable.emoji27); break;
            case 28: ivEmoji.setImageResource(R.drawable.emoji28); break;
            case 29: ivEmoji.setImageResource(R.drawable.emoji29); break;
            case 30: ivEmoji.setImageResource(R.drawable.emoji30); break;
            case 31: ivEmoji.setImageResource(R.drawable.emoji31); break;
        }

        onTouchListener = new RecyclerTouchListener(getActivity(),recyclerView)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Intent intent = new Intent(getActivity(),AddDiaryActivity.class);
                        intent.putExtra("position",diaryArrayList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                }).setSwipeOptionViews(R.id.button_heart,R.id.button_delete).setSwipeable(R.id.item_fg,R.id.item_bg,new RecyclerTouchListener.OnSwipeOptionsClickListener(){
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.button_heart){
                            if(MainActivity.diaryArrayList.get(diaryArrayList.get(position)).getFavorite()) {
                                MainActivity.diaryArrayList.get(diaryArrayList.get(position)).setFavorite(false);
                                MainActivity.favoriteList.remove(MainActivity.favoriteList.indexOf(diaryArrayList.get(position)));
                            }
                            else {
                                MainActivity.diaryArrayList.get(diaryArrayList.get(position)).setFavorite(false);
                                MainActivity.favoriteList.add(diaryArrayList.get(position));
                            }

                            adapter = new FavoriteAdapter(getContext(),diaryArrayList);
                            recyclerView.setAdapter(adapter);
                        } else if (viewID == R.id.button_delete){
                            int diary_position = diaryArrayList.get(position);
                            MainActivity.diaryArrayList.remove(diary_position);
                            if (MainActivity.favoriteList.contains(diary_position))
                                MainActivity.favoriteList.remove(MainActivity.favoriteList.indexOf(diary_position));
                            diaryArrayList.remove(position);

                            adapter = new FavoriteAdapter(getContext(),diaryArrayList);
                            recyclerView.setAdapter(adapter);
                            txtCount = "("+diaryArrayList.size()+")";
                            tvCount.setText(txtCount);
                        }
                    }
                });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener);

        diaryArrayList = MainActivity.emojiList.get(position+1);
        adapter = new FavoriteAdapter(getContext(),diaryArrayList);
        recyclerView.setAdapter(adapter);

        txtCount = "("+diaryArrayList.size()+")";
        tvCount.setText(txtCount);
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }
}