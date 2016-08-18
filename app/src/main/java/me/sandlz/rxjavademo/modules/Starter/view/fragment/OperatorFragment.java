package me.sandlz.rxjavademo.modules.Starter.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.core.view.BaseFragment;
import me.sandlz.rxjavademo.utils.LogUtil;

/**
 * @author zliu
 * @Date 16/8/18 19:10
 * @Describe 操作符
 *
 */
public class OperatorFragment extends BaseFragment {

    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_operator, container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
