package com.alexsophia.b2cgoodsprice.share;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;


/**
 * Loading dialog
 * Created by Alexander on 2016/4/21.
 */
public class LoadingDialog extends Dialog {
    private LoadingDialog(Builder builder) {
        super(builder.mContext, builder.mTheme);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (null != this.getWindow()) {
            this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        this.getWindow().getAttributes().alpha = 0.8f;
        this.setCanceledOnTouchOutside(false);
        if (null == builder.mOnCancelListener) {
            this.setCancelable(false);
        } else {
            this.setCancelable(true);
            this.setOnCancelListener(builder.mOnCancelListener);
        }
        this.setContentView(R.layout.common_loading_dialog);
        TextView tvMsg = (TextView) this.findViewById(R.id.tv_loading);
        if (tvMsg != null) {
            tvMsg.setText(builder.mMessage);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            dismiss();
        }
    }

    public static class Builder {
        private Context mContext;
        private int mTheme;
        private String mMessage;
        private OnCancelListener mOnCancelListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTheme(int theme) {
            this.mTheme = theme;
            return this;
        }

        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.mOnCancelListener = onCancelListener;
            return this;
        }

        public LoadingDialog build() {
            if (mTheme == 0) {
                mTheme = R.style.CustomLoadingDialog;
            }
            return new LoadingDialog(this);
        }
    }
}