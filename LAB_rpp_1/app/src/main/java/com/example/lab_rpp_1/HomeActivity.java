package com.example.lab_rpp_1;

import android.support.v7.app.AppCompatActivity;
import android.app.ListActivity;
import android.os.Bundle;

public class HomeActivity extends ListActivity {

    public static final int COUNT = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new My_Adapter(this, new String[COUNT]));
    }
}
