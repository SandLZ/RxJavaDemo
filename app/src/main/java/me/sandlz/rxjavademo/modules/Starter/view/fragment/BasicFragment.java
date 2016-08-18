package me.sandlz.rxjavademo.modules.Starter.view.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.core.mvp.MvpFragment;
import me.sandlz.rxjavademo.modules.Starter.presenter.BasicPresenter;
import me.sandlz.rxjavademo.modules.Starter.view.Interface.IBasicView;
import me.sandlz.rxjavademo.modules.Starter.view.adapter.BasicAdapter;
import me.sandlz.rxjavademo.utils.LogUtil;

/**
 * @author zliu
 * @Date 16/8/18 19:10
 * @Describe 基础
 *
 * 执行顺序 onCreate -> onCreateView -> onViewCreated
 */
public class BasicFragment extends MvpFragment<BasicPresenter> implements IBasicView {

    @Bind(R.id.basic_list)
    ListView basic_list;
    private BasicAdapter adapter;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_basic, container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 请求数据
        initData();
    }

    /*------------------------------------------------------------------------------------*/

    private void initData() {
        mvpPresenter.getData2();
    }

    @Override
    protected BasicPresenter createPresenter() {
        return new BasicPresenter(this);
    }

    @Override
    public void callBackGetDataResult(List<String> list) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogUtil.d("当前是主线程");
        }
        adapter = new BasicAdapter(this.getActivity(),list);
        basic_list.setAdapter(adapter);
    }
}
