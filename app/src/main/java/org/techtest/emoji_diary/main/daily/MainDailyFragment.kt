package org.techtest.emoji_diary.main.daily

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.melnykov.fab.FloatingActionButton
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.DiaryAdapter
import org.techtest.emoji_diary.add.AddActivity
import org.techtest.emoji_diary.main.MainActivity
import java.util.*

class MainDailyFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var onTouchListener: RecyclerTouchListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_daily, container, false)

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        val tvDate: TextView = view.findViewById(R.id.txt_main_date)
        val tvDefault: TextView = view.findViewById(R.id.tv_default)
        recyclerView = view.findViewById(R.id.diary_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val calender = Calendar.getInstance()
        val year = calender[Calendar.YEAR]
        val month = calender[Calendar.MONTH]
        val day = calender[Calendar.DAY_OF_MONTH]
        dateToday = Date(year, month, day)

        val strDate = "${year}년 ${month+1}월 ${day}일"
        tvDate.text = strDate

        if (MainActivity.diaryArrayList.size != 0) tvDefault.visibility = View.GONE
        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val intent = Intent(activity, AddActivity::class.java)
                        intent.putExtra("position", position)
                        startActivity(intent)
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete).setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                    if (viewID == R.id.button_heart) {
                        MainActivity.diaryArrayList[position].favorite = !MainActivity.diaryArrayList[position].favorite
                        adapter = DiaryAdapter(context, MainActivity.diaryArrayList)
                        recyclerView.adapter = adapter
                    } else if (viewID == R.id.button_delete) {
                        MainActivity.diaryArrayList.removeAt(position)
                        adapter = DiaryAdapter(context, MainActivity.diaryArrayList)
                        recyclerView.adapter = adapter
                    }
                }

        fab.attachToRecyclerView(recyclerView)
        fab.setOnClickListener {
            val intent = Intent(activity, AddActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        recyclerView.addOnItemTouchListener(onTouchListener)
        adapter = DiaryAdapter(context, MainActivity.diaryArrayList)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnItemTouchListener(onTouchListener)
    }

    companion object {
        var dateToday: Date? = null
    }
}