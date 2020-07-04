package com.e.ppb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.e.ppb.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BeritaActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    RequestQueue mRequest;
    List<ModelList> mListItems;
    private final String url = "http://192.168.137.1/PPB/getdataberita.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerTemp);
        mRequest = Volley.newRequestQueue(getApplicationContext());
        mListItems = new ArrayList<>();


        request();

        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new AdapterList(mListItems,BeritaActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void request(){
        JsonArrayRequest requestImage = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("JSONResponse",response.toString());

                        for(int i=0; i < response.length(); i++){
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelList model = new ModelList();
                                model.setJudul(data.getString("judul"));
                                model.setIsi(data.getString("isi"));
                                model.setImage(data.getString("image"));
                                model.setWaktu(data.getString("waktu"));
                                model.setId(data.getString("id"));

                                mListItems.add(model);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORRequest", "Error : " + error.getMessage());
                    }
                });

        mRequest.add(requestImage);
    }
}