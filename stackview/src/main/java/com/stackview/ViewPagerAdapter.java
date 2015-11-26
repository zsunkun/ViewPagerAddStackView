package com.stackview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sunkun on 2015/11/24.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViews;

    public ViewPagerAdapter(List<View> views) {
        mViews = views;
    }

    public void addView(View view) {
        if (view == null || mViews == null)
            return;
        mViews.add(view);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
        mViews.remove(position);
    }
}
