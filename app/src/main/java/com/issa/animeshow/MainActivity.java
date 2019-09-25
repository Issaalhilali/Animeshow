package com.issa.animeshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.issa.animeshow.scraper.SiteTools;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.toolbox.Volley.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ProgressDialog mProgressDialog;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MainRecyclerViewAdapter mainRecyclerViewAdapter;
    private List<List<String>> animeList = new ArrayList<>();
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "http://www.anime1.com/ongoing/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.mainRecyclerView);
        mainRecyclerViewAdapter=new MainRecyclerViewAdapter(animeList,this);
        recyclerView.setAdapter(mainRecyclerViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        //Gets all anime present in anime1 site.
        requestQueue = Volley.newRequestQueue(this);

        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,"Got Response");

                animeList.addAll(SiteTools.getOngoingAnimeList(response));
                progressBar.setVisibility(View.INVISIBLE);
                mainRecyclerViewAdapter.notifyItemRangeInserted(0,animeList.size());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error in connection");
            }
        });
        requestQueue.add(stringRequest);
        Log.d(TAG,"Made the request");

    }



}
