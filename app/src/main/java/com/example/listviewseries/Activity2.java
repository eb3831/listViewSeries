package com.example.listviewseries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView lv;
    Intent gi;
    double[] seriesArry;
    String[] disArray;
    double a1, seriesSum, difference;
    int seriesType;
    TextView tvDorQ, tvA1, tvDifference, tvSn, tvN;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        lv = findViewById(R.id.lv);
        tvDorQ = findViewById(R.id.tvDorQ);
        tvA1 = findViewById(R.id.tvA1);
        tvDifference = findViewById(R.id.tvDifference);
        tvSn = findViewById(R.id.tvSn);
        tvN = findViewById(R.id.tvN);

        gi = getIntent();
        seriesType = getIntent().getIntExtra("seriesType", 0);
        a1 = getIntent().getDoubleExtra("a1", 0);
        difference = getIntent().getDoubleExtra("difference", 0);

        seriesArry = new double[20];
        disArray = new String[20];


        tvDifference.setText(String.valueOf(difference));
        tvA1.setText(String.valueOf(a1));


        if (seriesType == 0)
        {
            tvDorQ.setText("q:");
            for (int i = 1; i < 21; i++)
            {
                seriesArry[i - 1] = a1 * Math.pow(difference, i - 1);
                disArray[i - 1] =  differentView(seriesArry[i - 1]);
            }
        }
        else
        {
            tvDorQ.setText("d:");
            for (int i = 1; i < 21; i++)
            {
                seriesArry[i - 1] = a1 + ((i - 1) * difference);
                disArray[i - 1] = differentView(seriesArry[i - 1]);
            }
        }


        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, disArray);
        lv.setAdapter(adp);
    }

    public static String differentView(double term)
    {
        if (term % 1 == 0 && Math.abs(term) < 10000)
        {
            return String.valueOf((int) term);
        }

        if (Math.abs(term) < 10000 && Math.abs(term) >= 0.001)
        {
            return String.format("%.3f", term);
        }

        int exponent = 0;
        double coefficient = term;

        if (Math.abs(term) >= 1)
        {
            while (Math.abs(coefficient) >= 10)
            {
                coefficient /= 10;
                exponent++;
            }
        }
        else
        {
            while (Math.abs(coefficient) < 1 && coefficient != 0)
            {
                coefficient *= 10;
                exponent--;
            }
        }

        return String.format("%.3f * 10^%d", coefficient, exponent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
    {
        if (seriesType == 0)
        {
            seriesSum = (a1 * (Math.pow(difference, pos + 1) - 1)) / (difference - 1);
        }
        else
        {
            seriesSum = ((pos + 1) * (a1 + seriesArry[pos])) / 2;
        }
        tvSn.setText(differentView(seriesSum));
        tvN.setText(String.valueOf(pos + 1));
    }

    public void clickedBack(View view)
    {
        finish();
    }

}