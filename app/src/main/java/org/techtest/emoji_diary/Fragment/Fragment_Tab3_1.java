package org.techtest.emoji_diary.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import org.techtest.emoji_diary.Adapter.EmojiAdapter;
import org.techtest.emoji_diary.MainActivity;
import org.techtest.emoji_diary.R;

import java.util.ArrayList;
import java.util.Map;

public class Fragment_Tab3_1 extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerTouchListener onTouchListener;
    private Map<Integer,ArrayList<Integer>> emojiList;
    private FragmentTransaction transaction;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment__tab3_1, container, false);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),6);
        recyclerView = view.findViewById(R.id.emoji_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        emojiList = MainActivity.emojiList;

        onTouchListener = new RecyclerTouchListener(getActivity(),recyclerView)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Fragment_Tab3_2 fragment_tab3_2 = new Fragment_Tab3_2().newInstance(position);
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, fragment_tab3_2).addToBackStack("tab3");

                        transaction.commit();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {

                    }
                });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener);

        adapter = new EmojiAdapter(getContext(),emojiList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }
}
