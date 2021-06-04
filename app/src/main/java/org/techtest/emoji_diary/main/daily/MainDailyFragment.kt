package org.techtest.emoji_diary.main.daily

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.melnykov.fab.FloatingActionButton
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.DiaryAdapter
import org.techtest.emoji_diary.add.AddActivity
import org.techtest.emoji_diary.database.Diary
import org.techtest.emoji_diary.viewmodel.DiaryViewModel
import org.techtest.emoji_diary.viewmodel.DiaryViewModelFactory
import java.util.*

class MainDailyFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mDiaryAdapter: DiaryAdapter
    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_daily, container, false)

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        val tvDate: TextView = view.findViewById(R.id.txt_main_date)
        val tvDefault: TextView = view.findViewById(R.id.tv_default)
        recyclerView = view.findViewById(R.id.diary_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mDiaryAdapter = DiaryAdapter()
        recyclerView.adapter = mDiaryAdapter

        val factory = DiaryViewModelFactory(
                MyApplication.sRepository!!
        )
        diaryViewModel = ViewModelProvider(this, factory).get(DiaryViewModel::class.java)
        diaryViewModel.allDiaries.observe(viewLifecycleOwner) { diaries ->
            run {
                diaries.let { mDiaryAdapter.submitList(it) }
            }
        }

        dateToday = Date()

        val strDate = "${dateToday!!.year}년 ${dateToday!!.month+1}월 ${dateToday!!.day}일"
        tvDate.text = MyApplication.dateStrFormat.format(dateToday)

        if (MyApplication.sInstance!!.diaryDao().getRowCount() > 0)
            tvDefault.visibility = View.GONE

        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : RecyclerTouchListener.OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val intent = Intent(activity, AddActivity::class.java)
                        intent.putExtra("diaryId", diaryViewModel.allDiaries.value!![position].id)
                        startActivity(intent)
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete).setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                    val original: Diary = diaryViewModel.allDiaries.value!![position]
                    if (viewID == R.id.button_heart) {
                        original.like = !original.like
                        diaryViewModel.update(original)
                    } else if (viewID == R.id.button_delete) {
                        MyApplication.sInstance!!.diaryDao().deleteDiary(original)
                        MyApplication.sInstance!!.emojiDao().decreaseCount(original.emojiId)
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
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnItemTouchListener(onTouchListener)
    }

    companion object {
        var dateToday: Date? = null
    }
}