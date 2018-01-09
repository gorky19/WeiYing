package MyAdapters_List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import Bean.CommentaryBean;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/16.
 */

public class MyAdapter_pingjia extends RecyclerView.Adapter<MyAdapter_pingjia.ViewHolder>{
    private Context context;
    private List<CommentaryBean.RetBean.ListBean> alist;
    public MyAdapter_pingjia(FragmentActivity activity, List<CommentaryBean.RetBean.ListBean> pingjia) {
        this.context = activity;
        this.alist = pingjia;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pingjia = View.inflate(context, R.layout.pingjia_item, null);
        ViewHolder pingjiavh = new ViewHolder(pingjia);
        return pingjiavh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.user_img.setImageURI(alist.get(position).getUserPic());
        holder.user_name.setText(alist.get(position).getPhoneNumber());
        holder.user_time.setText(alist.get(position).getTime());
        holder.user_manager.setText(alist.get(position).getMsg());
        holder.user_phonenum.setText(alist.get(position).getLikeNum()+"");
    }
    @Override
    public int getItemCount() {
        if(alist == null) {
            return  0;
        }
        return alist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView user_img;
        private TextView user_name;
        private TextView user_time;
        private TextView user_manager;
        private TextView user_phonenum;
        public ViewHolder(View itemView) {
            super(itemView);
            user_img = itemView.findViewById(R.id.comm_icons);
            user_name = itemView.findViewById(R.id.comm_name);
            user_time = itemView.findViewById(R.id.comm_tiem);
            user_manager = itemView.findViewById(R.id.comm_manager);
            user_phonenum = itemView.findViewById(R.id.list_num);
        }
    }
}
