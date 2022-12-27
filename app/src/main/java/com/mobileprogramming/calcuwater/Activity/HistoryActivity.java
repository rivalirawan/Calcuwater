package com.mobileprogramming.calcuwater.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileprogramming.calcuwater.Adapter.DataAdapter;
import com.mobileprogramming.calcuwater.Model.Data;
import com.mobileprogramming.calcuwater.Model.GetData;
import com.mobileprogramming.calcuwater.R;
import com.mobileprogramming.calcuwater.Rest.ApiClient;
import com.mobileprogramming.calcuwater.Rest.ApiInterface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    public static HistoryActivity ha;
    ApiInterface mApiInterface;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_history);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ha=this;
        showData();

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            Intent moveIntent = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(moveIntent);
        }
    }

    public void showData() {
        Call<GetData> DataCall = mApiInterface.getData("get_data");
        DataCall.enqueue(new Callback<GetData>() {
            @Override
            public void onResponse(Call<GetData> call, Response<GetData>
                    response) {
                List<Data> notesData = response.body().getListDataNotes();
                Log.d("Retrofit Get", "Jumlah data: " +
                        String.valueOf(notesData.size()));
                mAdapter = new DataAdapter(notesData);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}