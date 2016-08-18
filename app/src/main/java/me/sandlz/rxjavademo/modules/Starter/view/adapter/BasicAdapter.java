package me.sandlz.rxjavademo.modules.Starter.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.rxjavademo.R;

/**
 * Created by liuzhu on 16/8/17.
 * Description :
 * Usage :
 */
public class BasicAdapter extends BaseAdapter {

    private List<String> data;
    private LayoutInflater layoutInflater;

    public BasicAdapter(@NonNull Context context, @NonNull List<String> d) {
        if (null == d) {
            data = new ArrayList<>();
        }else {
            this.data = d;
        }
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_basic_item, null);
            holder.tv_key = (TextView) convertView.findViewById(R.id.basic_item_key);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (null != data.get(i))
            holder.tv_key.setText(data.get(i));
        return convertView;
    }

    private class ViewHolder {
        TextView tv_key;
    }
}
