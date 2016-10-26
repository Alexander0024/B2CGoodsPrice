package com.alexsophia.b2cgoodsprice.features.add.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.add.presenters.AddPresenters;
import com.alexsophia.b2cgoodsprice.features.add.presenters.impl.AddPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.add.ui.adapter.AttrAdapter;
import com.alexsophia.b2cgoodsprice.features.base.executor.impl.ThreadExecutor;
import com.alexsophia.b2cgoodsprice.features.base.threading.impl.MainThreadImpl;
import com.alexsophia.b2cgoodsprice.features.base.ui.BaseFragment;
import com.alexsophia.b2cgoodsprice.share.DropEditText;
import com.alexsophia.b2cgoodsprice.utils.LogWrapper;
import com.alexsophia.b2cgoodsprice.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * AddMainFragment
 * <p>
 * Created by Alexander on 2016/10/26.
 */
public class AddMainFragment extends BaseFragment implements AddPresenters.View {
    private String TAG = "AddMainFragment";

    @Bind(R.id.dropEdtTxt_add_type)
    DropEditText mDropEdtTxtType;
    @Bind(R.id.dropEdtTxt_add_brand)
    DropEditText mDropEdtTxtBrand;
    @Bind(R.id.dropEdtTxt_add_name)
    DropEditText mDropEdtTxtName;
    @Bind(R.id.edtTxt_add_standard)
    EditText mEdtTxtStandard;
    @Bind(R.id.rb_add_online)
    RadioButton mRbAddOnline;
    @Bind(R.id.rb_add_offline)
    RadioButton mRbAddOffline;
    @Bind(R.id.rg_add_type_select)
    RadioGroup mRgAddTypeSelect;
    @Bind(R.id.edtTxt_add_price)
    EditText mEdtTxtPrice;
    @Bind(R.id.btn_add_submit)
    Button mBtnSubmit;

    private AddPresenters mPresenters;
    private AttrAdapter mBrandAdapter;
    private AttrAdapter mNameAdapter;

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
        LogWrapper.e(TAG, "initData: ");
        mPresenters = new AddPresentersImpl(ThreadExecutor.getInstance(), MainThreadImpl
                .getInstance(), this);
        mBrandAdapter = new AttrAdapter(getContext(), AttrAdapter.ATTR_TYPE.BRAND);
        mNameAdapter = new AttrAdapter(getContext(), AttrAdapter.ATTR_TYPE.NAME);
        mDropEdtTxtType.setAdapter(new AttrAdapter(getContext(), AttrAdapter.ATTR_TYPE.TYPE));
        mDropEdtTxtType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogWrapper.e(TAG, "mDropEdtTxtType afterTextChanged: " + s.toString());
                mBrandAdapter.setExtra(s.toString());
            }
        });
        mDropEdtTxtBrand.setAdapter(mBrandAdapter);
        mDropEdtTxtBrand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogWrapper.e(TAG, "mDropEdtTxtBrand afterTextChanged: " + s.toString());
                mNameAdapter.setExtra(s.toString());
            }
        });
        mDropEdtTxtName.setAdapter(mNameAdapter);
    }

    public static AddMainFragment newInstance() {
        return new AddMainFragment();
    }

    @OnClick(R.id.btn_add_submit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_submit:
                LogWrapper.e(TAG, "Submit click!");
                mPresenters.addNew();
                break;
        }
    }

    @Override
    public void showProgress() {
        showLoadingProgress();
    }

    @Override
    public void hideProgress() {
        hideLoadingProgress();
    }

    @Override
    public void showError(String message) {
        ToastUtil.showLong(getContext(), message);
    }

    @Override
    public String getType() {
        return mDropEdtTxtType.getText();
    }

    @Override
    public String getBrand() {
        return mDropEdtTxtBrand.getText();
    }

    @Override
    public String getName() {
        return mDropEdtTxtName.getText();
    }

    @Override
    public String getStandard() {
        return mEdtTxtStandard.getText().toString();
    }

    @Override
    public boolean getOnlineOffline() {
        return mRbAddOnline.isChecked();
    }

    @Override
    public double getPrice() {
        return Double.parseDouble(mEdtTxtPrice.getText().toString());
    }
}
