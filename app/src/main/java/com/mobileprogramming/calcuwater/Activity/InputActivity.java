package com.mobileprogramming.calcuwater.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mobileprogramming.calcuwater.Config;
import com.mobileprogramming.calcuwater.Model.Data;
import com.mobileprogramming.calcuwater.Activity.HistoryActivity;
import com.mobileprogramming.calcuwater.Model.PostPutDelData;
import com.mobileprogramming.calcuwater.R;
import com.mobileprogramming.calcuwater.Rest.ApiClient;
import com.mobileprogramming.calcuwater.Rest.ApiInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputActivity extends AppCompatActivity {
    EditText edtName, edtLebar, edtTinggi, edtSelisih;
    String date;
    ImageButton btnSubmit, btnUpload, btnBack;
    TextView tvDebit;
    ApiInterface mApiInterface;
    boolean isEmpty = false, isSubmit = false;

    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_input);

        edtName = findViewById(R.id.edt_nama);
        edtLebar = findViewById(R.id.edt_lebar);
        edtTinggi = findViewById(R.id.edt_tinggi);
        edtSelisih = findViewById(R.id.edt_selisih);
        date = getCurrentDate();
        tvDebit = findViewById(R.id.tv_debit);
        btnSubmit = findViewById(R.id.btn_submit);
        btnUpload = findViewById(R.id.btn_upload);
        btnBack = findViewById(R.id.btn_back);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btn_submit) {
                    if (!checkDulu()) {
                        isSubmit = true;
                        hitungData();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(ALERT_DIALOG_CLOSE);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSubmit) {
                    if(v.getId() == R.id.btn_upload) {
                        if (!checkDulu()) {
                            saveData();
                        }
                    }
                }
            }
        });
    }

    public boolean checkDulu() {
        isEmpty = false;
        String inputNama = edtName.getText().toString().trim();
        String inputLebar = edtLebar.getText().toString().trim();
        String inputTinggi = edtTinggi.getText().toString().trim();
        String inputSelisih = edtSelisih.getText().toString().trim();

        if(TextUtils.isEmpty(inputNama)) {
            isEmpty = true;
            edtName.setError("Nama harus diisi!");
        }
        if(TextUtils.isEmpty(inputLebar)) {
            isEmpty = true;
            edtLebar.setError("Kolom harus diisi!");
        }
        if(TextUtils.isEmpty(inputTinggi)) {
            isEmpty = true;
            edtTinggi.setError("Kolom harus diisi!");
        }
        if(TextUtils.isEmpty(inputSelisih)) {
            isEmpty = true;
            edtSelisih.setError("Kolom harus diisi!");
        }

        return isEmpty;
    }

    public void hitungData(){
        float lebar, tinggi, selisih, gravitasi, koefisienDebit, temp_debit;
        lebar = Float.parseFloat(edtLebar.getText().toString());
        tinggi = Float.parseFloat(edtTinggi.getText().toString());
        selisih = Float.parseFloat(edtSelisih.getText().toString());
        gravitasi = (float) 9.8;
        koefisienDebit = (float) 0.86;

        temp_debit =  (float) (koefisienDebit*lebar*tinggi*Math.sqrt(2*gravitasi*selisih));

        tvDebit.setText(String.valueOf(temp_debit));
    }

    private void saveData(){
        Call<PostPutDelData> postDataCall = mApiInterface.postData("insert_tabeldata",
                RequestBody.create( MediaType.parse("text/plain"),
                        edtName.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),
                        edtLebar.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),
                        edtTinggi.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),
                        edtSelisih.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"),
                        date));

        postDataCall.enqueue(new Callback<PostPutDelData>() {
            @Override
            public void onResponse(Call<PostPutDelData> call, Response<PostPutDelData> response) {
                Intent moveIntent = new Intent(InputActivity.this, MainActivity.class);
                startActivity(moveIntent);
            }

            @Override
            public void onFailure(Call<PostPutDelData> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Log.d("RETRO", "ON FAILURE : " + t.getCause());
                Toast.makeText(getApplicationContext(), "Error, entry data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
            dialogTitle = "Hapus Data";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose) {
                            Intent moveIntent = new Intent(InputActivity.this, MainActivity.class);
                            startActivity(moveIntent);
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}