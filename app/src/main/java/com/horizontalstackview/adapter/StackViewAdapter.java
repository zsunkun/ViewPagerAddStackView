package com.horizontalstackview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.horizontalstackview.R;

import java.util.List;

public class StackViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mPhotos;

    public StackViewAdapter(Context context, List<Integer> photos) {
        mContext = context;
        mPhotos = photos;
    }

    /*********************************************************************/
    /**************************** BaseAdapter ****************************/
    /*********************************************************************/

    @Override
    public int getCount() {
        if(mPhotos == null){
            return 0;
        } else {
            return mPhotos.size();
        }
    }

    @Override
    public Integer getItem(int position) {
        return mPhotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.stack_view_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.mPhotoView = (ImageView) view.findViewById(R.id.slider_item_image);
            viewHolder.mCounter = (TextView) view.findViewById(R.id.slider_item_count);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.mPhotoView.setImageResource(getItem(position));
        viewHolder.mPhotoView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lyp", "on click " + position);
            }
        });
        viewHolder.mCounter.setText((position+1)+"/"+getCount());
        return view;
    }

    private final class ViewHolder {
        private  ImageView mPhotoView;
        private  TextView mCounter;
    }
}