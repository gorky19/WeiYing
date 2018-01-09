package MyAdapters_List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/31.
 */

public class Zhuan_MyAdapter extends RecyclerView.Adapter<Zhuan_MyAdapter.ViewHolder>{
    private Context context;
    private List<String> alist_tit;
    private List<String> alist_img;
    public Zhuan_MyAdapter(FragmentActivity activity, List<String> zhuan_title, List<String> zhuan_img) {
        this.context = activity;
        this.alist_tit = zhuan_title;
        this.alist_img = zhuan_img;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View zhuan = View.inflate(context, R.layout.zhuan_item, null);
        ViewHolder zhuan_vh = new ViewHolder(zhuan);
        return zhuan_vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(alist_tit.get(position));
        holder.imgs.setImageURI(alist_img.get(position));
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
        if(alist_tit == null || alist_img == null) {
            return 0;
        }
        return alist_img.size();
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
        void setItemClicked(View v,int pos);
    }
}
