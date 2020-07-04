package com.e.ppb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.e.ppb.TampilanActivity;
import com.e.ppb.Berita1Activity;
import com.e.ppb.Berita2Activity;
import com.e.ppb.Berita3Activity;
import com.e.ppb.BeritaActivity;
import com.e.ppb.DestinasiActivity;
import com.e.ppb.DownloadActivity;
import com.e.ppb.EkonomiActivity;
import com.e.ppb.EventActivity;
import com.e.ppb.GaleriActivity;
import com.e.ppb.List_Data;
import com.e.ppb.PartnerActivity;
import com.e.ppb.ProfilActivity;
import com.e.ppb.R;
import com.e.ppb.TerasiActivity;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    GridLayout gridLayout;
    CardView cardview, cardview2, cardview3, cardview4, cardview5, cardview6;
    private ImageView imgdestinasi;
    private TextView txtjudul, txtwaktu, txtisi;
    String TAG_NOP = "kuliner";

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    ArrayList<HashMap<String, String>> list_data;

    SliderLayout sliderLayout;
    private List<List_Data> list_dataList;

    private JsonArrayRequest request;

    private static final String HI = "http://192.168.137.1/PPB/getdata.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //sliderView = rootView.findViewById(R.id.sliderView);
        gridLayout=(GridLayout)rootView.findViewById(R.id.mainGrid);
        cardview = (CardView) rootView.findViewById(R.id.cardaction);
        cardview2 = (CardView) rootView.findViewById(R.id.cardwisata);
        cardview3 = (CardView) rootView.findViewById(R.id.cardikm);
        cardview4 = (CardView) rootView.findViewById(R.id.cardberita1);
        cardview5 = (CardView) rootView.findViewById(R.id.cardberita2);
        cardview6 = (CardView) rootView.findViewById(R.id.cardberita3);
        setSingleEvent(gridLayout);
        setCardEvent(cardview, 1);
        setCardEvent(cardview2, 2);
        setCardEvent(cardview3, 3);
        setCardEvent(cardview4, 4);
        setCardEvent(cardview5, 5);
        setCardEvent(cardview6, 6);

        ImageSlider imageSlider=rootView.findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("http://192.168.137.1/PPB/images/bebek_songkem.jpg"));
        slideModels.add(new SlideModel("http://192.168.137.1/PPB/images/terasi.jpg"));
        slideModels.add(new SlideModel("https://picsum.photos/id/892/300/200"));
        slideModels.add(new SlideModel("https://picsum.photos/id/891/300/200"));
        imageSlider.setImageList(slideModels,true);

        String url = "http://192.168.137.1/PPB/getdata.php";

        imgdestinasi = (ImageView)rootView.findViewById(R.id.songkem);
        txtjudul= (TextView)rootView.findViewById(R.id.songkemt1);
        //txtwaktu = (TextView)rootView.findViewById(R.id.);
        txtisi = (TextView)rootView.findViewById(R.id.songkemt2);

        requestQueue = Volley.newRequestQueue(getActivity());

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("image");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("id", json.getString("id"));
                        map.put("image", json.getString("image"));
                        map.put("judul", json.getString("judul"));
                        map.put("waktu", json.getString("waktu"));
                        map.put("isi", json.getString("isi"));
                        list_data.add(map);
                    }
                    Glide.with(getActivity())
                            .load(list_data.get(0).get("image"))
                            .override(600, 200)
                            .fitCenter()
                            .into(imgdestinasi);
                    txtjudul.setText(list_data.get(0).get("judul"));
                    //txttipe.setText(list_data.get(0).get("waktu"));
                    txtisi.setText(list_data.get(0).get("isi"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

        return rootView;
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        Intent intent = new Intent(getActivity(), ProfilActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==1){
                        Intent intent = new Intent(getActivity(), BeritaActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==2){
                        Intent intent = new Intent(getActivity(), EventActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==3){
                        Intent intent = new Intent(getActivity(), DestinasiActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==4){
                        Intent intent = new Intent(getActivity(), EkonomiActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==5){
                        Intent intent = new Intent(getActivity(), PartnerActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI==6){
                        Intent intent = new Intent(getActivity(), DownloadActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getActivity(), GaleriActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void setCardEvent(final CardView cardview, final Integer a) {
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (a==1) {
                        Intent intent = new Intent(getActivity(), TampilanActivity.class);
                        intent.putExtra(TAG_NOP, a);
                    }
                    else if (a==2) {
                        Intent intent = new Intent(getActivity(), TampilanActivity.class);
                        intent.putExtra(TAG_NOP, a);
                        startActivity(intent);
                    }
                    else if (a==3) {
                        Intent intent = new Intent(getActivity(), TerasiActivity.class);
                        intent.putExtra(TAG_NOP, a);
                    }
                    else if (a==4) {
                        Intent intent = new Intent(getActivity(), Berita1Activity.class);
                        startActivity(intent);
                    }
                    else if (a==5) {
                        Intent intent = new Intent(getActivity(), Berita2Activity.class);
                        startActivity(intent);
                    }
                    else if (a==6) {
                        Intent intent = new Intent(getActivity(), Berita3Activity.class);
                        startActivity(intent);
                    }
                }
            });
        }

}