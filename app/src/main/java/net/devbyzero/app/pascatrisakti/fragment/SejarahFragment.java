package net.devbyzero.app.pascatrisakti.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import net.devbyzero.app.pascatrisakti.PascaDB;
import net.devbyzero.app.pascatrisakti.R;

/**
 * Created by anta40 on 17-May-17.
 */

public class SejarahFragment extends Fragment {

    private WebView wv;

    public static SejarahFragment newInstance(String param1, String param2){
        SejarahFragment fragment = new SejarahFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_html, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Sejarah");
        wv = (WebView) view.findViewById(R.id.wv);
        wv.loadUrl("file:///android_asset/www/sejarah.html");


        return view;
    }

}
