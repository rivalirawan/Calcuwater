package com.mobileprogramming.calcuwater.Activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnLogin;
    private EditText username, password;
    ApiInterface mApiInterface;
    List<Data> mDataList;
    private int jumlahUser = 1;
    Throwable t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_login) {
            Call<GetData> DataCall = mApiInterface.getData("get_account");
            DataCall.enqueue(new Callback<GetData>() {
                @Override
                public void onResponse(Call<GetData> call, Response<GetData>
                        response) {
                    List<Data> notesData = response.body().getListDataNotes();
                    Log.d("Retrofit Get", "Jumlah data: " +
                            String.valueOf(notesData.size()));
                    boolean isCorrect = false;

                    for (int i = 0; i <= jumlahUser; i++) {
                        if (notesData.get(i).getUsername().equals(username.getText().toString().trim()) && notesData.get(i).getPassword().equals(password.getText().toString().trim())){
                            isCorrect = true;
                            Intent moveIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(moveIntent);
                        }
                    }

                    if (!isCorrect) {
                        Toast.makeText(getApplicationContext(), "Error, Wrong Username or Password", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<GetData> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                }
            });

        }
    }
}