package net.devbyzero.app.pascatrisakti.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;

import net.devbyzero.app.pascatrisakti.NewsListAdapter;
import net.devbyzero.app.pascatrisakti.R;
import net.devbyzero.app.pascatrisakti.ViewPagerAdapter;
import net.devbyzero.app.pascatrisakti.WrapContentViewPager;
import net.devbyzero.app.pascatrisakti.data.NewsProvider;

import me.relex.circleindicator.CircleIndicator;


public class NewsFragment extends Fragment {

    private RecyclerView recView;
    private NewsListAdapter adapter;
    private WrapContentViewPager vPager;
    private PagerAdapter pAdapter;

    private CircleIndicator llDots;

    public static NewsFragment newInstance(String param1, String param2){
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recView = (RecyclerView) view.findViewById(R.id.news_list);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        vPager = (WrapContentViewPager) view.findViewById(R.id.view_pager);
        llDots = (CircleIndicator) view.findViewById(R.id.lldots);
        int[] banners = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
        pAdapter = new ViewPagerAdapter(getActivity(), banners);
        vPager.setAdapter(pAdapter);
        llDots.setViewPager(vPager);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Berita");

        adapter = new NewsListAdapter(new NewsProvider().getNews());
        recView.setAdapter(adapter);
        return view;
    }


}
