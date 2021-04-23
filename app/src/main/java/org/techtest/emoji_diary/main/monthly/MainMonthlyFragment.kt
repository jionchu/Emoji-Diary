package org.techtest.emoji_diary.main.monthly

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener.OnRowClickListener
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.EmojiAdapter
import org.techtest.emoji_diary.main.MainActivity
import java.util.*

class MainMonthlyFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var onTouchListener: RecyclerTouchListener
    private lateinit var emojiList: Map<Int, ArrayList<Int>>
    private lateinit var transaction: FragmentTransaction

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_monthly, container, false)
        val layoutManager = GridLayoutManager(activity, 6)
        recyclerView = view.findViewById(R.id.emoji_recycler_view)
        recyclerView.layoutManager = layoutManager
        emojiList = MainActivity.emojiList
        onTouchListener = RecyclerTouchListener(activity, recyclerView)
                .setClickable(object : OnRowClickListener {
                    override fun onRowClicked(position: Int) {
                        val mainEmojiFragment: MainEmojiFragment = MainEmojiFragment.newInstance(position)
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
        adapter = EmojiAdapter(context, emojiList)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        recyclerView.removeOnItemTouchListener(onTouchListener)
    }
}