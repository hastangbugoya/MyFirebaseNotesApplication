package com.example.usersapi.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.usersapi.databinding.UserItemLayoutBinding;
import com.example.usersapi.databinding.UserItemVerticalLayoutBinding;
import com.example.usersapi.model.UserItem;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserItem> results;

    public UserAdapter(List<UserItem> results) {
        this.results = results;
    }

    public void setResults(List<UserItem> results) {

        this.results = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserItemVerticalLayoutBinding binding = UserItemVerticalLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position){
          UserItem item = results.get(position);
          holder.binding.titleTextview.setText(item.getName().getFirst()+" "+item.getName().getLast());
        holder.binding.emailTextview.setText(item.getEmail());
        holder.binding.phoneTextview.setText(item.getCell());

        Glide.with(holder.binding.getRoot())
                .applyDefaultRequestOptions(RequestOptions.bitmapTransform(new RoundedCorners(360)))
                .load(item.getPicture().getLarge())
                .into(holder.binding.posterImageview);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        UserItemVerticalLayoutBinding binding;
        public UserViewHolder(@NonNull UserItemVerticalLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
