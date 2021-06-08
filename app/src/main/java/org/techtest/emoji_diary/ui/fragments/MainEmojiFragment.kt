package org.techtest.emoji_diary.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.ui.adapters.DiaryAdapter
import org.techtest.emoji_diary.ui.AddActivity
import org.techtest.emoji_diary.database.entity.DiaryEntity
import org.techtest.emoji_diary.viewmodel.DiaryViewModel
import org.techtest.emoji_diary.viewmodel.DiaryViewModelFactory

class MainEmojiFragment : androidx.fragment.app.Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mOnTouchListener: RecyclerTouchListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_emoji, container, false)

        val tvCount: TextView = view.findViewById(R.id.txt_count)
        val ivEmoji: ImageView = view.findViewById(R.id.emoji_image)
        ivEmoji.setImageResource(arguments!!.getInt("emojiRes"))
        val emojiId = arguments!!.getInt("emojiId")

        val diaryAdapter = DiaryAdapter()
        mRecyclerView = view.findViewById(R.id.diary_recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = diaryAdapter

        val factory = DiaryViewModelFactory(
                MyApplication.sRepository!!
        )
        val diaryViewModel: DiaryViewModel = ViewModelProvider(this, factory).get(DiaryViewModel::class.java)
        diaryViewModel.loadByEmoji(emojiId).observe(viewLifecycleOwner) { diaries ->
            run {
                diaries.let { diaryAdapter.submitList(it) }
                val strCount = "(${diaries.size})"
                tvCount.text = strCount
            }
        }

        mOnTouchListener = RecyclerTouchListener(activity, mRecyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val intent = Intent(activity, AddActivity::class.java)
                        intent.putExtra("diaryId", diaryViewModel.emojiDiaries.value!![position].id)
                        startActivity(intent)
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                }).setSwipeOptionViews(R.id.button_heart, R.id.button_delete).setSwipeable(R.id.item_fg, R.id.item_bg) { viewID, position ->
                    val original: DiaryEntity = diaryViewModel.allDiaries.value!![position]
                    if (viewID == R.id.button_heart) {
                        original.like = !original.like
                        diaryViewModel.update(original)
                    } else if (viewID == R.id.button_delete) {
                        MyApplication.sInstance!!.diaryDao().deleteDiary(original)
                        MyApplication.sInstance!!.emojiDao().decreaseCount(original.emojiId)
                    }
                }

        return view
    }

    override fun onResume() {
        super.onResume()
        mRecyclerView.addOnItemTouchListener(mOnTouchListener)
    }

    override fun onPause() {
        super.onPause()
        mRecyclerView.removeOnItemTouchListener(mOnTouchListener)
    }

    companion object {
        fun newInstance(param1: Int, param2: Int): MainEmojiFragment {
            val fragment = MainEmojiFragment()
            val args = Bundle()
            args.putInt("emojiId", param1)
            args.putInt("emojiRes", param2)
            fragment.arguments = args
            return fragment
        }
    }
}