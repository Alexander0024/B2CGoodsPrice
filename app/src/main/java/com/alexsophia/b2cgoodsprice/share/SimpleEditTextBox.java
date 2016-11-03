package com.alexsophia.b2cgoodsprice.share;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * SimpleEditTextBox
 * <p>
 * Created by Alexander on 2016/10/28.
 */
public class SimpleEditTextBox {
    public SimpleEditTextBox(Context context, int title, int message, final OnClickListener
            onClickListener) {
        this(context, context.getString(title), context.getString(message), null, onClickListener);
    }

    public SimpleEditTextBox(Context context, String title, String message, String defaultValue,
                             final OnClickListener onClickListener) {
        final EditText input = new EditText(context);
        if (null != defaultValue) {
            input.setText(defaultValue);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setView(input);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClickListener != null) {
                    onClickListener.onPositiveButtonClicked(input.getText().toString());
                }
            }
        });
        builder.show();
    }

    public interface OnClickListener {
        void onPositiveButtonClicked(String value);
    }
}
