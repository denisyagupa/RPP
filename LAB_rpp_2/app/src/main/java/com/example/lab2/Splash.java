package com.example.lab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class Splash extends AppCompatActivity
{
    private Service serviceAPI = Client.getClient();
    Call<ArrayList<JsonPojoClass>> call = serviceAPI.getTech();
    private QueryTask queryTask = (QueryTask) new QueryTask();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        queryTask.execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    class QueryTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Response<ArrayList<JsonPojoClass>> response = call.execute();
                ArrayList<JsonPojoClass> arrayList = response.body();
                assert arrayList != null;
                ClassList.fill(arrayList);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}


