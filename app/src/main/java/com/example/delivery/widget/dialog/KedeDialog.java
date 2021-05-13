package com.example.delivery.widget.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleRes;

import com.blankj.utilcode.util.ScreenUtils;
import com.example.delivery.R;
import com.example.delivery.utils.ProportionUtils;


public abstract class KedeDialog implements View.OnClickListener {
    private Context mContext;
    private int mThemeResId;
    private DialogCallBack mDialogCallBack;
    private AlertDialog mDialog;
    private View mDialogView;
    private TextView mDialogTitle;
    private TextView mDialogMessage;
    private TextView mDialogCall;
    private TextView mDialogCancle;

    private float scale = 1.0f;
    private int mDialogLLBackgroundColor = Color.WHITE;

    private float showWidthScale = 0.8f;
    private float showHeighScale = -1f;

    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    private int mOutTitleSize = 20;

    private int mDividerSize = 1;

    private float mTitleSize = 40.0f;

    private int mTitleBackgroundColor = Color.TRANSPARENT;

    private int mMessagePadding = 24;
    private float mMessageSize = 30.0f;


    private float mCallSize = 30.0f;

    private float mCancleSize = 30.0f;

    private LinearLayout mDialogLL;
    private float hightProportion;
    private float widthProportion;

    public KedeDialog(Context context) {
        this.mContext = context;
        initDialog();
    }

    public KedeDialog(Context context, @StyleRes int themeResId) {
        this.mContext = context;
        this.mThemeResId = themeResId;
        initDialog();
    }

    private void initDialog() {
        this.scale = ProportionUtils.getWidthProportion();
        hightProportion = ProportionUtils.getHightProportion();
        widthProportion = ProportionUtils.getWidthProportion();
        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.view_base_dialog, null);
        mDialogTitle = (TextView) mDialogView.findViewById(R.id.dialog_title);
        mDialogLL = (LinearLayout) mDialogView.findViewById(R.id.dialog_LL);

        if (initLayoutId() != 0) {
            View inflate = LayoutInflater.from(mContext).inflate(initLayoutId(), null);
            mDialogLL.addView(inflate);
            initView(inflate, mContext);
        } else {
            mDialogMessage = new TextView(mContext);
            mDialogMessage.setGravity(Gravity.CENTER);
            mDialogMessage.setPadding(24, 0, 24, 0);
            mDialogLL.addView(mDialogMessage);
        }
        mDialogCall = (TextView) mDialogView.findViewById(R.id.dialog_call);
        mDialogCancle = (TextView) mDialogView.findViewById(R.id.dialog_cancle);
        setDividedrSize(mDividerSize);

