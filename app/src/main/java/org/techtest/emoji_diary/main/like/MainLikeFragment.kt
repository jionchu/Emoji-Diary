package org.techtest.emoji_diary.main.like

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.FavoriteAdapter
import org.techtest.emoji_diary.add.AddActivity
import org.techtest.emoji_diary.main.MainActivity
import java.util.*

class MainLikeFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var onTouchListener: RecyclerTouchListener
    private var favoriteList: ArrayList<Int>? = null
    private lateinit var tvCount: TextView
    private lateinit var txtCount: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_like, container, false)
        val layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.favorite_recycler_view)
        recyclerView.layoutManager = layoutManager
        favoriteList = MainActivity.favoriteList
        tvCount = view.findViewById(R.id.txt_count)
        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val intent = Intent(activity, AddActivity::class.java)
                        intent.putExtra("position", favoriteList!![position])
                        startActivity(intent)
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete).setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                    if (viewID == R.id.button_heart) {
                        MainActivity.diaryArrayList[favoriteList!![position]].favorite = false
                        favoriteList!!.removeAt(position)
                        adapter = FavoriteAdapter(context, favoriteList!!)
                        recyclerView.adapter = adapter
                        txtCount = "(" + favoriteList!!.size + ")"
                        tvCount.text = txtCount
                    } else if (viewID == R.id.button_delete) {
                        val diaryPosition = favoriteList!![position]
                        MainActivity.diaryArrayList.removeAt(diaryPosition)
                        favoriteList!!.removeAt(position)
                        MainActivity.favoriteList = ArrayList<Int>()
                        for (i in MainActivity.diaryArrayList.indices) {
                            if (MainActivity.diaryArrayList[i].favorite) {
                                MainActivity.favoriteList.add(i)
                            }
                        }
                        favoriteList = MainActivity.favoriteList
                        adapter = FavoriteAdapter(context, favoriteList!!)
                        recyclerView.adapter = adapter
                        txtCount = "(" + favoriteList!!.size + ")"
                        tvCount.text = txtCount
                    }
                }
        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerView.addOnItemTouchListener(onTouchListener)
        adapter = FavoriteAdapter(context, favoriteList!!)
        recyclerView.adapter = adapter
        txtCount = "(" + favoriteList!!.size + ")"
        tvCount.text = txtCount
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnItemTouchListener(onTouchListener)
    }
}