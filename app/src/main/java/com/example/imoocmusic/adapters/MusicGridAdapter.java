package com.example.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imoocmusic.Models.AlbumModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.activities.AlbumListActivity;

import java.util.List;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.adapters
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/21 21:14
 * @change
 * @chang time
 * @class describe
 ******/
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private Context mContext;

    private List<AlbumModel> mDataSource;

    public MusicGridAdapter(Context context, List<AlbumModel> dataSource){
        mContext=context;
        this.mDataSource=dataSource;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemView;
        TextView mTvPlayNum,mTvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ivIcon=itemView.findViewById(R.id.iv_icon);
            mTvPlayNum=itemView.findViewById(R.id.tv_playNum);
            mTvName=itemView.findViewById(R.id.tv_name);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AlbumModel albumModel=mDataSource.get(position);


        Glide.with(mContext)
                .load(albumModel.getPoster())
                .into(holder.ivIcon);

        holder.mTvPlayNum.setText(albumModel.getPlayNum());
        holder.mTvName.setText(albumModel.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, AlbumListActivity.class);
                intent.putExtra(AlbumListActivity.ALBUM_ID,albumModel.getAlbumId());
                mContext.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
}
