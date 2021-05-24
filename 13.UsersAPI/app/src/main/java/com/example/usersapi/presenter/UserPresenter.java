package com.example.usersapi.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.usersapi.model.UserResponse;
import com.example.usersapi.network.UserRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements Contract.Presenter {
    private Contract.View view;

    private UserRetrofit userRetrofit = new UserRetrofit();

    public UserPresenter(Contract.View view)  {
        this.view = view;
    }

    @Override
    public void getResults(int a) {
        view.setStatus(Status.LOADING);
        userRetrofit.searchQuery(a).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                Log.d("TAG_X", "onResponse -> " + Thread.currentThread().getName());
                if(response.body()!= null && response.body().getResults() != null){
                    view.displayResults(response.body().getResults());
                    view.setStatus(Status.COMPLETE);
                } else{
                    Log.d("TAG_X", "Empty or null.. :(");
                    view.setStatus(Status.ERROR);
                }
            }

            @Override
            //This will happen when no internet
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("TAG_X", t.toString()+" \nurl:"+call.request().url());
                view.setStatus(Status.ERROR);
            }
        });
    }

    public enum Status{
        LOADING,
        COMPLETE,
        ERROR
    }
}
