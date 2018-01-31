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

public class PendaftaranFragment extends Fragment {
    private WebView wv;

    public static PendaftaranFragment newInstance(String param1, String param2){
        PendaftaranFragment fragment = new PendaftaranFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_html, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pendaftaran");
        wv = (WebView) view.findViewById(R.id.wv);
        wv.loadUrl("file:///android_asset/www/pendaftaran.html");

        return view;
    }
}
