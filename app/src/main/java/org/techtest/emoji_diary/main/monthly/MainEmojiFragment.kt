package org.techtest.emoji_diary.main.monthly

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.FavoriteAdapter
import org.techtest.emoji_diary.add.AddActivity
import org.techtest.emoji_diary.main.MainActivity
import java.util.*

class MainEmojiFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var diaryArrayList: ArrayList<Int>
    private lateinit var tvCount: TextView
    private lateinit var ivEmoji: ImageView
    private lateinit var txtCount: String
    private var position = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_emoji, container, false)
        position = arguments!!.getInt("position")
        val layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.diary_recycler_view)
        recyclerView.layoutManager = layoutManager
        diaryArrayList = MainActivity.emojiList[position + 1]!!
        tvCount = view.findViewById(R.id.txt_count)
        ivEmoji = view.findViewById(R.id.emoji_image)
        when (position + 1) {
            1 -> ivEmoji.setImageResource(R.drawable.emoji1)
            2 -> ivEmoji.setImageResource(R.drawable.emoji2)
            3 -> ivEmoji.setImageResource(R.drawable.emoji3)
            4 -> ivEmoji.setImageResource(R.drawable.emoji4)
            5 -> ivEmoji.setImageResource(R.drawable.emoji5)
            6 -> ivEmoji.setImageResource(R.drawable.emoji6)
            7 -> ivEmoji.setImageResource(R.drawable.emoji7)
            8 -> ivEmoji.setImageResource(R.drawable.emoji8)
            9 -> ivEmoji.setImageResource(R.drawable.emoji9)
            10 -> ivEmoji.setImageResource(R.drawable.emoji10)
            11 -> ivEmoji.setImageResource(R.drawable.emoji11)
            12 -> ivEmoji.setImageResource(R.drawable.emoji12)
            13 -> ivEmoji.setImageResource(R.drawable.emoji13)
            14 -> ivEmoji.setImageResource(R.drawable.emoji14)
            15 -> ivEmoji.setImageResource(R.drawable.emoji15)
            16 -> ivEmoji.setImageResource(R.drawable.emoji16)
            17 -> ivEmoji.setImageResource(R.drawable.emoji17)
            18 -> ivEmoji.setImageResource(R.drawable.emoji18)
            19 -> ivEmoji.setImageResource(R.drawable.emoji19)
            20 -> ivEmoji.setImageResource(R.drawable.emoji20)
            21 -> ivEmoji.setImageResource(R.drawable.emoji21)
            22 -> ivEmoji.setImageResource(R.drawable.emoji22)
            23 -> ivEmoji.setImageResource(R.drawable.emoji23)
            24 -> ivEmoji.setImageResource(R.drawable.emoji24)
            25 -> ivEmoji.setImageResource(R.drawable.emoji25)
            26 -> ivEmoji.setImageResource(R.drawable.emoji26)
            27 -> ivEmoji.setImageResource(R.drawable.emoji27)
            28 -> ivEmoji.setImageResource(R.drawable.emoji28)
            29 -> ivEmoji.setImageResource(R.drawable.emoji29)
            30 -> ivEmoji.setImageResource(R.drawable.emoji30)
            31 -> ivEmoji.setImageResource(R.drawable.emoji31)
        }
        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val intent = Intent(activity, AddActivity::class.java)
                        intent.putExtra("position", diaryArrayList[position])
                        startActivity(intent)
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete).setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                    if (viewID == R.id.button_heart) {
                        if (MainActivity.diaryArrayList[diaryArrayList[position]].favorite) {
                            MainActivity.diaryArrayList[diaryArrayList[position]].favorite = false
                            MainActivity.favoriteList.removeAt(MainActivity.favoriteList.indexOf(diaryArrayList[position]))
                        } else {
                            MainActivity.diaryArrayList[diaryArrayList[position]].favorite = false
                            MainActivity.favoriteList.add(diaryArrayList[position])
                        }
                        adapter = FavoriteAdapter(context, diaryArrayList)
                        recyclerView.adapter = adapter
                    } else if (viewID == R.id.button_delete) {
                        val diaryPosition = diaryArrayList[position]
                        MainActivity.diaryArrayList.removeAt(diaryPosition)
                        if (MainActivity.favoriteList.contains(diaryPosition)) MainActivity.favoriteList.removeAt(MainActivity.favoriteList.indexOf(diaryPosition))
                        diaryArrayList.removeAt(position)
                        adapter = FavoriteAdapter(context, diaryArrayList)
                        recyclerView.adapter = adapter
                        txtCount = "(" + diaryArrayList.size + ")"
                        tvCount.text = txtCount
                    }
                }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerView.addOnItemTouchListener(onTouchListener)
        diaryArrayList = MainActivity.emojiList[position + 1]!!
        adapter = FavoriteAdapter(context, diaryArrayList)
        recyclerView.adapter = adapter
        txtCount = "(" + diaryArrayList.size + ")"
        tvCount.text = txtCount
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnItemTouchListener(onTouchListener)
    }

    companion object {
        fun newInstance(param1: Int): MainEmojiFragment {
            val fragment = MainEmojiFragment()
            val args = Bundle()
            args.putInt("position", param1)
            fragment.arguments = args
            return fragment
        }
    }
}