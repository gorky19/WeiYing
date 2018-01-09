package MyAdapters_List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.LishiBean;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2018/1/4.
 */

public class MyAdapter_Lishi extends RecyclerView.Adapter<MyAdapter_Lishi.ViewHolder>{
    private Context context;
    private List<LishiBean> alist;
    public MyAdapter_Lishi(FragmentActivity frag4, List<LishiBean> dbHelper_data) {
        this.context = frag4;
        this.alist = dbHelper_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lishi_lay = View.inflate(context, R.layout.lishi_recy_item, null);
        ViewHolder viewHolder = new ViewHolder(lishi_lay);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.icons.setImageURI(alist.get(position).getIcons());
        holder.titles.setText(alist.get(position).getTitles());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mitemclick != null) {
                    mitemclick.onItemClicked(v,position);
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
            icons = itemView.findViewById(R.id.lishi_img);
            titles = itemView.findViewById(R.id.lishi_text);
        }
    }
    private onitemclick mitemclick;
    public void setItemClickeds(onitemclick itemClickeds) {
        this.mitemclick = itemClickeds;
    }
    public interface onitemclick {
        void onItemClicked(View view, int pos);
    }
}
