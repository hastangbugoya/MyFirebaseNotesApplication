
package com.example.usersapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;


public class UserResponse {

    @SerializedName("results")
    @Expose
    private List<UserItem> results = null;

    public List<UserItem> getResults() {
        return results;
    }

    public void setResults(List<UserItem> results) {
        this.results = results;
    }

}
