package com.example.alarmtest;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // 알람 시간
    private Calendar calendar;
    private TimePicker timePicker;
    String strtime;
    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.calendar = Calendar.getInstance();
      /*  this.timePicker = findViewById(R.id.timePicker1);
        // 현재 날짜 표시
        displayDate();

        this.timePicker = findViewById(R.id.timePicker1);

        findViewById(R.id.btnCalendar).setOnClickListener(mClickListener);
        findViewById(R.id.btnAlarm).setOnClickListener(mClickListener);*/
    setAlarm();
    }
/*
     //날짜 표시
    private void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        ((TextView) findViewById(R.id.txtDate1)).setText(format.format(this.calendar.getTime()));
    }

     //DatePickerDialog 호출
    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 알람 날짜 설정
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, dayOfMonth);

                // 날짜 표시
                displayDate();
            }
        }, this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }*/
    /* 알람 등록 */
    private void setAlarm() {
        // 알람 시간 설정
/*        this.calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getHour());
        this.calendar.set(Calendar.MINUTE, this.timePicker.getMinute());*/
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,35);
        calendar.set(Calendar.SECOND, 0);

        //this.calendar.set(Calendar.HOUR_OF_DAY,H);
        //this.calendar.set(Calendar.HOUR_OF_DAY,M);
        // 현재일보다 이전이면 등록 실패
        if (this.calendar.before(Calendar.getInstance())) {
            Toast.makeText(this, "알람시간이 현재시간보다 이전일 수 없습니다.", Toast.LENGTH_LONG).show();
            calendar.add(Calendar.DATE, 1);
            Log.d("ODH","SETTING_TOMMROW");
        }
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        strtime=format.format(calendar.getTime());
        // Receiver 설정
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("Time",strtime);

        //cnt 대신 리퀘스트코드
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, cnt, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        // 알람 설정
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24,pendingIntent);
        // Toast 보여주기 (알람 시간 표시)
        Toast.makeText(this, "Alarm : " + format.format(calendar.getTime()), Toast.LENGTH_LONG).show();
        Log.d("ODH","complete");

    }/*
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCalendar:
                    // 달력
                    showDatePicker();
                    break;
                case R.id.btnAlarm:
                    // 알람 등록
                    setAlarm();
                    break;

            }
        }

    };*/

}
