package com.kyra.moviedisplay;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.Math;

public class ListDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvResults;
    private Button buPrev,buNext;
    List<RecyclerViewData> movieList=new ArrayList<>();
    int results,pages,curr_page=1;
    Handler handler=new Handler();
    //private List<RecyclerViewData> mockDataList=new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);
        Bundle bundle=getIntent().getExtras();
        assert bundle != null;
        title=bundle.getString("query");
        recyclerView=findViewById(R.id.recycler_view);
        tvResults=findViewById(R.id .tvResults);
        buPrev=findViewById(R.id.buPrev);
        buNext=findViewById(R.id.buNext);
        //createMockList();
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        apiCall();
        //setUpRecyclerView();
    }
    private void apiCall() {
        APIServices apiService=AppClient.getInstance().createService(APIServices.class);
        Call<UserWrapper> call = apiService.getMovieList(title,APIServices.type,curr_page,APIServices.API_KEY);
        call.enqueue(new Callback<UserWrapper>() {
            @Override
            public void onResponse(Call<UserWrapper> call, Response<UserWrapper> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        if(response.body().isResponse())
                        {
                            movieList.addAll(response.body().getRecyclerViewDataList());
                            results=response.body().getResults();
                            pages= (int) Math.ceil(results/10.0);
                            buPrev.setVisibility(View.VISIBLE);
                            buNext.setVisibility(View.VISIBLE);
                            tvResults.setText(String.format("%s%s and %s pages", tvResults.getText(), results,pages));
                            tvResults.setVisibility(View.VISIBLE);
                            if(curr_page==1)
                                buPrev.setEnabled(false);
                            else if(curr_page==pages)
                                buNext.setEnabled(false);
                            setUpRecyclerView();
                        }
                        else{
                            recyclerView.setVisibility(View.INVISIBLE);
                            tvResults.setText(getString(R.string.no_movie_found));
                            tvResults.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserWrapper> call, Throwable t) {
                apiCall();
            }
        });
    }
    /*private void createMockList(){
        RecyclerViewData data;
        data = new RecyclerViewData("https://m.media-amazon.com/images/M/MV5BZmM2ZWYzZWItNWQxNi00YjY0LTkwMzUtMzFmOTg1N2U4MzI4XkEyXkFqcGdeQXVyMTcwMjIxNDg@._V1_SX300.jpg","Nisheeth Agrawal","7");
        mockDataList.add(data);
        data = new RecyclerViewData("https://m.media-amazon.com/images/M/MV5BZmM2ZWYzZWItNWQxNi00YjY0LTkwMzUtMzFmOTg1N2U4MzI4XkEyXkFqcGdeQXVyMTcwMjIxNDg@._V1_SX300.jpg","Ayush Patel","8");
        mockDataList.add(data);
        data = new RecyclerViewData("https://m.media-amazon.com/images/M/MV5BZmM2ZWYzZWItNWQxNi00YjY0LTkwMzUtMzFmOTg1N2U4MzI4XkEyXkFqcGdeQXVyMTcwMjIxNDg@._V1_SX300.jpg","Shireen Kunal Sona","9");
        mockDataList.add(data);
        data = new RecyclerViewData("https://m.media-amazon.com/images/M/MV5BZmM2ZWYzZWItNWQxNi00YjY0LTkwMzUtMzFmOTg1N2U4MzI4XkEyXkFqcGdeQXVyMTcwMjIxNDg@._V1_SX300.jpg","Bhushan Thakre","5");
        mockDataList.add(data);
    }*/
    private void setUpRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.setRecyclerViewDataList(movieList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void buSearchAgain(View view) {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(runnable,500);
    }

    public void buPrev(View view) {
        pages--;
        apiCall();
    }

    public void buNext(View view) {
        pages++;
        apiCall();
    }
}
