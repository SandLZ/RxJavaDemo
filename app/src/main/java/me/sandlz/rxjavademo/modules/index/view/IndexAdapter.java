package me.sandlz.rxjavademo.modules.index.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.modules.index.model.IndexEntity;

/**
 * Created by liuzhu on 16/8/15.
 * Description :
 * Usage :
 */
public class IndexAdapter extends RecyclerView.Adapter{

    private List<IndexEntity> data;
    private OnClickRecyclerItemListener listener;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_index_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        IndexEntity entity = data.get(position);
        viewHolder.tv_func.setText(entity.getFunction());
        viewHolder.tv_desc.setText(entity.getDescription());
        viewHolder.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != listener) {
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<IndexEntity> data) {
        if (null != data) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.index_item_func_text)
        TextView tv_func;
        @Bind(R.id.index_item_desc_text)
        TextView tv_desc;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickRecyclerItemListener {
        void onClick(int position);
    }

    public void registerListener(OnClickRecyclerItemListener listener) {
        this.listener = listener;
    }

    public void unRegisListener() {
        this.listener = null;
    }

}
