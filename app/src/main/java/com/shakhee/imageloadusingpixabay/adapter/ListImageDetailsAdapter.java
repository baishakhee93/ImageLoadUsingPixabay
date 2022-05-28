package com.shakhee.imageloadusingpixabay.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shakhee.imageloadusingpixabay.R;
import com.shakhee.imageloadusingpixabay.model.Item;

import java.util.List;

public class ListImageDetailsAdapter extends ArrayAdapter<Item> {
    private int i;
    private Context context;
    String id;
    RequestOptions requestOptions=new RequestOptions();

    public ListImageDetailsAdapter(Context mContext, List<Item> mItemList, int i, String id) {
        super(mContext, 0, mItemList);
        this.context = mContext;
        this.i = i;
        this.id=id;

    }

    @SuppressLint("CheckResult")
    @Override
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final Item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_details_listview, parent, false);
        }

        ImageView mImageView=convertView.findViewById(R.id.imageView);
        TextView imageName=convertView.findViewById(R.id.imageName);
        TextView webformatWidth=convertView.findViewById(R.id.webformatWidth);
        TextView webformatHeight=convertView.findViewById(R.id.webformatHeight);
        TextView  downloads=convertView.findViewById(R.id.downloads);
        TextView  comments=convertView.findViewById(R.id.comments);
        TextView  views=convertView.findViewById(R.id.views);
        TextView  likes=convertView.findViewById(R.id.likes);
        TextView collections=convertView.findViewById(R.id.collections);
        TextView user_id=convertView.findViewById(R.id.user_id);
        TextView user=convertView.findViewById(R.id.user);


        if(item!=null) {
            if (id.equalsIgnoreCase(item.getId()) ) {
                String imageUrl = item.getImageUrl();

                Glide.with(context)
                        .setDefaultRequestOptions(requestOptions)
                        .load(imageUrl).into(mImageView);
                imageName.setText(item.getTags());
                webformatWidth.setText(item.getWebformatWidth());
                webformatHeight.setText(item.getWebformatHeight());
                downloads.setText(item.getDownloads());
                views.setText(item.getViews());
                likes.setText(item.getLikes());
                comments.setText(item.getComments());
                collections.setText(item.getCollections());
                user.setText(item.getUser());
            }
        }
        return convertView;
    }
}
