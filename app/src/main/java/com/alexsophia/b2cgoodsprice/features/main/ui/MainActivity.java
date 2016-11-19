package com.alexsophia.b2cgoodsprice.features.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.goods.ui.GoodsMainFragment;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;
import com.alexsophia.b2cgoodsprice.features.main.ui.adapter.FragmentAdapter;
import com.alexsophia.b2cgoodsprice.features.manage.ui.ManageMainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenters.View {
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_activity);
        ButterKnife.bind(this);
        //初始化控件及布局
        initView();
    }

    private void initView() {
        //初始化ToolBar
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //初始化TabLayout的title数据集
        List<String> titles = new ArrayList<>();
        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        // Item 0 - 物品列表
        titles.add(getString(R.string.menu_item));
        fragments.add(GoodsMainFragment.newInstance());
        // Item 1 - 管理
        titles.add(getString(R.string.menu_manage));
        fragments.add(ManageMainFragment.newInstance());
        //初始化TabLayout的title
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }
        //创建ViewPager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments,
                titles);
        mViewPager.setAdapter(adapter);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void refreshUI() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //主界面右上角的menu菜单
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.menu_manage:
                mViewPager.setCurrentItem(1);
                break;
            case android.R.id.home:
                //主界面左上角的icon点击反应
                //mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
