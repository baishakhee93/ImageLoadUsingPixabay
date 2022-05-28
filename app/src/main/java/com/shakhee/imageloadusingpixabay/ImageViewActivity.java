package com.shakhee.imageloadusingpixabay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shakhee.imageloadusingpixabay.adapter.ListImageDetailsAdapter;
import com.shakhee.imageloadusingpixabay.model.Item;

import java.util.List;

public class ImageViewActivity extends AppCompatActivity {
    public static List<Item> mItemList;
    public static  Context mContext;
    public static ImageView mImageView;
    public static  TextView imageName,webformatWidth,webformatHeight,views,downloads,comments,likes,collections,user_id,user;
    public static  String id,imageUrl,imageNames,webformatWidths,webformatHeights,viewss,downloadss,commentss,likess,collectionss,user_ids,users;
    RequestOptions requestOptions;
    ListImageDetailsAdapter mListImageDetailsAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mContext=this;
        mListView=findViewById(R.id.list_item);
        mImageView=findViewById(R.id.imageView);
        imageName=findViewById(R.id.imageName);
        webformatWidth=findViewById(R.id.webformatWidth);
        webformatHeight=findViewById(R.id.webformatHeight);
        downloads=findViewById(R.id.downloads);
        comments=findViewById(R.id.comments);
        views=findViewById(R.id.views);
        likes=findViewById(R.id.likes);
        collections=findViewById(R.id.collections);
        user_id=findViewById(R.id.user_id);
        user=findViewById(R.id.user);
        requestOptions=new RequestOptions();
        gettingData();
        displayData();
    }


    private void gettingData() {
        try {
            System.out.println("Print...........getdata....0000............");

            Intent intent = getIntent();
            if (intent != null) {


                if (intent.getSerializableExtra("imageDetailsList") != null) {
                    imageUrl=intent.getStringExtra("imageUrl");
                    imageNames=intent.getStringExtra("imageName");
                    webformatWidths=intent.getStringExtra("weight");
                    webformatHeights=intent.getStringExtra("height");
                    commentss=intent.getStringExtra("comments");
                    downloadss=intent.getStringExtra("download");
                    likess=intent.getStringExtra("likes");
                    id=intent.getStringExtra("id");
                    viewss=intent.getStringExtra("views");
                    users=intent.getStringExtra("user");
                    collectionss=intent.getStringExtra("collection");

                   mItemList = (List<Item>) intent.getSerializableExtra("imageDetailsList");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void displayData() {

        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(imageUrl).into(mImageView);
        imageName.setText(imageNames);
        webformatWidth.setText(webformatWidths);
        webformatHeight.setText(webformatHeights);
        comments.setText(commentss);
         views.setText(viewss);
         likes.setText(likess);
         downloads.setText(downloadss);
         user.setText(users);
         collections.setText(collectionss);
       /* mListImageDetailsAdapter=new ListImageDetailsAdapter(mContext,mItemList,0,id);
        mListView.setAdapter(mListImageDetailsAdapter);*/


    }


}