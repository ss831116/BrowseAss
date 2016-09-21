package com.bernie.browseass.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bernie.browseass.R;
import com.bernie.browseass.listener.DelBookListener;

import bernie.greendao.dao.BrowseAssBookMarks;

/**
 * Created by bernie.shi on 2016/9/21.
 */

public class DelBookDialog extends Dialog {
    public DelBookDialog(Context context) {
        super(context);
    }
    BrowseAssBookMarks bookMarks;
    DelBookListener delBookListener;
    public DelBookDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public DelBookDialog(Context context, int themeResId,BrowseAssBookMarks bookMarks,DelBookListener delBookListener) {
        super(context, themeResId);
        this.bookMarks = bookMarks;
        this.delBookListener = delBookListener;
    }
    protected DelBookDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_book_dialog);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.dialog_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button online = (Button) findViewById(R.id.online_button);
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                delBookListener.confirm(bookMarks);
            }
        });
        TextView cancel = (TextView) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
