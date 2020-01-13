package org.techtest.emoji_diary;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.techtest.emoji_diary.Fragment.Fragment_Tab1;

import java.util.Date;

public class AddDiaryActivity extends AppCompatActivity {

    private Button btnDone,btnBack;
    private LinearLayout layoutEmoji;
    private EditText etTitle,etContent;
    private ImageView ivEmoji;
    private TextView tvDate;
    private Diary diary;
    private int position;
    private int emoji;
    private boolean favorite;
    private Date date;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        btnDone = findViewById(R.id.button_done);
        btnBack = findViewById(R.id.button_back);
        layoutEmoji = findViewById(R.id.emoji_layout);
        etTitle = findViewById(R.id.txt_title);
        etContent = findViewById(R.id.txt_content);
        ivEmoji = findViewById(R.id.img_emoji);
        tvDate = findViewById(R.id.txt_date);

        position = getIntent().getIntExtra("position",-1);

        date = Fragment_Tab1.dateToday;
        tvDate.setText(date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDate()+"일");

        if(position>=0) {
            diary = MainActivity.diaryArrayList.get(position);

            date = diary.getDate();
            tvDate.setText(date.getYear()+"년 "+(date.getMonth()+1)+"월 "+date.getDate()+"일");
            etTitle.setText(diary.getTitle());
            etContent.setText(diary.getContent().replace(" ","\u00A0"));
            favorite = diary.getFavorite();

            emoji = diary.getImage();
            switch (emoji) {
                case 1:
                    ivEmoji.setImageResource(R.drawable.emoji1);
                    break;
                case 2:
                    ivEmoji.setImageResource(R.drawable.emoji2);
                    break;
                case 3:
                    ivEmoji.setImageResource(R.drawable.emoji3);
                    break;
                case 4:
                    ivEmoji.setImageResource(R.drawable.emoji4);
                    break;
                case 5:
                    ivEmoji.setImageResource(R.drawable.emoji5);
                    break;
                case 6:
                    ivEmoji.setImageResource(R.drawable.emoji6);
                    break;
                case 7:
                    ivEmoji.setImageResource(R.drawable.emoji7);
                    break;
                case 8:
                    ivEmoji.setImageResource(R.drawable.emoji8);
                    break;
                case 9:
                    ivEmoji.setImageResource(R.drawable.emoji9);
                    break;
                case 10:
                    ivEmoji.setImageResource(R.drawable.emoji10);
                    break;
                case 11:
                    ivEmoji.setImageResource(R.drawable.emoji11);
                    break;
                case 12:
                    ivEmoji.setImageResource(R.drawable.emoji12);
                    break;
                case 13:
                    ivEmoji.setImageResource(R.drawable.emoji13);
                    break;
                case 14:
                    ivEmoji.setImageResource(R.drawable.emoji14);
                    break;
                case 15:
                    ivEmoji.setImageResource(R.drawable.emoji15);
                    break;
                case 16:
                    ivEmoji.setImageResource(R.drawable.emoji16);
                    break;
                case 17:
                    ivEmoji.setImageResource(R.drawable.emoji17);
                    break;
                case 18:
                    ivEmoji.setImageResource(R.drawable.emoji18);
                    break;
                case 19:
                    ivEmoji.setImageResource(R.drawable.emoji19);
                    break;
                case 20:
                    ivEmoji.setImageResource(R.drawable.emoji20);
                    break;
                case 21:
                    ivEmoji.setImageResource(R.drawable.emoji21);
                    break;
                case 22:
                    ivEmoji.setImageResource(R.drawable.emoji22);
                    break;
                case 23:
                    ivEmoji.setImageResource(R.drawable.emoji23);
                    break;
                case 24:
                    ivEmoji.setImageResource(R.drawable.emoji24);
                    break;
                case 25:
                    ivEmoji.setImageResource(R.drawable.emoji25);
                    break;
                case 26:
                    ivEmoji.setImageResource(R.drawable.emoji26);
                    break;
                case 27:
                    ivEmoji.setImageResource(R.drawable.emoji27);
                    break;
                case 28:
                    ivEmoji.setImageResource(R.drawable.emoji28);
                    break;
                case 29:
                    ivEmoji.setImageResource(R.drawable.emoji29);
                    break;
                case 30:
                    ivEmoji.setImageResource(R.drawable.emoji30);
                    break;
                case 31:
                    ivEmoji.setImageResource(R.drawable.emoji31);
                    break;
            }
        }

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder datepicker_builder = new AlertDialog.Builder(AddDiaryActivity.this);
                View datepicker_view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.date_picker_dialog,null,false);
                datepicker_builder.setView(datepicker_view);

                final AlertDialog datepicker_dialog = datepicker_builder.create();
                datePicker = (DatePicker)datepicker_view.findViewById(R.id.date_picker);

                datepicker_view.findViewById(R.id.datepicker_dialog_submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        date.setYear(year);
                        date.setDate(day);
                        date.setMonth(month);
                        tvDate.setText(year+"년 "+(month+1)+"월 "+day+"일");

                        datepicker_dialog.dismiss();
                    }
                });

                datepicker_view.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datepicker_dialog.dismiss();
                    }
                });

                datepicker_dialog.show();
            }
        });

        layoutEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDiaryActivity.this);
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.emoji_dialog,null,false);
                builder.setView(view);

                final AlertDialog dialog = builder.create();

                view.findViewById(R.id.emoji1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 1;
                        ivEmoji.setImageResource(R.drawable.emoji1);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 2;
                        ivEmoji.setImageResource(R.drawable.emoji2);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 3;
                        ivEmoji.setImageResource(R.drawable.emoji3);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 4;
                        ivEmoji.setImageResource(R.drawable.emoji4);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji5).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 5;
                        ivEmoji.setImageResource(R.drawable.emoji5);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji6).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 6;
                        ivEmoji.setImageResource(R.drawable.emoji6);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji7).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 7;
                        ivEmoji.setImageResource(R.drawable.emoji7);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 8;
                        ivEmoji.setImageResource(R.drawable.emoji8);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji9).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 9;
                        ivEmoji.setImageResource(R.drawable.emoji9);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji10).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 10;
                        ivEmoji.setImageResource(R.drawable.emoji10);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji11).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 11;
                        ivEmoji.setImageResource(R.drawable.emoji11);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji12).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 12;
                        ivEmoji.setImageResource(R.drawable.emoji12);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji13).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 13;
                        ivEmoji.setImageResource(R.drawable.emoji13);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji14).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 14;
                        ivEmoji.setImageResource(R.drawable.emoji14);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji15).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 15;
                        ivEmoji.setImageResource(R.drawable.emoji15);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji16).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 16;
                        ivEmoji.setImageResource(R.drawable.emoji16);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji17).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 17;
                        ivEmoji.setImageResource(R.drawable.emoji17);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji18).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 18;
                        ivEmoji.setImageResource(R.drawable.emoji18);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji19).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 19;
                        ivEmoji.setImageResource(R.drawable.emoji19);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji20).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 20;
                        ivEmoji.setImageResource(R.drawable.emoji20);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji21).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 21;
                        ivEmoji.setImageResource(R.drawable.emoji21);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji22).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 22;
                        ivEmoji.setImageResource(R.drawable.emoji22);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji23).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 23;
                        ivEmoji.setImageResource(R.drawable.emoji23);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji24).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 24;
                        ivEmoji.setImageResource(R.drawable.emoji24);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji25).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 25;
                        ivEmoji.setImageResource(R.drawable.emoji25);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji26).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 26;
                        ivEmoji.setImageResource(R.drawable.emoji26);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji27).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 27;
                        ivEmoji.setImageResource(R.drawable.emoji27);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji28).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 28;
                        ivEmoji.setImageResource(R.drawable.emoji28);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji29).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 29;
                        ivEmoji.setImageResource(R.drawable.emoji29);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji30).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 30;
                        ivEmoji.setImageResource(R.drawable.emoji30);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.emoji31).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emoji = 31;
                        ivEmoji.setImageResource(R.drawable.emoji31);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if(title.trim().equals(""))
                    etTitle.setError("제목을 입력하세요.");
                else if (content.trim().equals(""))
                    etContent.setError("내용을 입력하세요.");
                else {
                    if (position == -1)
                        MainActivity.diaryArrayList.add(0, new Diary(emoji, title, date, content, favorite));
                    else {
                        diary.setDate(date);
                        diary.setContent(content);
                        diary.setTitle(title);
                        diary.setImage(emoji);
                        diary.setFavorite(favorite);
                        MainActivity.diaryArrayList.set(position, diary);
                    }
                    finish();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
