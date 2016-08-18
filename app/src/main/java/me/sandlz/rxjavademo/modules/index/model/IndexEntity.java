package me.sandlz.rxjavademo.modules.index.model;

import android.support.annotation.NonNull;

/**
 * Created by liuzhu on 16/8/15.
 * Description :
 * Usage :
 */
public class IndexEntity {

    private String function;
    private String description;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IndexEntity(@NonNull String fun, @NonNull String des) {
        this.function = fun;
        this.description = des;
    }
}
