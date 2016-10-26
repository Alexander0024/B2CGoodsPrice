package com.alexsophia.b2cgoodsprice.features.add.ui;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.add.ui.adapter.AttrAdapter;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.share.DropEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * AddMainFragment
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AddMainFragment extends BaseFragment {
    @Bind(R.id.dropEdtTxt_add_brand)
    DropEditText mDropEdtTxtBrand;

    @Override
    protected void stop() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.add_main_fragment;
    }

    @Override
    protected void createView() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroyView() {

    }

    @Override
    protected void initData() {
        mDropEdtTxtBrand.setAdapter(new AttrAdapter(getContext(), AttrAdapter.ATTR_TYPE.BRAND));
    }

    public static AddMainFragment newInstance() {
        return new AddMainFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
