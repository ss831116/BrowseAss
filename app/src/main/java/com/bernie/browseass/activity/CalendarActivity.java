package com.bernie.browseass.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bernie.browseass.R;
import com.bernie.browseass.http.HttpRequest;
import com.bernie.browseass.listener.HttpRequestListener;
import com.bernie.browseass.utils.RequestKey;
import com.bernie.browseass.widget.CalendarView;
import com.bernie.browseass.widget.CalendarView.OnItemClickListener;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements HttpRequestListener {
    private CalendarView calendar;
    private ImageButton calendarLeft;
    private TextView calendarCenter;
    private ImageButton calendarRight;
    private SimpleDateFormat format;
    private static final String CALENDAR_TAG = "get_calendar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    public void initView() {
        format = new SimpleDateFormat("yyyy-MM-dd");
        //获取日历控件对象
        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setSelectMore(false); //单选

        calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);
        calendarCenter = (TextView) findViewById(R.id.calendarCenter);
        calendarRight = (ImageButton) findViewById(R.id.calendarRight);
        try {
            //设置日历日期
            Date date = format.parse("2015-01-01");
            calendar.setCalendarData(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取日历中年月 ya[0]为年，ya[1]为月（格式大家可以自行在日历控件中改）
        String[] ya = calendar.getYearAndmonth().split("-");
        calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
        calendarLeft.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击上一月 同样返回年月
                String leftYearAndmonth = calendar.clickLeftMonth();
                String[] ya = leftYearAndmonth.split("-");
                calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
            }
        });

        calendarRight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击下一月
                String rightYearAndmonth = calendar.clickRightMonth();
                String[] ya = rightYearAndmonth.split("-");
                calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
            }
        });

        //设置控件监听，可以监听到点击的每一天（大家也可以在控件中根据需求设定）
        calendar.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void OnItemClick(Date selectedStartDate,
                                    Date selectedEndDate, Date downDate) {
                if (calendar.isSelectMore()) {
                    Toast.makeText(getApplicationContext(), format.format(selectedStartDate) + "到" + format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
                }
                StringBuffer str=new StringBuffer(format.format(downDate));
                for (int i = 0; i < str.length(); i++) {
                    if (String.valueOf(str.charAt(i)).equals("-")) {
                        if (String.valueOf(str.charAt(i+1)).equals("0")) {
                            str = str.replace(i+1,i+2,"");
                            Log.d("CalendarActivity", "11111"+ ";="+ str);
                        }
                    }
                }
                HttpRequest.httpRequest("http://japi.juhe.cn/calendar/day?date=" + str + RequestKey.CALENDAR_KEY, CalendarActivity.this, CALENDAR_TAG);
            }
        });

    }

    @Override
    public void requestSuccess(JSONObject jsonObject) {
        Log.d("CalendarActivity", "jsonObject = " + jsonObject.toString());
    }

    @Override
    public void requestFail(VolleyError error) {

    }

    public static String getMyDate(String str) {
        return StringToDate(str, "yyyy-MM-dd", "yyyy/MM/dd");
    }

    public static String StringToDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);

        return s.format(date);
    }
}
