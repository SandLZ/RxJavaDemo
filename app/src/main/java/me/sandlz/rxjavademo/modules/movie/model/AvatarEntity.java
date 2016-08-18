package me.sandlz.rxjavademo.modules.movie.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by liuzhu on 16/8/3.
 * Description : 电影海报bean
 * Usage :
 */
@Table(name = "AvatarEntity")
public class AvatarEntity {
    @Column(name = "id", isId = true, autoGen = false)
    private int id;
    @Column(name = "small")
    private String small;
    @Column(name = "medium")
    private String medium;
    @Column(name = "large")
    private String large;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

}
