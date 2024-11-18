package com.example.listviewseries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView lv;
    Intent gi;
    double [] seriesArry;
    String[] disArray;
    double a1, seriesSum, dORq;
    int seriesType;
    double difference;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lv = findViewById(R.id.lv);

        gi = getIntent();
        seriesType = getIntent().getIntExtra("seriesType", 0);
        a1 = getIntent().getDoubleExtra("a1", 0);
        difference = getIntent().getDoubleExtra("difference", 0);

        seriesArry = new double[20];
        disArray = new String[20];

        if (seriesType == 0)
        {

             for ( int i = 1; i < 21; i++ )
             {
                 seriesArry[i-1] = a1 * Math.pow(difference, i - 1);
                 disArray[i-1] = String.valueOf(seriesArry[i-1]);
             }
        }

        else
        {
            for ( int i = 1; i < 21; i++ )
            {
                seriesArry[i-1] = a1 + ((i - 1) * difference);
                disArray[i-1] = String.valueOf(seriesArry[i-1]);
            }
        }

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, disArray);
        lv.setAdapter(adp);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {

    }
}