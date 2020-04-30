package com.e.ppb.ui.kategori;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.ppb.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KategoriFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kategori, container, false);
    }
}