package org.techtest.emoji_diary.main.monthly

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.EmojiAdapter
import org.techtest.emoji_diary.viewmodel.EmojiViewModel
import org.techtest.emoji_diary.viewmodel.EmojiViewModelFactory

class MainMonthlyFragment : androidx.fragment.app.Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: EmojiAdapter
    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var transaction: FragmentTransaction
    private lateinit var mEmojiViewModel: EmojiViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_monthly, container, false)

        recyclerView = view.findViewById(R.id.emoji_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 6)
        mAdapter = EmojiAdapter()
        recyclerView.adapter = mAdapter

        val factory = EmojiViewModelFactory(
                MyApplication.sRepository!!
        )
        mEmojiViewModel = ViewModelProvider(this, factory).get(EmojiViewModel::class.java)
        mEmojiViewModel.allEmojis.observe(viewLifecycleOwner) { emojis ->
            run {
                emojis.let { mAdapter.submitList(it) }
            }
        }

        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val mainEmojiFragment: MainEmojiFragment = MainEmojiFragment
                                .newInstance(
                                        mEmojiViewModel.allEmojis.value!![position].id,
                                        mEmojiViewModel.allEmojis.value!![position].image
                                )
                        transaction = activity!!.supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.container, mainEmojiFragment).addToBackStack("tab3")
                        transaction.commit()
                    }

                    override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                })
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
}