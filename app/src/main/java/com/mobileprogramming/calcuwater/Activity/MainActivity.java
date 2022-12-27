package com.mobileprogramming.calcuwater.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.mobileprogramming.calcuwater.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton btnCalc, btnHist, btnLogout;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        ma = this;
        btnCalc = findViewById(R.id.btn_calc);
        btnHist = findViewById(R.id.btn_hist);
        btnLogout = findViewById(R.id.btn_logout);
        btnCalc.setOnClickListener(this);
        btnHist.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        /*Intent moveIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(moveIntent);*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calc) {
            Intent moveIntent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(moveIntent);
        } else if (v.getId() == R.id.btn_hist) {
            Intent moveIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(moveIntent);
        } else if (v.getId() == R.id.btn_logout) {
            Intent moveIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(moveIntent);
        }
    }
}