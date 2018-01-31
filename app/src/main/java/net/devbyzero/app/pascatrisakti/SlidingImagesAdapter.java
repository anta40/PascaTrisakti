package net.devbyzero.app.pascatrisakti;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by anta40 on 30-May-17.
 */

public class SlidingImagesAdapter extends PagerAdapter {

    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImagesAdapter(Context context, ArrayList<Integer> IMAGES){
        this.context = context;
        this.IMAGES = IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, container, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.sliding_image);

        imageView.setImageResource(IMAGES.get(position));
        container.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
