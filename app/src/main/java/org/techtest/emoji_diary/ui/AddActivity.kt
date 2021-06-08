package org.techtest.emoji_diary.ui

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
import org.techtest.emoji_diary.ui.adapters.EmojiDialogAdapter
import org.techtest.emoji_diary.database.entity.DiaryEntity
import org.techtest.emoji_diary.database.entity.EmojiEntity
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
    private var diary: DiaryEntity? = null
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

        date = Date()
        tvDate.text = MyApplication.dateStrFormat.format(date)

        //리스트의 일기를 선택해 들어온 경우
        if (diaryId != -1) {
            //해당 인덱스의 일기 데이터 불러오기
            Executors.newSingleThreadExecutor().execute {
                diary = MyApplication.sRepository!!.loadDiary(diaryId)
                etTitle.setText(diary!!.title)
                etContent.setText(diary!!.content)
                emojiId = diary!!.emojiId
                image = diary!!.emojiRes
                ivEmoji.setImageResource(image)
                date = dateFormat.parse(diary!!.date)
                tvDate.text = MyApplication.dateStrFormat.format(date)
            }
        }


        //날짜 클릭 시
        tvDate.setOnClickListener {
            //날짜 선택 다이얼로그 생성
            val datePickerBuilder = AlertDialog.Builder(this@AddActivity)
            val datePickerView = LayoutInflater.from(applicationContext).inflate(R.layout.date_picker_dialog, null, false)
            datePickerBuilder.setView(datePickerView)
            val datePickerDialog = datePickerBuilder.create()
            datePicker = datePickerView.findViewById<View>(R.id.date_picker) as DatePicker

            //완료 버튼 클릭 시
            datePickerView.findViewById<View>(R.id.datepicker_dialog_submit).setOnClickListener {
                //선택되어 있는 값들 저장
                val year = datePicker!!.year
                val month = datePicker!!.month
                val day = datePicker!!.dayOfMonth
                date!!.year = year-1900
                date!!.month = month
                date!!.date = day
                tvDate.text = MyApplication.dateStrFormat.format(date)
                datePickerDialog.dismiss()
            }

            //닫기 버튼 클릭 시 다이얼로그 닫기
            datePickerView.findViewById<View>(R.id.button_close).setOnClickListener { datePickerDialog.dismiss() }

            //다이얼로그 띄우기
            datePickerDialog.show()
        }

        //이모지 클릭 시
        layoutEmoji.setOnClickListener {
            //이모지 선택 다이얼로그 생성
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

            //이모지 선택 이벤트 리스너 설정
            recyclerView.addOnItemTouchListener(RecyclerTouchListener(this, recyclerView)
                    .setClickable(object : RecyclerTouchListener.OnRowClickListener {
                        //다이얼로그의 이모지 선택 시
                        override fun onRowClicked(position: Int) {
                            val emoji: EmojiEntity = mEmojiViewModel.allEmojis.value!![position]
                            emojiId = emoji.id
                            image = emoji.image
                            ivEmoji.setImageResource(image)

                            //다이얼로그 닫기
                            dialog.dismiss()
                        }
                        override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
                    }))

            //다이얼로그 띄우기
            dialog.show()
        }

        //완료 버튼 클릭 시
        btnDone.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()
            when {
                //제목을 입력하지 않은 경우
                title.trim { it <= ' ' } == "" -> etTitle.error = "제목을 입력하세요."
                //내용을 입력하지 않은 경우
                content.trim { it <= ' ' } == "" -> etContent.error = "내용을 입력하세요."

                else -> {
                    //일기 추가
                    if (diaryId == -1) {
                        MyApplication.sInstance!!.diaryDao().insertDiary(DiaryEntity(dateFormat.format(date).toString(), emojiId, image, title, content, false))
                        MyApplication.sInstance!!.emojiDao().increaseCount(emojiId)
                    }
                    //일기 수정
                    else {
                        //원래 일기에서 사용한 이모지 count 개수 감소
                        MyApplication.sInstance!!.emojiDao().decreaseCount(diary!!.emojiId)

                        //원래 일기 데이터 수정
                        diary!!.title = title
                        diary!!.content = content
                        diary!!.emojiId = emojiId
                        diary!!.emojiRes = image
                        diary!!.date = dateFormat.format(date)

                        //새로운 데이터로 업데이트
                        MyApplication.sInstance!!.diaryDao().updateDiary(diary!!)
                        MyApplication.sInstance!!.emojiDao().increaseCount(emojiId)
                    }

                    finish()
                }
            }
        }

        //뒤로가기 버튼 클릭 시
        btnBack.setOnClickListener { finish() }
    }
}