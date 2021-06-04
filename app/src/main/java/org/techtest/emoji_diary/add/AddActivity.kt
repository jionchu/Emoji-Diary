package org.techtest.emoji_diary.add

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener
import org.techtest.emoji_diary.MyApplication
import org.techtest.emoji_diary.MyApplication.Companion.dateFormat
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.adapter.EmojiDialogAdapter
import org.techtest.emoji_diary.database.Diary
import org.techtest.emoji_diary.database.Emoji
import org.techtest.emoji_diary.main.daily.MainDailyFragment
import org.techtest.emoji_diary.viewmodel.EmojiViewModel
import org.techtest.emoji_diary.viewmodel.EmojiViewModelFactory
import java.util.*
import java.util.concurrent.Executors

class AddActivity : AppCompatActivity() {

    private lateinit var btnDone: Button
    private lateinit var btnBack: Button
    private lateinit var layoutEmoji: LinearLayout
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var ivEmoji: ImageView
    private lateinit var tvDate: TextView
    private var diary: Diary? = null
    private var emojiId = 1
    private var image = R.drawable.emoji1
    private var date: Date? = null
    private var datePicker: DatePicker? = null
    private lateinit var mEmojiViewModel: EmojiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary)
        btnDone = findViewById(R.id.button_done)
        btnBack = findViewById(R.id.button_back)
        layoutEmoji = findViewById(R.id.emoji_layout)
        etTitle = findViewById(R.id.txt_title)
        etContent = findViewById(R.id.txt_content)
        ivEmoji = findViewById(R.id.img_emoji)
        tvDate = findViewById(R.id.txt_date)
        val diaryId = intent.getIntExtra("diaryId", -1)

        date = MainDailyFragment.dateToday
        tvDate.text = MyApplication.dateStrFormat.format(date)

        if (diaryId != -1) {
            Executors.newSingleThreadExecutor().execute {
                diary = MyApplication.sRepository!!.loadDiary(diaryId)
                etTitle.setText(diary!!.title)
                etContent.setText(diary!!.content)
                emojiId = diary!!.emojiId
                image = diary!!.emojiRes
                ivEmoji.setImageResource(image)
                tvDate.text = diary!!.date
            }
        }

        tvDate.setOnClickListener {
            val datepickerBuilder = AlertDialog.Builder(this@AddActivity)
            val datepickerView = LayoutInflater.from(applicationContext).inflate(R.layout.date_picker_dialog, null, false)
            datepickerBuilder.setView(datepickerView)
            val datepickerDialog = datepickerBuilder.create()
            datePicker = datepickerView.findViewById<View>(R.id.date_picker) as DatePicker
            datepickerView.findViewById<View>(R.id.datepicker_dialog_submit).setOnClickListener {
                val year = datePicker!!.year
                val month = datePicker!!.month
                val day = datePicker!!.dayOfMonth
                date!!.year = year
                date!!.date = day
                date!!.month = month
                tvDate.text = year.toString() + "년 " + (month + 1) + "월 " + day + "일"
                datepickerDialog.dismiss()
            }
            datepickerView.findViewById<View>(R.id.button_close).setOnClickListener { datepickerDialog.dismiss() }
            datepickerDialog.show()
        }

        layoutEmoji.setOnClickListener {
            val builder = AlertDialog.Builder(this@AddActivity)
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.emoji_dialog, null, false)
            builder.setView(view)
            val dialog = builder.create()
            val recyclerView: RecyclerView = view.findViewById(R.id.emoji_dialog_recycler_view)
            recyclerView.layoutManager = GridLayoutManager(this, 6)
            val dialogAdapter = EmojiDialogAdapter()
            recyclerView.adapter = dialogAdapter

            val factory = EmojiViewModelFactory(
                    MyApplication.sRepository!!
            )
            mEmojiViewModel = ViewModelProvider(this, factory).get(EmojiViewModel::class.java)
            mEmojiViewModel.allEmojis.observe(this) { emojis ->
                run {
                    emojis.let { dialogAdapter.submitList(it) }
                }
            }
            recyclerView.addOnItemTouchListener(RecyclerTouchListener(this, recyclerView)
                    .setClickable(object : RecyclerTouchListener.OnRowClickListener {
                        override fun onRowClicked(position: Int) {
                            val emoji: Emoji = mEmojiViewModel.allEmojis.value!![position]
                            emojiId = emoji.id
                            image = emoji.image
                            ivEmoji.setImageResource(image)
                            dialog.dismiss()
                        }
                        override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                    }))

            dialog.show()
        }

        btnDone.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()
            when {
                title.trim { it <= ' ' } == "" -> etTitle.error = "제목을 입력하세요."
                content.trim { it <= ' ' } == "" -> etContent.error = "내용을 입력하세요."
                else -> {
                    if (diaryId == -1) {
                        MyApplication.sInstance!!.diaryDao().insertDiary(Diary(dateFormat.format(date).toString(), emojiId, image, title, content, false))
                        MyApplication.sInstance!!.emojiDao().increaseCount(emojiId)
                    } else {
                        MyApplication.sInstance!!.emojiDao().decreaseCount(diary!!.emojiId)
                        diary!!.title = title
                        diary!!.content = content
                        diary!!.emojiId = emojiId
                        diary!!.emojiRes = image
                        diary!!.date = dateFormat.format(date)
                        MyApplication.sInstance!!.diaryDao().updateDiary(diary!!)
                        MyApplication.sInstance!!.emojiDao().increaseCount(emojiId)
                    }
                    finish()
                }
            }
        }

        btnBack.setOnClickListener { finish() }
    }
}