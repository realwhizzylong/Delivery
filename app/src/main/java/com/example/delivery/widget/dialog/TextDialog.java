package com.example.delivery.widget.dialog;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;

/**
 * Created by 陈小龙 on 2017/3/4.
 */
public class TextDialog extends KedeDialog {
    public TextDialog(Context context) {
        super(context);
    }


    @Override
    protected int initLayoutId() {
        return 0;
    }

    @Override
    protected void initView(View view, Context context) {

    }

    @Override
    protected void initConfig() {
//        setTitleSize(50.f);
//        setTitleColor(Color.parseColor("#00000000"));
//        setTitleBackgroundColor(Color.parseColor("#00000000"));
//        setMessageColor(Color.parseColor("#00000000"));
//        setMessageBackgroundColor(Color.parseColor("#00000000"));
//        setCallColor(Color.parseColor("#00000000"));
//        setCallBackgroundColor(Color.parseColor("#00000000"));
//        setCancleColor(Color.parseColor("#00000000"));
//        setCancleBackgroundColor(Color.parseColor("#00000000"));
//        setDividerBackgroundColor(Color.BLACK);
    }

    public void setMsgText(SpannableStringBuilder msgText) {
        getmDialogMessage().setText(msgText);
        getmDialogMessage().setMovementMethod(LinkMovementMethod.getInstance());
    }
}
