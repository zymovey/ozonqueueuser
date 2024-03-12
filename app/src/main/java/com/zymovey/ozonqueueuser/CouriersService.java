package com.zymovey.ozonqueueuser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CouriersService {
    @GET("/spring2/couriers")
    Call<ArrayList<Courier>> getAllItem();
}
