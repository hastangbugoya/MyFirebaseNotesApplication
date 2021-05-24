package com.example.usersapi.network;

import android.util.Log;

import com.example.usersapi.model.UserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UserRetrofit {

    // URL --   https://randomuser.me/api/?results=70
    //base url -- https://randomuser.me
    //path -- api
    // query ---  ?results=70

    private UserService userService = createRetrofit().create(UserService.class);

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(" https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<UserResponse> searchQuery(int search){

        Log.d("TAG_X", "SearchQuery..." + Thread.currentThread().getName());
        return userService.searchResults(search);
    }

    interface UserService {
        @GET("api")                            //results because the in URL is results
        Call<UserResponse> searchResults(@Query("results") int query);
    }

}
