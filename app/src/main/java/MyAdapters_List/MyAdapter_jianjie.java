package MyAdapters_List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.VideoBean;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public class MyAdapter_jianjie extends RecyclerView.Adapter<MyAdapter_jianjie.ViewHolder>{
    private Context context;
    private List<VideoBean.RetBean.ListBean.ChildListBean> alist;
    public MyAdapter_jianjie(FragmentActivity player1, List<VideoBean.RetBean.ListBean.ChildListBean> jianli) {
        this.context = player1;
        this.alist = jianli;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View jianli = View.inflate(context, R.layout.jianjie_item, null);
        ViewHolder jianlivh = new ViewHolder(jianli);
        return jianlivh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.more_img.setImageURI(alist.get(position).getPic());
        holder.more_titles.setText(alist.get(position).getTitle());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclick.setItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(alist == null) {
            return 0;
        }
        return alist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView more_img;
        private TextView more_titles;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            more_img = itemView.findViewById(R.id.sim_icons);
            more_titles = itemView.findViewById(R.id.movic_name);
        }
    }
    private OnItemClick mclick;
    public void setOnItemClicked(OnItemClick item) {
        this.mclick = item;
    }
    public interface OnItemClick {
        void setItemClick(View v,int pos);
    }
}
