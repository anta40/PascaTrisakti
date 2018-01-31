package net.devbyzero.app.pascatrisakti;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.devbyzero.app.pascatrisakti.data.News;

import java.util.List;

/**
 * Created by anta40 on 23-May-17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private final List<News> newsList;

    public NewsListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.news_card, viewGroup, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int i) {
        holder.newsTitle.setText(newsList.get(i).getTitle());
        holder.newsURL = newsList.get(i).getUrl();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
