package com.example.usersapi.presenter;

import com.example.usersapi.model.UserItem;

import java.util.List;

public interface Contract {

    interface Presenter {

        void getResults(int a);
    }

    interface View {
        void displayResults(List<UserItem> items);
        void setStatus(UserPresenter.Status status);
    }

}
