package com.example.lab3;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    mDatabase database;
    Button btn_one;
    Button btn_two;
    Button btn_three;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room.databaseBuilder(this, mDatabase.class, "databaseST")
                .allowMainThreadQueries()
                .build();

        database.studentDao().clearAll();

        btn_one = findViewById(R.id.button);
        btn_two = findViewById(R.id.button2);
        btn_three = findViewById(R.id.button3);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);

        database.studentDao().insertAll(new Student("Аверченков Никита Эдуардович", getDate()));
        database.studentDao().insertAll(new Student("Верба Дмитрий Сергеевич", getDate()));
        database.studentDao().insertAll(new Student("Данилов Дмитрий Евгеньевич", getDate()));
        database.studentDao().insertAll(new Student("Загидулин Амир Равилевич", getDate()));
        database.studentDao().insertAll(new Student("Крамаренко Злата Викторовна", getDate()));
        database.studentDao().insertAll(new Student("Матвеев Артем Сергеевич", getDate()));
        database.studentDao().insertAll(new Student("Меньков Иван Александрович", getDate()));
        database.studentDao().insertAll(new Student("Политов Александр Юрьевич", getDate()));


    }
    @SuppressLint("SimpleDateFormat")
    private String getDate()
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                Intent intent = new Intent(this, ListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button2: {
                database.studentDao().insertAll(new Student("Тимофеев Илья Сергеевич", getDate()));
                break;
            }
            case R.id.button3: {
                Student student = database.studentDao().getLastRecord();
                student.name = "Травин Михаил Борисович";
                database.studentDao().update(student);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        database.close();
        super.onDestroy();
    }

}
