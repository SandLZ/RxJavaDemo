package me.sandlz.rxjavademo.modules.login.model;

import com.google.gson.annotations.SerializedName;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
@Table(name = "UserInfo")
public class UserEntity {

    @Column(name = "id",isId = true)
    @SerializedName("stunum")
    private int id;

    @Column(name = "name")
    @SerializedName("name")
    private String name;

    @Column(name = "headUrl")
    @SerializedName("headurl")
    private String headUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