        if (mThemeResId == 0) {
            mDialog = new AlertDialog.Builder(mContext, R.style.dialogstyle_white).create();
        } else {
            mDialog = new AlertDialog.Builder(mContext, mThemeResId).create();
        }
    }


    protected abstract int initLayoutId();


    protected abstract void initView(View view, Context context);


    protected abstract void initConfig();


    public TextDialog showDialog(String title, String message, String YES_name, String NO_name, DialogCallBack dialogCallBack) {
        this.mDialogCallBack = dialogCallBack;
        mDialog.show();

        initConfig();


        setCancelable(mCancelable);


        setCanceledOnTouchOutside(mCanceledOnTouchOutside);


        setTitle(title);


        setMessage(message);


        setCall(YES_name);


        setCancle(NO_name);

        mDialogCall.setOnClickListener(this);
        mDialogCancle.setOnClickListener(this);


        Window window = mDialog.getWindow();
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth() * showWidthScale); // 宽度设置为屏幕的0.8
        if (showHeighScale != -1f) {
            p.height = (int) (ScreenUtils.getScreenHeight() * showHeighScale);
        } else {
            p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        window.setAttributes(p);

        window.setContentView(mDialogView);


        return null;
    }

    private void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mDialogTitle.setVisibility(View.GONE);
        } else {
            mDialogTitle.setVisibility(View.VISIBLE);
            setTitleSize(mTitleSize);
            setTitleBackgroundColor(mTitleBackgroundColor);
            if (title.length() < mOutTitleSize) {
                mDialogTitle.setText(title);
            } else {
                mDialogTitle.setText("Text too long");
            }
        }
    }

    private void setMessage(String message) {
        if (mDialogMessage == null) {
            return;
        }
        if (TextUtils.isEmpty(message)) {
            mDialogMessage.setVisibility(View.GONE);
        } else {
            mDialogMessage.setVisibility(View.VISIBLE);
            setTextStyle(mDialogMessage, message);
        }
    }

    private void setCall(String call) {
        if (TextUtils.isEmpty(call)) {
            mDialogCall.setVisibility(View.GONE);

        } else {
            mDialogCall.setVisibility(View.VISIBLE);
            setCallSize(mCallSize);
            mDialogCall.setText(call);
        }
    }

    private void setCancle(String cancle) {
        if (TextUtils.isEmpty(cancle)) {
            mDialogCancle.setVisibility(View.GONE);
        } else {
            mDialogCancle.setVisibility(View.VISIBLE);
            setCancleSize(mCancleSize);
            mDialogCancle.setText(cancle);
        }
    }


    private void setTextSize(TextView tv, float titleSize) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize * scale);
    }

    private void setViewBackgroundColor(View view, int coclor) {
        view.setBackgroundColor(coclor);
    }

    private void setLayoutParams(final View view, final int size, final int type) {
        view.post(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case 1:
                        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, size));
                        break;
                    case 2:
                        view.setLayoutParams(new LinearLayout.LayoutParams(size, LinearLayout.LayoutParams.MATCH_PARENT));
                        break;
                }

            }
        });
    }

    protected int[] measureTextViewHeight(TextView textView, String content, int width, int maxLine) {
        TextPaint textPaint = textView.getPaint();
        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        int[] result = new int[3];
        int lineCount = staticLayout.getLineCount();
        result[0] = lineCount;
        if (lineCount > maxLine) {
            int lastIndex = staticLayout.getLineStart(maxLine) - 1;
            result[1] = lastIndex;
            result[2] = new StaticLayout(content.substring(0, lastIndex), textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false).getHeight();
            return result;
        } else {
            result[1] = -1;
            result[2] = staticLayout.getHeight();
            return result;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_call:
                if (mDialogCallBack != null) {
                    mDialogCallBack.doselectok();
                }
                mDialog.dismiss();
                break;
            case R.id.dialog_cancle:
                if (mDialogCallBack != null) {
                    mDialogCallBack.doselectno();
                }
                mDialog.dismiss();
                break;
        }
    }


    public void setTextStyle(TextView tv, String message) {
        setTextSize(tv, mMessageSize);
        tv.setText(message);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setPadding(mMessagePadding, (mMessagePadding - 12), mMessagePadding, (mMessagePadding - 12));
        int[] ints = measureTextViewHeight(tv, message, 600, 20);
        int lineCount = ints[0];
        if (lineCount <= 1) {
            tv.setGravity(Gravity.CENTER);
        } else {
            tv.setGravity(Gravity.CENTER);
        }
        int anInt1 = ints[2];
        int maxhight = (int) (500 * scale);
        int minhight = (int) (150 * scale);
        if (anInt1 > maxhight) {
            setLLHight(maxhight);
        } else if (anInt1 < minhight) {
            setLLHight(minhight);
        }
    }

    public void setDividedrSize(int dividerSize) {
        mDividerSize = dividerSize;
    }

    public void setLLHight(int lLHight) {
        setLayoutParams(mDialogLL, lLHight, 1);
    }

    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        setTextSize(mDialogTitle, mTitleSize);
    }

    public void setTitleBackgroundColor(int titleBackgroundColor) {
        mTitleBackgroundColor = titleBackgroundColor;
        setViewBackgroundColor(mDialogTitle, mTitleBackgroundColor);
    }

    public void setCallSize(float callSize) {
        mCallSize = callSize;
        setTextSize(mDialogCall, mCallSize);
    }

    public void setCancleSize(float cancleSize) {
        mCancleSize = cancleSize;
        setTextSize(mDialogCancle, mCancleSize);
    }

    public void setCancelable(Boolean cancelable) {
        mCancelable = cancelable;
        if (mDialog != null) {
            mDialog.setCancelable(mCancelable);
        }
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mCanceledOnTouchOutside = cancel;
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(cancel);
        }
    }

    public TextView getmDialogMessage() {
        return mDialogMessage;
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    public Context getContext() {
        return mContext;
    }

}
