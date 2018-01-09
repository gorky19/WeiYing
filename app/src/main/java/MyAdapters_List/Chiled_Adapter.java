package MyAdapters_List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.RootBean;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class Chiled_Adapter extends RecyclerView.Adapter<Chiled_Adapter.ViewHolder>{
    private Context context;
    private List<RootBean.RetBean.ListBean.ChildListBean> chiled_alist;
    public Chiled_Adapter(Context context, List<RootBean.RetBean.ListBean.ChildListBean> childList) {
        this.context = context;
        this.chiled_alist = childList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View chiled_items = View.inflate(context, R.layout.chiled_item, null);
        ViewHolder chiled_vh = new ViewHolder(chiled_items);
        return chiled_vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.simple_icons.setImageURI(chiled_alist.get(position).getPic());
        holder.chiled_movic_titles.setText(chiled_alist.get(position).getTitle());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monitemclick.setItemClicked(view,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(chiled_alist == null) {
            return 0;
        }
        return chiled_alist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simple_icons;
        private TextView chiled_movic_titles;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            this.root = itemView;
            simple_icons = itemView.findViewById(R.id.simple_icons);
            chiled_movic_titles = itemView.findViewById(R.id.chiled_movis_titles);
        }
    }
    private onItemclick monitemclick;
    public void setOnItemClick(onItemclick onItemClick) {
        this.monitemclick = onItemClick;
    }
    public interface onItemclick {
        void setItemClicked(View view,int pos);
    }
}
