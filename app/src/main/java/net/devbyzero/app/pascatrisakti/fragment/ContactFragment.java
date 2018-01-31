package net.devbyzero.app.pascatrisakti.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;

import net.devbyzero.app.pascatrisakti.R;

/**
 * Created by Cipta-NB on 22-Jan-18.
 */

public class ContactFragment extends Fragment {

    private WebView wv;

    public static ContactFragment newInstance(String param1, String param2){
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        wv = (WebView) view.findViewById(R.id.wv);
        wv.loadUrl("file:///android_asset/www/contact.html");

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contact Us");

        return view;

    }
}
