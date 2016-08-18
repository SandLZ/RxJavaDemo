package me.sandlz.rxjavademo.modules.index.view;

import java.util.List;

import me.sandlz.rxjavademo.modules.index.model.IndexEntity;

/**
 * Created by liuzhu on 16/8/15.
 * Description :
 * Usage :
 */
public interface IIndexView {

    void getDataSuccess(List<IndexEntity> entities);

    void getDataFailed();

}
