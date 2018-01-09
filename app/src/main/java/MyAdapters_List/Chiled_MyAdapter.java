package MyAdapters_List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.RootDataBean;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Zhuan_chiled;

/**
 * Created by ZhangTAO on 2017/12/31.
 */

public class Chiled_MyAdapter extends RecyclerView.Adapter<Chiled_MyAdapter.ViewHolder>{
    private Context context;
    private List<RootDataBean.RetBean.ListBean> alist;
    public Chiled_MyAdapter(Zhuan_chiled zhuan_chiled, List<RootDataBean.RetBean.ListBean> list) {
        this.context = zhuan_chiled;
        this.alist = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View chiled = View.inflate(context, R.layout.zhuan_chiled_item, null);
        ViewHolder chiled_vh = new ViewHolder(chiled);
        return chiled_vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.desc.setText(alist.get(position).getDescription());
        holder.zhuan_img.setImageURI(alist.get(position).getPic());
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
        private TextView desc;
        private SimpleDraweeView zhuan_img;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            desc = itemView.findViewById(R.id.chiled_zhuan_text);
            zhuan_img = itemView.findViewById(R.id.chiled_zhuan_img);
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
