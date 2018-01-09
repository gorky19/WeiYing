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

/**
 * Created by ZhangTAO on 2018/1/1.
 */

public class Huan_MyAdapter extends RecyclerView.Adapter<Huan_MyAdapter.ViewHolder>{
    private Context context;
    private List<RootDataBean.RetBean.ListBean> alist;
    public Huan_MyAdapter(Context context, List<RootDataBean.RetBean.ListBean> list) {
        this.context = context;
        this.alist = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View see = View.inflate(context, R.layout.see_recy_item, null);
        ViewHolder see_vh = new ViewHolder(see);
        return see_vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String title = alist.get(position).getTitle();
        if(title.contains("《")) {
            String substring = title.substring(title.indexOf("《"), title.lastIndexOf("》") + 1);
            holder.mov_tit.setText(substring);
        }else {
            holder.mov_tit.setText("《"+title+"》");
        }
        holder.mov_img.setImageURI(alist.get(position).getPic());
        holder.desc.setText(alist.get(position).getDescription());
        holder.mov_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mitem != null) {
                    mitem.setItemClicked(v,position);
                }
            }
        });
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mov_tit;
        private SimpleDraweeView mov_img;
        private TextView desc;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            mov_tit = itemView.findViewById(R.id.mov_tit);
            mov_img = itemView.findViewById(R.id.mov_img);
            desc = itemView.findViewById(R.id.mov_desc);
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
