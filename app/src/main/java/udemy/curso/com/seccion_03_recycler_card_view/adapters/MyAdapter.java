package udemy.curso.com.seccion_03_recycler_card_view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import udemy.curso.com.seccion_03_recycler_card_view.R;
import udemy.curso.com.seccion_03_recycler_card_view.models.Movie;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener itemClickListener;

    private Context context;

    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Nos obliga que debemos devolver un ViewHolder
        //Se inflo la lista
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextViewName;
        public ImageView imageViewPoster;

        public ViewHolder(View itemView){
            super(itemView);

            TextViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            //this.TextViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }

        public void bind(final Movie movie, final OnItemClickListener listener){

            TextViewName.setText(movie.getName());
            TextViewName.setTextColor(Color.parseColor("#ffffff"));
            Picasso.get().load(movie.getPoster()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
}
