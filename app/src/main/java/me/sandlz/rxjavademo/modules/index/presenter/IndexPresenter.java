package me.sandlz.rxjavademo.modules.index.presenter;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.rxjavademo.core.mvp.BasePresenter;
import me.sandlz.rxjavademo.modules.index.model.IndexEntity;
import me.sandlz.rxjavademo.modules.index.view.IIndexView;

/**
 * Created by liuzhu on 16/8/15.
 * Description :
 * Usage :
 */
public class IndexPresenter extends BasePresenter<IIndexView> {

    public IndexPresenter(IIndexView iIndexView) {
        attachView(iIndexView);
    }

    public void makeData() {
        IndexEntity entity = new IndexEntity("1","1111111");
        IndexEntity entity2 = new IndexEntity("2","222222");
        IndexEntity entity3 = new IndexEntity("3","3333333");
        IndexEntity entity4 = new IndexEntity("4","4444444");
        IndexEntity entity5 = new IndexEntity("5","5555555");
        IndexEntity entity6 = new IndexEntity("6","6666666");
        IndexEntity entity7 = new IndexEntity("7","7777777");
        IndexEntity entity8 = new IndexEntity("8","88888888");
        IndexEntity entity9 = new IndexEntity("9","99999999");
        IndexEntity entity10 = new IndexEntity("10","1010101010");
        List<IndexEntity> entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity2);
        entities.add(entity3);
        entities.add(entity4);
        entities.add(entity5);
        entities.add(entity6);
        entities.add(entity7);
        entities.add(entity8);
        entities.add(entity9);
        entities.add(entity10);
        mvpView.getDataSuccess(entities);
    }
}
