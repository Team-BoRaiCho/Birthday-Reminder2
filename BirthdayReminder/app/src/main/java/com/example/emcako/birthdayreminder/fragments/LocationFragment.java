package com.example.emcako.birthdayreminder.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.emcako.birthdayreminder.R;

public class LocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_locations, container, false);

//        WebView browser = (WebView) rootView.findViewById(R.id.webview);
//        browser.setWebViewClient(new WebViewClient());
//        browser.loadUrl("https://www.google.bg/maps/search/giftshop/@42.673245,23.3290978,15z?hl=en");

        return rootView;
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            WebView browser = (WebView) getView().findViewById(R.id.webview);
//            browser.setWebViewClient(new WebViewClient());
//            browser.loadUrl("https://www.google.bg/maps/search/giftshop/@42.673245,23.3290978,15z?hl=en");
//        }
//        else {
//        }
//    }
}
