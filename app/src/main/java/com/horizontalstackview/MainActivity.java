package com.horizontalstackview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.horizontalstackview.adapter.StackViewAdapter;
import com.stackview.DisplaySideViewPager;
import com.stackview.StackView;
import com.stackview.StackViewVertical;
import com.stackview.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<View> mViews;
    private ViewPagerAdapter mViewPagerAdapter;
    private DisplaySideViewPager mViewPager;
    private final List<Integer> mImagesLink = new ArrayList<>();

    {
        mImagesLink.add(R.drawable.widow);
        mImagesLink.add(R.drawable.hulk);
        mImagesLink.add(R.drawable.captain);
        mImagesLink.add(R.drawable.thor);
        mImagesLink.add(R.drawable.ironman);
    }

    /*********************************************************************/
    /**************************** Activity *******************************/
    /**
     * *****************************************************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViewPager();
        initStackView();
    }

    private void initStackView() {
        StackViewVertical stackViewVertical = (StackViewVertical) findViewById(R.id.stackViewVertical);
        stackViewVertical.setAdapter(new StackViewAdapter(MainActivity.this, mImagesLink));
    }

    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewLeft = inflater.inflate(R.layout.view_page_item_one, null);
        View viewRight = inflater.inflate(R.layout.view_page_item_two, null);

        StackView stackViewOrigin = (StackView) viewRight.findViewById(R.id.stack_view_origin);
        stackViewOrigin.setAdapter(new StackViewAdapter(this, mImagesLink));


        mViews = new ArrayList<View>();
        mViews.add(viewLeft);
        mViews.add(viewRight);
        mViewPager = (DisplaySideViewPager) findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(mViews);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}