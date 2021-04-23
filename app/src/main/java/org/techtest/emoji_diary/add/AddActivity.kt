package org.techtest.emoji_diary.add

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import org.techtest.emoji_diary.R
import org.techtest.emoji_diary.main.MainActivity
import org.techtest.emoji_diary.main.daily.MainDailyFragment
import org.techtest.emoji_diary.model.Diary
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var btnDone: Button
    private lateinit var btnBack: Button
    private lateinit var layoutEmoji: LinearLayout
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var ivEmoji: ImageView
    private lateinit var tvDate: TextView
    private var diary: Diary? = null
    private var position = 0
    private var emoji = 0
    private var favorite = false
    private var date: Date? = null
    private var datePicker: DatePicker? = null

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
        position = intent.getIntExtra("position", -1)
        date = MainDailyFragment.Companion.dateToday
        tvDate.text = date!!.year.toString() + "년 " + (date!!.month + 1) + "월 " + date!!.date + "일"
        if (position >= 0) {
            diary = MainActivity.diaryArrayList[position]
            date = diary!!.date
            tvDate.text = date!!.year.toString() + "년 " + (date!!.month + 1) + "월 " + date!!.date + "일"
            etTitle.setText(diary!!.title)
            etContent.setText(diary!!.content?.replace(" ", "\u00A0"))
            favorite = diary!!.favorite
            emoji = diary!!.image
            when (emoji) {
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
            view.findViewById<View>(R.id.emoji1).setOnClickListener {
                emoji = 1
                ivEmoji.setImageResource(R.drawable.emoji1)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji2).setOnClickListener {
                emoji = 2
                ivEmoji.setImageResource(R.drawable.emoji2)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji3).setOnClickListener {
                emoji = 3
                ivEmoji.setImageResource(R.drawable.emoji3)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji4).setOnClickListener {
                emoji = 4
                ivEmoji.setImageResource(R.drawable.emoji4)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji5).setOnClickListener {
                emoji = 5
                ivEmoji.setImageResource(R.drawable.emoji5)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji6).setOnClickListener {
                emoji = 6
                ivEmoji.setImageResource(R.drawable.emoji6)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji7).setOnClickListener {
                emoji = 7
                ivEmoji.setImageResource(R.drawable.emoji7)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji8).setOnClickListener {
                emoji = 8
                ivEmoji.setImageResource(R.drawable.emoji8)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji9).setOnClickListener {
                emoji = 9
                ivEmoji.setImageResource(R.drawable.emoji9)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji10).setOnClickListener {
                emoji = 10
                ivEmoji.setImageResource(R.drawable.emoji10)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji11).setOnClickListener {
                emoji = 11
                ivEmoji.setImageResource(R.drawable.emoji11)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji12).setOnClickListener {
                emoji = 12
                ivEmoji.setImageResource(R.drawable.emoji12)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji13).setOnClickListener {
                emoji = 13
                ivEmoji.setImageResource(R.drawable.emoji13)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji14).setOnClickListener {
                emoji = 14
                ivEmoji.setImageResource(R.drawable.emoji14)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji15).setOnClickListener {
                emoji = 15
                ivEmoji.setImageResource(R.drawable.emoji15)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji16).setOnClickListener {
                emoji = 16
                ivEmoji.setImageResource(R.drawable.emoji16)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji17).setOnClickListener {
                emoji = 17
                ivEmoji.setImageResource(R.drawable.emoji17)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji18).setOnClickListener {
                emoji = 18
                ivEmoji.setImageResource(R.drawable.emoji18)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji19).setOnClickListener {
                emoji = 19
                ivEmoji.setImageResource(R.drawable.emoji19)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji20).setOnClickListener {
                emoji = 20
                ivEmoji.setImageResource(R.drawable.emoji20)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji21).setOnClickListener {
                emoji = 21
                ivEmoji.setImageResource(R.drawable.emoji21)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji22).setOnClickListener {
                emoji = 22
                ivEmoji.setImageResource(R.drawable.emoji22)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji23).setOnClickListener {
                emoji = 23
                ivEmoji.setImageResource(R.drawable.emoji23)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji24).setOnClickListener {
                emoji = 24
                ivEmoji.setImageResource(R.drawable.emoji24)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji25).setOnClickListener {
                emoji = 25
                ivEmoji.setImageResource(R.drawable.emoji25)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji26).setOnClickListener {
                emoji = 26
                ivEmoji.setImageResource(R.drawable.emoji26)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji27).setOnClickListener {
                emoji = 27
                ivEmoji.setImageResource(R.drawable.emoji27)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji28).setOnClickListener {
                emoji = 28
                ivEmoji.setImageResource(R.drawable.emoji28)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji29).setOnClickListener {
                emoji = 29
                ivEmoji.setImageResource(R.drawable.emoji29)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji30).setOnClickListener {
                emoji = 30
                ivEmoji.setImageResource(R.drawable.emoji30)
                dialog.dismiss()
            }
            view.findViewById<View>(R.id.emoji31).setOnClickListener {
                emoji = 31
                ivEmoji.setImageResource(R.drawable.emoji31)
                dialog.dismiss()
            }
            dialog.show()
        }
        btnDone.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()
            when {
                title.trim { it <= ' ' } == "" -> etTitle.error = "제목을 입력하세요."
                content.trim { it <= ' ' } == "" -> etContent.error = "내용을 입력하세요."
                else -> {
                    if (position == -1) MainActivity.diaryArrayList.add(0, Diary(emoji, title, date!!, content, favorite)) else {
                        diary?.date = date as Date
                        diary?.content = content
                        diary?.title = title
                        diary?.image = emoji
                        diary?.favorite = favorite
                        diary?.let { it1 -> MainActivity.diaryArrayList.set(position, it1) }
                    }
                    finish()
                }
            }
        }
        btnBack.setOnClickListener { finish() }
    }
}