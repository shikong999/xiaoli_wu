package com.wu.wucheng.xiaoli_wu.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wu.wucheng.xiaoli_wu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Guanxiguanli extends Fragment {
    public static final String TAG=Guanxiguanli.class.getSimpleName();

    public Guanxiguanli() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guanxiguanli, container, false);
    }

}
