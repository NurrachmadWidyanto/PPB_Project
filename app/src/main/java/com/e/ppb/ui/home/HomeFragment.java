package com.e.ppb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.e.ppb.BebekActivity;
import com.e.ppb.Berita1Activity;
import com.e.ppb.Berita2Activity;
import com.e.ppb.Berita3Activity;
import com.e.ppb.BeritaActivity;
import com.e.ppb.DestinasiActivity;
import com.e.ppb.DownloadActivity;
import com.e.ppb.EkonomiActivity;
import com.e.ppb.EventActivity;
import com.e.ppb.GaleriActivity;
import com.e.ppb.PartnerActivity;
import com.e.ppb.ProfilActivity;
import com.e.ppb.R;
import com.e.ppb.TerasiActivity;
import com.e.ppb._sliders.FragmentSlider;
import com.e.ppb._sliders.SliderIndicator;
import com.e.ppb._sliders.SliderPagerAdapter;
import com.e.ppb._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    GridLayout gridLayout;
    CardView cardview, cardview2, cardview3, cardview4, cardview5, cardview6;

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

        mLinearLayout = rootView.findViewById(R.id.pagesContainer);
        //setupSlider();
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
                        Intent intent = new Intent(getActivity(), BebekActivity.class);
                        startActivity(intent);
                    }
                    else if (a==2) {
                        Intent intent = new Intent(getActivity(), BebekActivity.class);
                        startActivity(intent);
                    }
                    else if (a==3) {
                        Intent intent = new Intent(getActivity(), TerasiActivity.class);
                        startActivity(intent);
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

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-2.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-3.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-4.jpg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}