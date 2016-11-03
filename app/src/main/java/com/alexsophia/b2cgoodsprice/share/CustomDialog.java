package com.alexsophia.b2cgoodsprice.share;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexsophia.b2cgoodsprice.R;

/**
 * 自定义对话框的实现
 * Created by Alexander on 2016/3/16.
 */
@SuppressWarnings("unused")
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context mContext;
        private String mTitle;
        private String mMessage;
        private String mPositiveButtonText;
        private String mNegativeButtonText;
        private View mContentView;
        private DialogInterface.OnClickListener mPositiveButtonClickListener;
        private DialogInterface.OnClickListener mNegativeButtonClickListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setContentView(View v) {
            this.mContentView = v;
            return this;
        }

        /**
         * Set the Dialog title from resource
         */
        public Builder setTitle(int title) {
            this.mTitle = (String) mContext.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         */
        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        /**
         * Set the Dialog message from resource
         */
        public Builder setMessage(int message) {
            this.mMessage = (String) mContext.getText(message);
            return this;
        }

        /**
         * Set the Dialog message from String
         */
        public Builder setMessage(String message) {
            this.mMessage = message;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.mPositiveButtonText = (String) mContext
                    .getText(positiveButtonText);
            this.mPositiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.mPositiveButtonText = positiveButtonText;
            this.mPositiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.mNegativeButtonText = (String) mContext
                    .getText(negativeButtonText);
            this.mNegativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.mNegativeButtonText = negativeButtonText;
            this.mNegativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (null != dialog.getWindow()) {
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            View layout = inflater.inflate(R.layout.common_custom_dialog, null);
            dialog.addContentView(layout, new FrameLayout.LayoutParams(FrameLayout.LayoutParams
                    .MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

            // set the dialog title
            if (null == mTitle || "".equals(mTitle)) {
                layout.findViewById(R.id.tv_item_title).setVisibility(View.GONE);
            } else {
                ((TextView) layout.findViewById(R.id.tv_item_title)).setText(mTitle);
            }
            // set the content message
            if (mMessage != null) {
                ((TextView) layout.findViewById(R.id.tv_message)).setText(mMessage);
            } else if (mContentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.ll_content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.ll_content)).addView(mContentView, new
                        FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT));
            }
            // set the confirm button
            if (mPositiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.tv_positive_button)).setText(mPositiveButtonText);
                if (mPositiveButtonClickListener != null) {
                    (layout.findViewById(R.id.tv_positive_button)).setOnClickListener(new View
                            .OnClickListener() {
                        public void onClick(View v) {
                            mPositiveButtonClickListener.onClick(dialog, DialogInterface
                                    .BUTTON_POSITIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.tv_positive_button).setVisibility(View.GONE);
                layout.findViewById(R.id.v_split_line).setVisibility(View.GONE);
            }
            // set the cancel button
            if (mNegativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.tv_negative_button)).setText(mNegativeButtonText);
                if (mNegativeButtonClickListener != null) {
                    (layout.findViewById(R.id.tv_negative_button)).setOnClickListener(new View
                            .OnClickListener() {
                        public void onClick(View v) {
                            mNegativeButtonClickListener.onClick(dialog, DialogInterface
                                    .BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.tv_negative_button).setVisibility(View.GONE);
                layout.findViewById(R.id.v_split_line).setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            // 所有弹出框禁止点击外面后自动消失
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }
}