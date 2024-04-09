package com.zymovey.ozonqueueuser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CouriersService {
    @GET("/spring2/couriers")
    Call<ArrayList<Courier>> getAllItem();

    @PUT("/spring2/couriers/{id}")
    Call<ArrayList<Courier>> putItem(@Path ("id") int id, @Body Courier courier);

    @PATCH("/spring2/couriers/{id}")
    Call<Courier> updateItem(@Path("id") int id, @Body Courier courier );

}
