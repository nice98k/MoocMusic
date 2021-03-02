package com.example.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imoocmusic.Models.MusicModel;
import com.example.imoocmusic.R;
import com.example.imoocmusic.activities.PlayMusicActivity;

import java.util.List;

/******
 * @name imoocMusic
 * @class name：com.example.imoocmusic.adapters
 * @class describe
 * @Email :2532937079@qq.com
 * @time 2021/2/22 20:15
 * @change
 * @chang time
 * @class describe
 ******/
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mcontext;
    private View  mView;
    private RecyclerView mRv;
    private boolean isCalculate=false;
    private List<MusicModel> mDataSource;


    public MusicListAdapter(Context context,RecyclerView recyclerView,List<MusicModel> dataSource) {
        mcontext=context;
        mRv=recyclerView;
        mDataSource=dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView=LayoutInflater.from(mcontext).inflate(R.layout.item_list_music,parent,false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecycleViewHeight();

        final MusicModel musicModel=mDataSource.get(position);


        Glide.with(mcontext)
                .load(musicModel.getPoster())
                .into(holder.ivIcon);

        holder.TvName.setText(musicModel.getName());
        holder.TvAuthor.setText(musicModel.getAuthor());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.MUSIC_ID,musicModel.getMusicId());

                mcontext.startActivity(intent);
            }
        });

    }

    private void setRecycleViewHeight(){


        if(isCalculate||mRv==null){
            return ;
        }

        isCalculate=true;

//        获取itemView的高度
        RecyclerView.LayoutParams itemViewlp= (RecyclerView.LayoutParams) mView.getLayoutParams();
//        获取itemView的数量
        int itemCount=getItemCount();

        int recyclerViewHeight=itemViewlp.height*itemCount;

        LinearLayout.LayoutParams rvlp= (LinearLayout.LayoutParams) mRv.getLayoutParams();

        rvlp.height=recyclerViewHeight;

        mRv.setLayoutParams(rvlp);


    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemView;
        TextView TvName,TvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ivIcon=itemView.findViewById(R.id.iv_icon);
            TvName=itemView.findViewById(R.id.tv_name);
            TvAuthor=itemView.findViewById(R.id.tv_author);
        }
    }

}
