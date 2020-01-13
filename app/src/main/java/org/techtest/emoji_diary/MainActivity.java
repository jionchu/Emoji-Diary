package org.techtest.emoji_diary;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.techtest.emoji_diary.Fragment.Fragment_Tab1;
import org.techtest.emoji_diary.Fragment.Fragment_Tab2;
import org.techtest.emoji_diary.Fragment.Fragment_Tab3_1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnTab1,btnTab2,btnTab3;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ImageView ivTab1,ivTab2,ivTab3;
    private TextView tvTab1,tvTab2,tvTab3;
    public static ArrayList<Diary> diaryArrayList;
    public static ArrayList<Integer> favoriteList;
    public static Map<Integer,ArrayList<Integer>> emojiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryArrayList = new ArrayList<>();

        favoriteList = new ArrayList<>();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        Fragment_Tab1 fragment1 = new Fragment_Tab1();
        transaction.replace(R.id.container,fragment1);
        transaction.commit();

        btnTab1 = findViewById(R.id.button_tab1);
        btnTab2 = findViewById(R.id.button_tab2);
        btnTab3 = findViewById(R.id.button_tab3);

        ivTab1 = findViewById(R.id.img_tab1);
        ivTab2 = findViewById(R.id.img_tab2);
        ivTab3 = findViewById(R.id.img_tab3);

        tvTab1 = findViewById(R.id.txt_tab1);
        tvTab2 = findViewById(R.id.txt_tab2);
        tvTab3 = findViewById(R.id.txt_tab3);

        btnTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }

                ivTab1.setImageResource(R.drawable.ic_list);
                tvTab1.setTextColor(Color.rgb(255,255,255));
                ivTab2.setImageResource(R.drawable.ic_favorite_border_unclicked);
                tvTab2.setTextColor(Color.rgb(137,137,137));
                ivTab3.setImageResource(R.drawable.ic_trending_up_unclicked);
                tvTab3.setTextColor(Color.rgb(137,137,137));

                Fragment_Tab1 fragment1 = new Fragment_Tab1();
                transaction.replace(R.id.container,fragment1);
                transaction.commit();
            }
        });
        btnTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }

                ivTab1.setImageResource(R.drawable.ic_list_unclicked);
                tvTab1.setTextColor(Color.rgb(137,137,137));
                ivTab2.setImageResource(R.drawable.ic_favorite_border);
                tvTab2.setTextColor(Color.rgb(255,255,255));
                ivTab3.setImageResource(R.drawable.ic_trending_up_unclicked);
                tvTab3.setTextColor(Color.rgb(137,137,137));

                Fragment_Tab2 fragment2 = new Fragment_Tab2();
                transaction.replace(R.id.container,fragment2);
                transaction.commit();
            }
        });
        btnTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = fragmentManager.beginTransaction();
                for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }

                ivTab1.setImageResource(R.drawable.ic_list_unclicked);
                tvTab1.setTextColor(Color.rgb(137,137,137));
                ivTab2.setImageResource(R.drawable.ic_favorite_border_unclicked);
                tvTab2.setTextColor(Color.rgb(137,137,137));
                ivTab3.setImageResource(R.drawable.ic_trending_up);
                tvTab3.setTextColor(Color.rgb(255,255,255));

                Fragment_Tab3_1 fragment3 = new Fragment_Tab3_1();
                transaction.replace(R.id.container,fragment3);
                transaction.commit();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        favoriteList = new ArrayList<>();
        emojiList = new HashMap<>();

        for(int i=0;i<31;i++)
            emojiList.put(i+1,new ArrayList<Integer>());
        for(int i=0;i<diaryArrayList.size();i++){
            if(diaryArrayList.get(i).getFavorite()){
                favoriteList.add(i);
            }

            int emoji = diaryArrayList.get(i).getImage();

            ArrayList<Integer> list = emojiList.get(emoji);
            if(list==null)
                list = new ArrayList<>();
            list.add(i);

            emojiList.put(emoji,list);
        }
    }
}
