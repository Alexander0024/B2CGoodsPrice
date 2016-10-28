package com.alexsophia.b2cgoodsprice.features.main.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.add.ui.AddMainFragment;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragmentActivity;
import com.alexsophia.b2cgoodsprice.features.list.ui.ListMainFragment;
import com.alexsophia.b2cgoodsprice.features.main.presenters.MainPresenters;
import com.alexsophia.b2cgoodsprice.features.manage.ui.ManageMainFragment;
import com.alexsophia.b2cgoodsprice.share.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity implements MainPresenters.View {
    private String TAG = "CommonMainActivity";
    /**
     * 主界面相关控件
     */
    @Bind(R.id.tab1_iv_main)
    ImageView mTab1Img; // 第一个Tab的图标
    @Bind(R.id.tab2_iv_main)
    ImageView mTab2Img; // 第二个Tab的图标
    @Bind(R.id.tab3_iv_main)
    ImageView mTab3Img; // 第三个Tab的图标
    @Bind(R.id.tab4_iv_main)
    ImageView mTab4Img; // 第四个Tab的图标
    @Bind(R.id.tab1_tv_main)
    TextView mTab1Tv; // 第一个Tab的文字
    @Bind(R.id.tab2_tv_main)
    TextView mTab2Tv; // 第二个Tab的文字
    @Bind(R.id.tab3_tv_main)
    TextView mTab3Tv; // 第三个Tab的文字
    @Bind(R.id.tab4_tv_main)
    TextView mTab4Tv; // 第四个Tab的文字
    @Bind(R.id.iv_tab1_unread_mark)
    RoundedImageView mTab1UnreadRiv; // 第一个Tab红点标记
    @Bind(R.id.iv_tab2_unread_mark)
    RoundedImageView mTab2UnreadRiv; // 第二个Tab红点标记
    @Bind(R.id.iv_tab3_unread_mark)
    RoundedImageView mTab3UnreadRiv; // 第三个Tab红点标记
    @Bind(R.id.iv_tab4_unread_mark)
    RoundedImageView mTab4UnreadRiv; // 第四个Tab红点标记

    protected List<Fragment> mFragmentsList; // 主界面fragment切换
    private FragmentManager mFragmentManager; // 碎片管理器
    private Fragment mContent; // 当前展示的fragment标识

    @Override
    protected int getContentViewRes() {
        return R.layout.main_content_activity;
    }

    @Override
    protected Activity getTarget() {
        return this;
    }

    /**
     * init
     */
    @Override
    protected void loadData() {
        /**
         * 初始化碎片及碎片管理器
         */
        mFragmentsList = new ArrayList<>();
        addFragments();
        mFragmentManager = getSupportFragmentManager();
        /**
         * 默认进入第一个页面（消息公告）
         */
        selectTab(0);
    }

    @Override
    protected void resumeData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void stop() {

    }

    @Override
    protected void destroy() {

    }

    /**
     * 添加fragment到Activity
     */
    protected void addFragments() {
        ListMainFragment listMainFragment = ListMainFragment.newInstance();
        mFragmentsList.add(listMainFragment);
        AddMainFragment addMainFragment = AddMainFragment.newInstance();
        mFragmentsList.add(addMainFragment);
        mFragmentsList.add(addMainFragment);
        ManageMainFragment manageMainFragment = ManageMainFragment.newInstance();
        mFragmentsList.add(manageMainFragment);
    }

    /**
     * 点击选中后切换TAB，切换下方导航栏的字体及图片
     */
    private void selectTab(int pos) {
        switch (pos) {
            case 0:
                mTab1Tv.setTextColor(getFocusTabColor());
                mTab2Tv.setTextColor(getNoFocusTabColor());
                mTab3Tv.setTextColor(getNoFocusTabColor());
                mTab4Tv.setTextColor(getNoFocusTabColor());
//                mTab1Img.setImageResource(getTab1FocusImgRes());
//                mTab2Img.setImageResource(getTab2NoFocusImgRes());
//                mTab3Img.setImageResource(getTab3NoFocusImgRes());
//                mTab4Img.setImageResource(getTab4NoFocusImgRes());
                break;
            case 1:
                mTab1Tv.setTextColor(getNoFocusTabColor());
                mTab2Tv.setTextColor(getFocusTabColor());
                mTab3Tv.setTextColor(getNoFocusTabColor());
                mTab4Tv.setTextColor(getNoFocusTabColor());
//                mTab1Img.setImageResource(getTab1NoFocusImgRes());
//                mTab2Img.setImageResource(getTab2FocusImgRes());
//                mTab3Img.setImageResource(getTab3NoFocusImgRes());
//                mTab4Img.setImageResource(getTab4NoFocusImgRes());
                break;
            case 2:
                mTab1Tv.setTextColor(getNoFocusTabColor());
                mTab2Tv.setTextColor(getNoFocusTabColor());
                mTab3Tv.setTextColor(getFocusTabColor());
                mTab4Tv.setTextColor(getNoFocusTabColor());
//                mTab1Img.setImageResource(getTab1NoFocusImgRes());
//                mTab2Img.setImageResource(getTab2NoFocusImgRes());
//                mTab3Img.setImageResource(getTab3FocusImgRes());
//                mTab4Img.setImageResource(getTab4NoFocusImgRes());
                break;
            case 3:
                mTab1Tv.setTextColor(getNoFocusTabColor());
                mTab2Tv.setTextColor(getNoFocusTabColor());
                mTab3Tv.setTextColor(getNoFocusTabColor());
                mTab4Tv.setTextColor(getFocusTabColor());
//                mTab1Img.setImageResource(getTab1NoFocusImgRes());
//                mTab2Img.setImageResource(getTab2NoFocusImgRes());
//                mTab3Img.setImageResource(getTab3NoFocusImgRes());
//                mTab4Img.setImageResource(getTab4FocusImgRes());
                break;
            default:
                break;
        }
        /**
         * 切换碎片
         */
        changeFragment(pos);
    }

    /**
     * 切换碎片
     *
     * @param position 点击的Position
     */
    private void changeFragment(int position) {
        if (mFragmentsList != null && !mFragmentsList.isEmpty()) {
            /**
             * 获取点击的碎片
             */
            Fragment fragment = mFragmentsList.get(position);
            switchContent(fragment);
        }
    }

    /**
     * 执行切换Fragment的操作
     *  @param to          需要切换至的fragment
     *
     */
    private void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            /**
             * 执行切换
             */
            if (null == mContent) {
                /**
                 * 如果content为空，即初始化，直接添加至显示即可。
                 */
                transaction.add(R.id.content_main, to).commit();
            } else if (!to.isAdded()) {
                /**
                 * 如果需要进入的fragment没有被Add过
                 * 隐藏当前的fragment，add下一个到Activity中。
                 */
                transaction.hide(mContent).add(R.id.content_main, to).commit();
            } else {
                /**
                 * 如果已经被Add过
                 * 隐藏当前的fragment，直接显示到需要去的fragment。
                 */
                transaction.hide(mContent).show(to).commit();
            }
            mContent = to;
        }
    }

    /**
     * 未点击时tab文字的颜色
     */
    private int getFocusTabColor() {
        return ContextCompat.getColor(this, R.color.main);
    }

    /**
     * 点击时tab文字的颜色
     */
    private int getNoFocusTabColor() {
        return ContextCompat.getColor(this, R.color.tv_light_hint);
    }

    @OnClick({R.id.tab1_main, R.id.tab2_main, R.id.tab3_main, R.id.tab4_main})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tab1_main:
                selectTab(0);
                break;
            case R.id.tab2_main:
                selectTab(1);
                break;
            case R.id.tab3_main:
                selectTab(2);
                break;
            case R.id.tab4_main:
                selectTab(3);
                break;
            default:
        }
    }

    @Override
    public Context getContext() {
        return null;
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
}
