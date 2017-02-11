package com.example.tina.iLOVEZappos.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tina.iLOVEZappos.R;
import com.example.tina.iLOVEZappos.binding.ProductsAdapter;
import com.example.tina.iLOVEZappos.model.ProductsResponse;
import com.example.tina.iLOVEZappos.Retrofit.ApiClient;
import com.example.tina.iLOVEZappos.Retrofit.ApiInterface;
import com.example.tina.iLOVEZappos.model.Product;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity2_search extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";
    private static String search_term = "";
    @Bind(R.id.tapBarMenu)
    TapBarMenu tapBarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();





        //get search query from user input in MainActivity
        final String search_term = intent.getStringExtra("search");

        Log.i(TAG, "onCreate: "+search_term);

        //change action bar title to search term
        setTitle(search_term);


        //Display list of products
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        Call<ProductsResponse> call = apiService.getSearch(search_term,API_KEY);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                int statusCode = response.code();
                List<Product> movies = response.body().getResults();
                if (movies.size()==0){
                    Toast.makeText(getApplicationContext(),"Sorry, no result found!",Toast.LENGTH_SHORT).show();
                }
                recyclerView.setAdapter(new ProductsAdapter(movies, R.layout.list_item_search, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.tapBarMenu) public void onMenuButtonClick() {
        tapBarMenu.toggle();
        Log.i(TAG, "onMenuButtonClick: ");
    }

    //3 button on clicks
    public void cart(View view){
        //send query by intern to Activity2
        Intent intent = new Intent(this, MainActivity4_cart.class);
        startActivity(intent);

    }

    public void aboutMe(View view){
        Intent intent = new Intent(this, MainActivity5_about_me.class);
        startActivity(intent);

    }

    public void search(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}