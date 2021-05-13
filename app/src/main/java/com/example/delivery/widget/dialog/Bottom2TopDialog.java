package com.example.delivery.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.delivery.R;


/**
 * @author guosir
 */
public abstract class Bottom2TopDialog extends Dialog {

    public Bottom2TopDialog(@NonNull Context context) {
        super(context, R.style.style_dialog_white);
        initConfig();
    }

    private void initConfig() {
        View contentView = View.inflate(getContext(), getLayoutId(), null);
        setContentView(contentView);
        setCancelable(true);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setWindowAnimations(R.style.style_top_bottom);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getContext().getResources().getDisplayMetrics().widthPixels;
        contentView.measure(0, 0);
        window.setAttributes(lp);

        initViewAndListener(contentView);
    }

    protected abstract int getLayoutId();

    protected abstract void initViewAndListener(View view);
}
