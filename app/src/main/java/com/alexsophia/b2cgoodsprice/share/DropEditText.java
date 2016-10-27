package com.alexsophia.b2cgoodsprice.share;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alexsophia.b2cgoodsprice.R;

/**
 * DropEditText
 * <p>
 * Created by Alexander on 2016/10/26.
 */

public class DropEditText extends FrameLayout implements View.OnClickListener, AdapterView
        .OnItemClickListener {
    private final Context mContext;
    private EditText mEditText;  // 输入框
    private ImageView mDropImage; // 右边的图片按钮
    private PopupWindow mPopup; // 点击图片弹出popupwindow
    private WrapListView mPopView; // popupwindow的布局

    private int mDrawableLeft;
    private int mDropMode; // flower_parent or wrap_content
    private String mHit;

    public DropEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.common_drop_edittext, this);
        mPopView = (WrapListView) LayoutInflater.from(context).inflate(R.layout.common_drop_pop,
                null);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DropEditText, defStyle,
                0);
        mDrawableLeft = ta.getResourceId(R.styleable.DropEditText_drawableRight, R.drawable
                .common_drop);
        mDropMode = ta.getInt(R.styleable.DropEditText_dropMode, 0);
        mHit = ta.getString(R.styleable.DropEditText_hint);
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mEditText = (EditText) findViewById(R.id.dv_editText);
        mDropImage = (ImageView) findViewById(R.id.dv_iv);

        mEditText.setSelectAllOnFocus(true);
        mDropImage.setImageResource(mDrawableLeft);

        if (!TextUtils.isEmpty(mHit)) {
            mEditText.setHint(mHit);
        }

        mDropImage.setOnClickListener(this);
        mPopView.setOnItemClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 如果布局发生改
        // 并且dropMode是flower_parent
        // 则设置ListView的宽度
        if (changed && 0 == mDropMode) {
            mPopView.setListWidth(getMeasuredWidth());
        }
    }

    /**
     * 设置Adapter
     *
     * @param adapter ListView的Adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        mPopView.setAdapter(adapter);

        mPopup = new PopupWindow(mPopView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        mPopup.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(mContext, R.color
                .transparent)));
        mPopup.setFocusable(true); // 让popwin获取焦点
    }

    /**
     * 添加事件改变的监听
     *
     * @param watcher 监听器
     */
    public void addTextChangedListener(TextWatcher watcher) {
        mEditText.addTextChangedListener(watcher);
    }

    /**
     * 获取输入框内的内容
     *
     * @return String content
     */
    public String getText() {
        return mEditText.getText().toString();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dv_iv) {
            if (mPopup.isShowing()) {
                mPopup.dismiss();
                return;
            }
            mPopup.showAsDropDown(this, 0, 5);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mEditText.setText(mPopView.getAdapter().getItem(position).toString());
        mPopup.dismiss();
    }
}