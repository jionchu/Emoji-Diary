package org.techtest.emoji_diary.Fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.techtest.emoji_diary.AddDiaryActivity;
import org.techtest.emoji_diary.Adapter.FavoriteAdapter;
import org.techtest.emoji_diary.MainActivity;
import org.techtest.emoji_diary.R;

import java.util.ArrayList;

public class Fragment_Tab2 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerTouchListener onTouchListener;
    private ArrayList<Integer> favoriteList;
    private TextView tvCount;
    private String txtCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment__tab2, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = view.findViewById(R.id.favorite_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        favoriteList = MainActivity.favoriteList;
        tvCount = view.findViewById(R.id.txt_count);

        onTouchListener = new RecyclerTouchListener(getActivity(),recyclerView)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Intent intent = new Intent(getActivity(),AddDiaryActivity.class);
                        intent.putExtra("position",favoriteList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                }).setSwipeOptionViews(R.id.button_heart,R.id.button_delete).setSwipeable(R.id.item_fg,R.id.item_bg,new RecyclerTouchListener.OnSwipeOptionsClickListener(){
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.button_heart){
                            MainActivity.diaryArrayList.get(favoriteList.get(position))
                                    .setFavorite(false);
                            favoriteList.remove(position);

                            adapter = new FavoriteAdapter(getContext(),favoriteList);
                            recyclerView.setAdapter(adapter);
                            txtCount = "("+favoriteList.size()+")";
                            tvCount.setText(txtCount);
                        } else if (viewID == R.id.button_delete){
                            int diary_position = favoriteList.get(position);
                            MainActivity.diaryArrayList.remove(diary_position);
                            favoriteList.remove(position);

                            MainActivity.favoriteList = new ArrayList<>();
                            for(int i=0;i<MainActivity.diaryArrayList.size();i++) {
                                if (MainActivity.diaryArrayList.get(i).getFavorite()) {
                                    MainActivity.favoriteList.add(i);
                                }
                            }

                            favoriteList = MainActivity.favoriteList;

                            adapter = new FavoriteAdapter(getContext(),favoriteList);
                            recyclerView.setAdapter(adapter);
                            txtCount = "("+favoriteList.size()+")";
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

        adapter = new FavoriteAdapter(getContext(),favoriteList);
        recyclerView.setAdapter(adapter);

        txtCount = "("+favoriteList.size()+")";
        tvCount.setText(txtCount);
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }
}
