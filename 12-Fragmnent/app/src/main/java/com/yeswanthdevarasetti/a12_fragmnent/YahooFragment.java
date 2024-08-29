package com.yeswanthdevarasetti.a12_fragmnent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class YahooFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_yahoo2,container,false);
        WebView wv1=view.findViewById(R.id.webgoogle);
        wv1.loadUrl("https://www.yahoo.com/");
        return view;

    }