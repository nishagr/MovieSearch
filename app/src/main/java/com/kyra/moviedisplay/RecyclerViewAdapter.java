package com.kyra.moviedisplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<RecyclerViewData> recyclerViewDataList = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setRecyclerViewDataList(List<RecyclerViewData> recyclerViewDataList) {
        this.recyclerViewDataList = recyclerViewDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_view,viewGroup,false);
        return new MyViewHolder(view,recyclerViewDataList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final RecyclerViewData data = recyclerViewDataList.get(i);
        //data fetch
        myViewHolder.txtSN.setText((i+1)+".");
        myViewHolder.txtName.setText(data.getName());
        myViewHolder.txtImdbID.setText("IMDB ID:"+data.getImdbID());
        Glide.with(context).load(data.getImage()).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
            return recyclerViewDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        //data initialize
        TextView txtSN, txtName, txtImdbID;
        ImageView img;
        private final Context context;
        List<RecyclerViewData> recyclerViewData;

        public MyViewHolder(@NonNull View itemView,List<RecyclerViewData> recyclerViewDataList) {
            super(itemView);
            context=itemView.getContext();
            itemView.setOnClickListener(this);
            recyclerViewData=recyclerViewDataList;
            //data link
            txtSN=itemView.findViewById(R.id.movieSN);
            txtName=itemView.findViewById(R.id.movieName);
            txtImdbID=itemView.findViewById(R.id.movieImdbID);
            img=itemView.findViewById(R.id.img);
        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(itemView.getContext(), "position = " + (getLayoutPosition()+1) , Toast.LENGTH_SHORT).show();
                final Intent intent;
                final String imdbID;
            if (getLayoutPosition() == 0) {
                intent = new Intent(context, MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==1){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==2){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==3){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==4){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==5){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==6){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==7){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==8){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();
            }
            else if (getLayoutPosition()==9){
                intent=new Intent(context,MovieDisplay.class);
                imdbID=recyclerViewData.get(getLayoutPosition()).getImdbID();

            }
            else{
                intent=null;
                imdbID=null;
            }
            assert intent != null;
            intent.putExtra("imdbID",imdbID);
            context.startActivity(intent);
        }
    }
}
