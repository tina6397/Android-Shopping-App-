package com.example.tina.iLOVEZappos.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.tina.iLOVEZappos.R;
import com.example.tina.iLOVEZappos.model.Product;
import com.example.tina.iLOVEZappos.model.ProductsResponse;
import com.example.tina.iLOVEZappos.Retrofit.ApiClient;
import com.example.tina.iLOVEZappos.Retrofit.ApiInterface;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";
    private final static String search_term = "";
    @Bind(R.id.tapBarMenu) TapBarMenu tapBarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        //set actionbar zappos logo
        ActionBar ab = getSupportActionBar();
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setLogo(R.drawable.zappos_logo_white);


        //make whole search field clickable
        final SearchView searchView = (SearchView)findViewById(R.id.search_field);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });


        //butterknife binding
        ButterKnife.bind(this);

    }



    @OnClick(R.id.tapBarMenu) public void onMenuButtonClick() {
        tapBarMenu.toggle();
        Log.i(TAG, "onMenuButtonClick: ");
    }


    //search button
    public void search(View view){
        Log.i(TAG, "search: click");

        SearchView searchView = (SearchView) findViewById(R.id.search_field);
        String q= (String) searchView.getQuery().toString();

        //send query by intern to Activity2
        Intent intent = new Intent(this, MainActivity2_search.class);
        intent.putExtra("search", q);
        startActivity(intent);

    }

    //on click function for the Advertisement
    public void searchAD(View view){
        //send query by intern to Activity2
        Intent intent = new Intent(this, MainActivity2_search.class);
        intent.putExtra("search", "vionic");

        startActivity(intent);

    }

    //lucky button
    public void searchLucky(View view){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        SearchView searchView = (SearchView) findViewById(R.id.search_field);
        String q= (String) searchView.getQuery().toString();


        Call<ProductsResponse> call = apiService.getSearch(q,API_KEY);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                int statusCode = response.code();
                List<Product> movies = response.body().getResults();

                if (movies.size()==0){
                    Toast.makeText(getApplicationContext(),"Sorry, no result found!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getBaseContext(), MainActivity3_detail.class);

                    intent.putExtra("product", movies.get(0));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getBaseContext().startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());


            }
        });

    }

    //button on click for cart
    public void cart(View view){
        //send query by intern to Activity2
        Intent intent = new Intent(this, MainActivity4_cart.class);
        startActivity(intent);

    }

    public void aboutMe(View view){
        Intent intent = new Intent(this, MainActivity5_about_me.class);
        startActivity(intent);

    }





}