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
import zhangtao.bwie.com.demo.Select_Activity;

/**
 * Created by ZhangTAO on 2017/12/31.
 */

public class Tui_MyAdapter extends RecyclerView.Adapter<Tui_MyAdapter.ViewHolder>{
    private Context context;
    private List<RootBean.RetBean.ListBean.ChildListBean> alist;

    public Tui_MyAdapter(Select_Activity select_activity, List<RootBean.RetBean.ListBean.ChildListBean> alist_list) {
        this.context = select_activity;
        this.alist = alist_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View zhuan = View.inflate(context, R.layout.zhuan_item, null);
        ViewHolder zhuan_vh = new ViewHolder(zhuan);
        return zhuan_vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(alist.get(position).getTitle());
        holder.imgs.setImageURI(alist.get(position).getPic());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mitem !=null) {
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
        private TextView title;
        private SimpleDraweeView imgs;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            title = itemView.findViewById(R.id.zhuan_text);
            imgs = itemView.findViewById(R.id.zhuan_img);
        }
    }
    private onItemClick mitem;
    public void setItemOnClick(onItemClick items) {
        this.mitem = items;
    }
    public interface onItemClick {
        void setItemClicked(View v, int pos);
    }
}
