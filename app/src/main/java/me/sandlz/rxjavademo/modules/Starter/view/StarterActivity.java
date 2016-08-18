package me.sandlz.rxjavademo.modules.Starter.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.Bind;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.core.view.BaseActivity;
import me.sandlz.rxjavademo.modules.Starter.view.fragment.BasicFragment;
import me.sandlz.rxjavademo.modules.Starter.view.fragment.OperatorFragment;

/**
 * @author zliu
 * @Date 16/8/17 10:16
 * @Describe
 * 入门示例
 *  1. 基础
 *  2. 操作符
 */
public class StarterActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        initView();
        initListener();
    }

    private void initView() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] mTitles = new String[]{"基础","操作符"};
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new BasicFragment();
                    case 1:
                        return new OperatorFragment();
                    default:
                        return new BasicFragment();
                }
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }

        });
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initListener() {

    }

}
