package com.example.tiplservice;

import com.example.tiplservice.ModelResponse.FetchUserResponse;
import com.example.tiplservice.ModelResponse.LoginResponse;
import com.example.tiplservice.ModelResponse.RegisterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
        @Field("username") String username,
        @Field("email") String email,
        @Field("password") String password

    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password

    );


    @GET("fetchusers.php")
    Call<FetchUserResponse> fetchAllUsers();


    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id") int userid,
            @Field("username") String userName,
            @Field("email") String email


    );
}
