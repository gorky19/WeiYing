package MyAdapters_List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.SelectBean;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Select_Activity;

/**
 * Created by ZhangTAO on 2018/1/2.
 */

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder>{
    private Context context;
    private List<SelectBean.RetBean.ListBean> alist;
    public SelectAdapter(Select_Activity select_activity, List<SelectBean.RetBean.ListBean> list) {
        this.context = select_activity;
        this.alist = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.zhuan_chiled_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imgs.setImageURI(alist.get(position).getPic());
        holder.titles.setText(alist.get(position).getTitle());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mitem != null) {
                    mitem.setItemClicked(v,position);
                }
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgs;
        private TextView titles;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            imgs = itemView.findViewById(R.id.chiled_zhuan_img);
            titles = itemView.findViewById(R.id.chiled_zhuan_text);
        }
    }
    private onItemClick mitem;
    public void setItemOnClick(onItemClick items) {
        this.mitem = items;
    }
    public interface onItemClick {
        void setItemClicked(View v,int pos);
    }
}
