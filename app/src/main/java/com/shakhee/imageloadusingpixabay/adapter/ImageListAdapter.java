package com.shakhee.imageloadusingpixabay.adapter;

import static com.shakhee.imageloadusingpixabay.MainActivity.MA;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shakhee.imageloadusingpixabay.ImageViewActivity;
import com.shakhee.imageloadusingpixabay.R;
import com.shakhee.imageloadusingpixabay.model.Item;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    Context mContext;
    List<Item> mItemList;
    RequestOptions requestOptions=new RequestOptions();

    public static  String file_name_path;
    public static  String createdDate;
    public ImageListAdapter(Activity ma, List<Item> itemArrayList) {
        mContext=ma;
        mItemList=itemArrayList;
    }

    @NonNull
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ImageListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.ViewHolder holder, int position) {
        final Item item = mItemList.get(position);

        if(item!=null){
            System.out.println("sOrder............commants.........."+item.getComments());
            holder.name.setText(item.getTags().toUpperCase());
            holder.commants.setText(item.getComments());
            holder.like.setText(item.getLikes());
            String imageUrl = item.getImageUrl();

            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl).into(holder.mImageView);

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, imageUrl);
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    mContext.startActivity(shareIntent);
                }
            });
            holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downloadFile(imageUrl);
                }
            });

            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MA, ImageViewActivity.class);
                    intent.putExtra("imageUrl",item.getImageUrl());
                    intent.putExtra("imageName",item.getTags());
                    intent.putExtra("weight",item.getWebformatWidth());
                    intent.putExtra("height",item.getWebformatHeight());
                    intent.putExtra("comments",item.getComments());
                    intent.putExtra("likes",item.getLikes());
                    intent.putExtra("id",item.getId());
                    intent.putExtra("download",item.getDownloads());
                    intent.putExtra("views",item.getViews());
                    intent.putExtra("user",item.getUser());
                    intent.putExtra("collection",item.getCollections());


                    intent.putExtra("imageDetailsList",(Serializable) mItemList);
                    mContext.startActivity(intent);

                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView share,like,commants,name,download;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.imageName);
            share=itemView.findViewById(R.id.share);
            like=itemView.findViewById(R.id.likes);
            commants=itemView.findViewById(R.id.comments);
            download=itemView.findViewById(R.id.download);

        }
    }

    public void downloadFile(String uRl) {
               /* File direct = new File(Environment.getExternalStorageDirectory()
                        + "/PixabayImage");
*/
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        Calendar calendar1 = Calendar.getInstance();
        createdDate = simpleDateFormat1.format(calendar1.getTime());
        final String fileName = "pixabay_iamge"+"_"+createdDate;
        file_name_path = fileName + ".jpg";
        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File direct = new File(pdfPath, file_name_path);


        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalFilesDir(mContext, file_name_path, fileName);


        mgr.enqueue(request);

    }
}
