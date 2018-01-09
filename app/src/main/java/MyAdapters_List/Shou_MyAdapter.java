package MyAdapters_List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.ShouBean;
import zhangtao.bwie.com.demo.R;
import zhangtao.bwie.com.demo.Shou_Act;

/**
 * Created by ZhangTAO on 2018/1/4.
 */

public class Shou_MyAdapter extends RecyclerView.Adapter<Shou_MyAdapter.ViewHolder>{
    private Context context;
    private List<ShouBean> alist;
    public Shou_MyAdapter(Shou_Act shou_act, List<ShouBean> dbHelper) {
        this.context = shou_act;
        this.alist = dbHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View shou = View.inflate(context, R.layout.zhuan_chiled_item, null);
        ViewHolder shou_vh = new ViewHolder(shou);
        return shou_vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
         holder.icons.setImageURI(alist.get(position).getShou_icon());
         holder.titles.setText(alist.get(position).getShou_title());
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
        private SimpleDraweeView icons;
        private TextView titles;
        private View root;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView;
            icons = itemView.findViewById(R.id.chiled_zhuan_img);
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