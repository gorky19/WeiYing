package MyAdapters_List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Bean.RootBean;
import zhangtao.bwie.com.demo.Player_activity;
import zhangtao.bwie.com.demo.R;

/**
 * Created by ZhangTAO on 2017/12/15.
 */

public class FatherAdapter extends RecyclerView.Adapter<FatherAdapter.ViewHolder>{
    private Context context;
    private List<RootBean.RetBean.ListBean> alist;
    private Chiled_Adapter chiled_adapter;

    public FatherAdapter(Context context, List<RootBean.RetBean.ListBean> father_data) {
        this.context = context;
        this.alist = father_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View father_layout = View.inflate(context, R.layout.recy_item, null);
        ViewHolder father_vh = new ViewHolder(father_layout);
        return father_vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.movic_titles.setText(alist.get(position).getTitle());
        chiled_adapter = new Chiled_Adapter(context,alist.get(position).getChildList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.chiled_recy.setLayoutManager(linearLayoutManager);
        holder.chiled_recy.setAdapter(chiled_adapter);
        chiled_adapter.setOnItemClick(new Chiled_Adapter.onItemclick() {
            @Override
            public void setItemClicked(View view, int pos) {
                Intent intent = new Intent(context, Player_activity.class);
                String dataId = alist.get(position).getChildList().get(pos).getDataId();
                intent.putExtra("key",dataId);
                context.startActivity(intent);
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
        private TextView movic_titles;
        private RecyclerView chiled_recy;
        public ViewHolder(View itemView) {
            super(itemView);
            movic_titles = itemView.findViewById(R.id.movis_titles);
            chiled_recy = itemView.findViewById(R.id.chiled_recy);
        }
    }
}
