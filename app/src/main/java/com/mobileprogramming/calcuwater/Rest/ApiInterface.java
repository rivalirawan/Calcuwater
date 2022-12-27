package com.mobileprogramming.calcuwater.Rest;

import com.mobileprogramming.calcuwater.Model.GetData;
import com.mobileprogramming.calcuwater.Model.PostPutDelData;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("restapi.php")
    Call<GetData> getData(@Query("function") String function);

    @Multipart
    @POST("restapi.php")
    Call<PostPutDelData> postData(@Query("function") String function,
                                   @Part("nama") RequestBody nama,
                                   @Part ("lebar_ambang") RequestBody lebar_ambang,
                                   @Part ("tinggi_bukaan") RequestBody tinggi_bukaan,
                                   @Part ("selisih_tinggi") RequestBody selisih_tinggi,
                                   @Part ("tanggal") RequestBody tanggal);


    @Multipart
    @POST("restapi.php")
    Call<PostPutDelData> postUpdateData(@Query("function") String function,
                                          @Query("id") String id,
                                          @Part ("nama") RequestBody nama,
                                          @Part ("lebar_ambang") RequestBody lebar_ambang,
                                          @Part ("tinggi_bukaan") RequestBody tinggi_bukaan,
                                          @Part ("selisih_tinggi") RequestBody selisih_tinggi,
                                          @Part ("tanggal") RequestBody tanggal);


    @POST("restapi.php")
    Call<PostPutDelData> deleteData(@Query("function") String function, @Query("id") String id);
}
