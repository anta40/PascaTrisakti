package net.devbyzero.app.pascatrisakti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import net.devbyzero.app.pascatrisakti.fragment.NewsFragment;

/**
 * Created by anta40 on 23-May-17.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {
    public TextView newsTitle;
    public String newsURL;

    public NewsViewHolder(final View itemView){
        super(itemView);
        newsTitle = (TextView) itemView.findViewById(R.id.news_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               Context ctxt = itemView.getContext();
                Intent in = new Intent(ctxt, NewsWebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("NEWS_URL", newsURL);
                in.putExtras(bundle);
                ctxt.startActivity(in);
               //Toast.makeText(ctxt, "News URL: "+newsURL, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
