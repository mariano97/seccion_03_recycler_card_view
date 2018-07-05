package udemy.curso.com.seccion_03_recycler_card_view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import udemy.curso.com.seccion_03_recycler_card_view.models.Movie;
import udemy.curso.com.seccion_03_recycler_card_view.adapters.MyAdapter;
import udemy.curso.com.seccion_03_recycler_card_view.R;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); //grid pero de doferente tamaño

        mAdapter = new MyAdapter(movies, R.layout.recicler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
                removeMovie(position);
            }
        });

        mRecyclerView.setHasFixedSize(true); //Sirve para controlar la extension del layout, (si se usa el Staggred, no se usa esta linea)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//Animacion default

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(0);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Martian", R.drawable.martian));
            add(new Movie("Holmes", R.drawable.holmes));
            add(new Movie("Star Wars", R.drawable.starwars));
            add(new Movie("Rogue One", R.drawable.rogueone));
        }};
    }

    private void addMovie(int position){
        movies.add(position, new Movie("Deadpoll"+ (++counter),R.drawable.deadpool));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);// dirige la vista hasta el item nuevo
    }

    private void removeMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
