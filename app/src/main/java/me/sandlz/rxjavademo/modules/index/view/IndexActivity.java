package me.sandlz.rxjavademo.modules.index.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.core.mvp.MvpActivity;
import me.sandlz.rxjavademo.modules.index.model.IndexEntity;
import me.sandlz.rxjavademo.modules.index.presenter.IndexPresenter;

public class IndexActivity extends MvpActivity<IndexPresenter> implements IIndexView{

    @Bind(R.id.index_recyclerView)
    RecyclerView recyclerView;

    private IndexAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
        initData();
    }

    @Override
    protected IndexPresenter createPresenter() {
        return new IndexPresenter(this);
    }

    private void initView() {
        initToolBar("常用示例",true);
        adapter = new IndexAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.registerListener(new IndexAdapter.OnClickRecyclerItemListener() {
            @Override
            public void onClick(int position) {
//                Intent intent = new Intent();
//                intent.setClass(IndexActivity.this, DrawerActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void initData() {
        mvpPresenter.makeData();
    }

    @Override
    public void getDataSuccess(List<IndexEntity> entities) {
        adapter.setData(entities);
    }

    @Override
    public void getDataFailed() {
        toastShow("获取数据失败 !!!");
    }
}
