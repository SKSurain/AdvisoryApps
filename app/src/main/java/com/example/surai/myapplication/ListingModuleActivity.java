package com.example.surai.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.surai.myapplication.model.Listing;
import com.example.surai.myapplication.model.ListingResponse;
import com.example.surai.myapplication.storage.SharedPrefManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingModuleActivity extends AppCompatActivity {
    private String token = "";
    private int id = -1;
    private RecyclerView rvListing;
    private RecyclerView.Adapter adapter;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_activity);

        this.rvListing = (RecyclerView) findViewById(R.id.rvListing);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.rvListing.setLayoutManager(mLayoutManager);

        myToolbar.setTitle(R.string.listing_module_title);
        setSupportActionBar(myToolbar);

        if (SharedPrefManager.getInstance(this).getToken() != null) {
            token = SharedPrefManager.getInstance(this).getToken();
            id = SharedPrefManager.getInstance(this).getId();
        }

        Call<ListingResponse> call = RetrofitClient.getInstance().getApi().getListing(id, token.trim());
        call.enqueue(new Callback<ListingResponse>() {
            @Override
            public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
                try {
                    ListingResponse listingResponse = response.body();
                    populateRecyclerView(listingResponse.getListing());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListingResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: This is not working");
                t.printStackTrace();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(ListingModuleActivity.this).clear();
                Toast.makeText(ListingModuleActivity.this, "Logout successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListingModuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void populateRecyclerView(ArrayList<Listing> listing) {
        adapter = new ListingAdapter(listing);
        rvListing.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
    }
}
