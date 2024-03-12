package com.zymovey.ozonqueueuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BaseUrl = "http://192.168.1.114:8080";
    private static Retrofit retro;

    RecyclerView recyclerView;
    ArrayList<Courier> dataArrayList;
    private CouriersService couriersService;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec_view);

// Инициализируем лист
        dataArrayList = new ArrayList<>();
        // adapter = new Adapter(dataArrayList);
        conectRetrofit();

        displayJson();

//создаем адаптер и передаем лист с данными через конструктор

    }

    public Retrofit conectRetrofit() {
        if (retro == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            retro = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return retro;
    }

    private void displayJson() {

        //now call api class

        couriersService = retro.create(CouriersService.class);
        Call<ArrayList<Courier>> arrayListCall = couriersService.getAllItem();

        arrayListCall.enqueue(new Callback<ArrayList<Courier>>() {
            @Override
            public void onResponse(Call<ArrayList<Courier>> call, Response<ArrayList<Courier>> response) {

                    ArrayList<Courier> queueArrayList;
                    queueArrayList = new ArrayList<>();
                    dataArrayList = response.body();


                    //Toast toast2 = Toast.makeText(MainActivity.this, dataArrayList.size()+" в очереди" , Toast.LENGTH_SHORT);
                    //toast2.show();
                    //loop for recyclerview item form mysql database
                if (dataArrayList == null) {
                    try {
                        throw new NullPointerException("Кота не существует");
                    } catch (NullPointerException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    for (int i = 0; i < dataArrayList.size(); i++) {
                        if (dataArrayList.get(i).getPosition() > 0) {
                            queueArrayList.add(dataArrayList.get(i));
                            //set adapter
                            //adapter = new Adapter(dataArrayList, MainActivity.this);
                        }

                        adapter = new Adapter((ArrayList<Courier>) queueArrayList.stream().sorted(Comparator.comparing(Courier::getPosition))
                                .collect(Collectors.toList()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                        //now set layout
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                }

                    //тост количество людей в очереди
                    Toast toast = Toast.makeText(MainActivity.this, queueArrayList.size() + " в очереди", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(Color.RED);
                    v.setTextSize(25);
                    toast.show();

            }

            @Override
            public void onFailure(Call<ArrayList<Courier>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("zimov", "Response = " + t.toString());
            }
        });

    }
}


