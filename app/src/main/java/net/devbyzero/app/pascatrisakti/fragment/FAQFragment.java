package net.devbyzero.app.pascatrisakti.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.devbyzero.app.pascatrisakti.PascaDB;
import net.devbyzero.app.pascatrisakti.R;

/**
 * Created by anta40 on 17-May-17.
 */

public class FAQFragment extends Fragment {
    private TextView tv;
  //  private PascaDB db;

    public static FAQFragment newInstance(String param1, String param2){
        FAQFragment fragment = new FAQFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("FAQ");
        tv = (TextView) view.findViewById(R.id.teks_generik);

    //    db = new PascaDB(getContext());

        tv.setText("This section is under construction");
        tv.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }

}
