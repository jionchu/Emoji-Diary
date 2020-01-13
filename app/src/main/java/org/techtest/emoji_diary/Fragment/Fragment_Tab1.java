package org.techtest.emoji_diary.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.techtest.emoji_diary.AddDiaryActivity;
import org.techtest.emoji_diary.Adapter.DiaryAdapter;
import org.techtest.emoji_diary.MainActivity;
import org.techtest.emoji_diary.R;

import java.util.Calendar;
import java.util.Date;

public class Fragment_Tab1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private com.melnykov.fab.FloatingActionButton fab;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private TextView tvDate;
    public static Date dateToday;
    private TextView tvDefault;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment__tab1, container, false);

        fab = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.diary_recycler_view);
        tvDate = view.findViewById(R.id.txt_main_date);
        tvDefault = view.findViewById(R.id.tv_default);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        dateToday = new Date(year,month,day);
        tvDate.setText(year+"년 "+(month+1)+"월 "+day+"일");

        if(MainActivity.diaryArrayList.size()!=0)
            tvDefault.setVisibility(View.GONE);

        onTouchListener = new RecyclerTouchListener(getActivity(),recyclerView)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Intent intent = new Intent(getActivity(),AddDiaryActivity.class);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                }).setSwipeOptionViews(R.id.button_heart,R.id.button_delete).setSwipeable(R.id.item_fg,R.id.item_bg,new RecyclerTouchListener.OnSwipeOptionsClickListener(){
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.button_heart){
                            MainActivity.diaryArrayList.get(position).setFavorite(!MainActivity.diaryArrayList.get(position).getFavorite());

                            adapter = new DiaryAdapter(getContext(),MainActivity.diaryArrayList);
                            recyclerView.setAdapter(adapter);
                        } else if (viewID == R.id.button_delete){
                            MainActivity.diaryArrayList.remove(position);

                            adapter = new DiaryAdapter(getContext(),MainActivity.diaryArrayList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });

        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddDiaryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener);

        adapter = new DiaryAdapter(getContext(),MainActivity.diaryArrayList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }

}
