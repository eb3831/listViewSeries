package com.example.listviewseries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    EditText etA1;
    EditText etQorD;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etA1 = findViewById(R.id.etA1);
        etQorD = findViewById(R.id.etQorD);
        sw = findViewById(R.id.sw);
    }

    public void clickedSwitch(View view)
    {
        if (sw.isChecked())
            etQorD.setHint("הכנס הפרש:");
        else
            etQorD.setHint("הכנס מכפיל:");
    }


    public void clickedNext(View view)
    {
        if (( etA1.getText().toString().isEmpty() )
                ||( etQorD.getText().toString().isEmpty()))
            Toast.makeText(this, "illegal action! please insert numbers.", Toast.LENGTH_SHORT).show();
        else
        {
            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("etQorD", String.valueOf(etQorD));
            intent.putExtra("etA1", String.valueOf(etA1));
            startActivity(intent);

        }
    }
}