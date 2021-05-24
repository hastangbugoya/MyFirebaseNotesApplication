package com.example.usersapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.usersapi.R;
import com.example.usersapi.databinding.ActivityMainBinding;
import com.example.usersapi.model.UserItem;
import com.example.usersapi.presenter.Contract;
import com.example.usersapi.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.usersapi.presenter.UserPresenter.Status.COMPLETE;
import static com.example.usersapi.presenter.UserPresenter.Status.ERROR;
import static com.example.usersapi.presenter.UserPresenter.Status.LOADING;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private ActivityMainBinding binding;
    private UserAdapter adapter = new UserAdapter(new ArrayList<>());

    private Contract.Presenter presenter = new UserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainRecyclerview.setAdapter(adapter);

        binding.searchButton.setOnClickListener(view -> {
            String value = binding.searchEdittext.getText().toString().trim();

            if(value.isEmpty())
                showToast("Cannot search for empty string..");
            else{
                int num = Integer.parseInt(value);
                Log.d("TAG_X", "Searching for " + num);
                presenter.getResults(num);
            }

        });
    }

    @Override
    public void displayResults(List<UserItem> items) {
        adapter.setResults(items);
    }

    @Override
    public void setStatus(UserPresenter.Status status) {
        switch (status){
            case ERROR:
                showToast("An error occurred!");
                binding.progressbar.setVisibility(View.GONE);
                break;
            case LOADING:
                binding.progressbar.setVisibility(View.VISIBLE);
                break;
            case COMPLETE:
                binding.progressbar.setVisibility(View.GONE);
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}