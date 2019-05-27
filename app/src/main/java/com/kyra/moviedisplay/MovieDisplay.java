package com.kyra.moviedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDisplay extends AppCompatActivity {

    String imdbID,poster,title,released,runtime,genre,director,writer,plot,imdbRating,production;
    ImageView ivPoster,ivBack;
    TextView tvTitle,tvReleased,tvRuntime,tvGenre,tvDirector,tvWriter,tvPlot,tvImdbRating,tvProduction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);
        //textView=findViewById(R.id.tvImdbID);
        Bundle bundle=getIntent().getExtras();
        assert bundle != null;
        imdbID=bundle.getString("imdbID");
        //textView.setText(imdbID);
        apicall();
    }
    private void apicall(){
        final APIServices2 apiServices2=AppClient.getInstance().createService(APIServices2.class);
        Call<MovieDisplayData> call=apiServices2.getMovie(imdbID,APIServices2.plot,APIServices2.API_KEY);
        call.enqueue(new Callback<MovieDisplayData>() {
            @Override
            public void onResponse(Call<MovieDisplayData> call, Response<MovieDisplayData> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        poster=response.body().getPoster();
                        title=response.body().getTitle();
                        released=response.body().getReleased();
                        runtime=response.body().getRuntime();
                        genre=response.body().getGenre();
                        director=response.body().getDirector();
                        writer=response.body().getWriter();
                        plot=response.body().getPlot();
                        imdbRating=response.body().getImdbRating();
                        production=response.body().getProduction();
                        setUpMovieDisplay();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDisplayData> call, Throwable t) {
                apicall();
            }
        });
    }
    public void setUpMovieDisplay(){
        ivBack=findViewById(R.id.ivBack);
        ivPoster=findViewById(R.id.ivPoster);
        tvTitle=findViewById(R.id.tvTitle);
        tvReleased=findViewById(R.id.tvReleased);
        tvRuntime=findViewById(R.id.tvRuntime);
        tvGenre=findViewById(R.id.tvGenre);
        tvDirector=findViewById(R.id.tvDirector);
        tvWriter=findViewById(R.id.tvWriter);
        tvPlot=findViewById(R.id.tvPlot);
        tvImdbRating=findViewById(R.id.tvImdbRating);
        tvProduction=findViewById(R.id.tvProduction);
        if(!poster.equals(getString(R.string.not_available)))
            Glide.with(this).load(poster).into(ivBack);
        if(!poster.equals(getString(R.string.not_available)))
            Glide.with(this).load(poster).into(ivPoster);
        else
            ivPoster.setImageResource(R.drawable.no_image);
        tvTitle.setText(title);
        if(!released.equals(getString(R.string.not_available)))
            tvReleased.setText(released);
        if(!runtime.equals(getString(R.string.not_available)))
            tvRuntime.setText(runtime);
        if(!genre.equals(getString(R.string.not_available)))
            tvGenre.setText(genre);
        if(!director.equals(getString(R.string.not_available)))
            tvDirector.setText(director);
        if(!writer.equals(getString(R.string.not_available)))
            tvWriter.setText(writer);
        if(!plot.equals(getString(R.string.not_available)))
            tvPlot.setText(plot);
        if(!imdbRating.equals(getString(R.string.not_available)))
            tvImdbRating.setText(imdbRating);
        if(!production.equals(getString(R.string.not_available)))
            tvProduction.setText(production);
    }
}
