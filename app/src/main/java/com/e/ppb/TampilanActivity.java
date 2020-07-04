package com.e.ppb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class TampilanActivity extends AppCompatActivity {

    Toolbar toolbar;
    private ImageView imgbebek;
    private TextView txtjudul, txtjarak, txtwaktu, txtalamat, txtisi;
    private String TAG_KULINER = "kuliner", kuliner;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bebek);

        kuliner = getIntent().getStringExtra(TAG_KULINER);

        String url = "http://192.168.137.1/PPB/getdata.php";

        imgbebek = (ImageView) findViewById(R.id.bebek);
        txtjudul = (TextView) findViewById(R.id.songkemt1);
        txtjarak = (TextView) findViewById(R.id.songkemt2);
        txtwaktu = (TextView) findViewById(R.id.songkemt3);
        txtalamat = (TextView) findViewById(R.id.songkemt4);
        txtisi = (TextView) findViewById(R.id.songkemt5);

        Tampil(kuliner, url);
    }

    public void Tampil (String a, String url){
        requestQueue = Volley.newRequestQueue(TampilanActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("image");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("image", json.getString("image"));
                        map.put("judul", json.getString("judul"));
                        map.put("jarak", json.getString("jarak"));
                        map.put("waktu", json.getString("waktu"));
                        map.put("alamat", json.getString("alamat"));
                        map.put("isi", json.getString("isi"));
                        list_data.add(map);
                    }
                    Glide.with(getApplicationContext())
                            .load(list_data.get(0).get("image"))
                            .override(600, 200)
                            .fitCenter()
                            .into(imgbebek);
                    txtjudul.setText(list_data.get(0).get("judul"));
                    txtjarak.setText(list_data.get(0).get("jarak"));
                    txtwaktu.setText(list_data.get(0).get("waktu"));
                    txtalamat.setText(list_data.get(0).get("alamat"));
                    txtisi.setText(list_data.get(0).get("isi"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TampilanActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}

