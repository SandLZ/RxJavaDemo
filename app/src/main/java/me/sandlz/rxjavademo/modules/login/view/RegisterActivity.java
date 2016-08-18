package me.sandlz.rxjavademo.modules.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.utils.LogUtil;

/**
 * Created by liuzhu on 16/8/12.
 * Description :
 * Usage :
 */
public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.bt_done)
    Button btn_register;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_userpwd;
    @Bind(R.id.et_repeatpassword)
    EditText et_repeatpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_done,R.id.fab})
    public void OnCLick(View view) {
        switch (view.getId()) {
            case R.id.bt_done:
                doRegisterUser();
                break;
            case R.id.fab:
                RegisterActivity.super.onBackPressed();
                break;
        }
    }
    // 注册用户
    private void doRegisterUser() {
        String userName = et_username.getText().toString();
        String userPwd = et_userpwd.getText().toString();
        String repeat_pwd = et_repeatpassword.getText().toString();
        if (! userPwd.endsWith(repeat_pwd)) {
            LogUtil.d("2次密码不相同");
            return;
        }
//        User user = new User();
//        user.setName(userName);
//        user.setPwd(userPwd);
//        //
//        try {
//            DBHelper.getCacheInstance().getDbManager().save(user);
//            RegisterActivity.super.onBackPressed();
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }


}
