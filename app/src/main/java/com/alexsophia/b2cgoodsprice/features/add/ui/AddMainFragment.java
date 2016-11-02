package com.alexsophia.b2cgoodsprice.features.add.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.alexsophia.b2cgoodsprice.R;
import com.alexsophia.b2cgoodsprice.features.add.presenters.AddPresenters;
import com.alexsophia.b2cgoodsprice.features.add.presenters.impl.AddPresentersImpl;
import com.alexsophia.b2cgoodsprice.features.add.ui.adapter.GoodsNameAdapter;
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
    @Bind(R.id.spinner_add_type)
    Spinner mSpinnerType;
    @Bind(R.id.spinner_add_brand)
    Spinner mSpinnerBrand;
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
    private String TAG = "AddMainFragment";
    private AddPresenters mPresenters;
    private GoodsNameAdapter mNameAdapter;

    public static AddMainFragment newInstance() {
        return new AddMainFragment();
    }

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
        // Type
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_spinner_item, mPresenters.getGoodsTypes());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(typeAdapter);
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Brand
        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(getContext(), android.R.layout
                .simple_spinner_item, mPresenters.getGoodsBrands());
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBrand.setAdapter(brandAdapter);
        mSpinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenters.selectBrand(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Name
        mNameAdapter = new GoodsNameAdapter(getContext());
        mDropEdtTxtName.setAdapter(mNameAdapter);
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
    public void refreshUI() {

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

    @Override
    public void onTypeSelected(Long goodsTypeId) {
        mNameAdapter.setType(goodsTypeId);
    }

    @Override
    public void onBrandSelected(Long goodsBrandId) {
        mNameAdapter.setBrand(goodsBrandId);
    }

    @Override
    public void onAddGoodsSuccess(long id) {
        ToastUtil.showLong(getContext(), "添加成功！id = " + id);
    }
}
