package org.techtest.emoji_diary.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.database.entity.DiaryEntity
import org.techtest.emoji_diary.databinding.FragmentDailyBinding
import org.techtest.emoji_diary.ui.adapters.DiaryAdapter
import org.techtest.emoji_diary.ui.AddActivity
import java.util.*

class MainDailyFragment : androidx.fragment.app.Fragment() {

    private lateinit var mDiaryAdapter: DiaryAdapter
    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var diaryViewModel: MainViewModel
    private lateinit var binding: FragmentDailyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily, container, false)
        mDiaryAdapter = DiaryAdapter()
        binding.dailyRecyclerview.adapter = mDiaryAdapter

        diaryViewModel = ViewModelProvider(
            this, MainViewModelFactory(
                MyApplication.sRepository!!
            )
        ).get(MainViewModel::class.java)

        // 현재 날짜 설정
        binding.dailyTvDate.text = MyApplication.dateStrFormat.format(Date())

        diaryViewModel.loadAllDiaries()
        diaryViewModel.diaries.observe(viewLifecycleOwner) { diaries ->
            run {
                diaries.let { mDiaryAdapter.submitList(it) }
            }
        }
        diaryViewModel.isEmpty.observe(viewLifecycleOwner) {
            binding.dailyTvDefault.isVisible = it
        }

        onTouchListener = RecyclerTouchListener(activity, binding.dailyRecyclerview)
            .setClickable(object : RecyclerTouchListener.OnRowClickListener {
                override fun onRowClicked(position: Int) {
                    val intent = Intent(activity, AddActivity::class.java)
                    intent.putExtra("diaryId", diaryViewModel.diaries.value!![position].id)
                    startActivity(intent)
                }

                override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
            }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete)
            .setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                val original: DiaryEntity = diaryViewModel.diaries.value!![position]
                when (viewID) {
                    R.id.button_heart -> {
                        original.like = !original.like
                        diaryViewModel.updateDiary(original)
                        diaryViewModel.loadAllDiaries()
                    }
                    R.id.button_delete -> {
                        diaryViewModel.deleteDiary(original)
                        diaryViewModel.loadAllDiaries()
                    }
                }
            }

        binding.dailyFab.attachToRecyclerView(binding.dailyRecyclerview)
        binding.dailyFab.setOnClickListener {
            val intent = Intent(activity, AddActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.dailyRecyclerview.addOnItemTouchListener(onTouchListener)
    }

    override fun onPause() {
        super.onPause()
        binding.dailyRecyclerview.removeOnItemTouchListener(onTouchListener)
    }
}