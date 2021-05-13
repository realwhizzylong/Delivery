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
//    private ImageView mDialogTitleDivider;
//    private ImageView mDialogMessageDivider;
//    private ImageView mDialogBtnDivider;
    private float scale = 1.0f;        //计算比例   屏幕宽度/750
    private int mDialogLLBackgroundColor = Color.WHITE;     //填充区域背景颜色

    private float showWidthScale = 0.8f;        //显示宽度比例
    private float showHeighScale = -1f;        //显示高度比例 默认自适应

    private boolean mCancelable = true;    //dialog按返回键是否可消失
    private boolean mCanceledOnTouchOutside = true;//dialog触摸外部区域是否可消失

    private int mOutTitleSize = 20;        //标题长度禁止超过20

//    private int mDividerColor = Color.LTGRAY;     //分割线默认颜色
    private int mDividerSize = 1;     //分割线默认宽度(高度)

    private float mTitleSize = 40.0f;           //标题默认字体大小
//    private int mTitleColor = Color.DKGRAY;     //标题默认字体颜色
    private int mTitleBackgroundColor = Color.TRANSPARENT;     //标题默认背景颜色

    private int mMessagePadding = 24;        //内容默认消息padding
    private float mMessageSize = 30.0f;           //内容默认字体大小
//    private int mMessageColor = Color.GRAY;     //内容默认字体颜色
//    private int mMessageBackgroundColor = Color.TRANSPARENT;     //标题默认背景颜色

    private float mCallSize = 30.0f;               //确认按钮默认字体大小
//    private int mCallColor = mContext.getResources().getColor(R.color.ensure_txt_color);          //确认按钮默认字体颜色
//    private int mCallBackgroundColor = Color.TRANSPARENT;        //确认按钮默认背景颜色

    private float mCancleSize = 30.0f;               //取消按钮默认字体大小
//    private int mCancleColor = Color.GRAY;          //取消按钮默认字体颜色
//    private int mCancleBackgroundColor = Color.TRANSPARENT;        //取消按钮默认背景颜色

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
        //填充布局
        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.view_base_dialog, null);
        //标题
        mDialogTitle = (TextView) mDialogView.findViewById(R.id.dialog_title);
        //填充区域
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
        //确定
        mDialogCall = (TextView) mDialogView.findViewById(R.id.dialog_call);
        //取消
        mDialogCancle = (TextView) mDialogView.findViewById(R.id.dialog_cancle);
        //标题下方分割线
//        mDialogTitleDivider = (ImageView) mDialogView.findViewById(R.id.dialog_title_divider);
        //消息下方分割线
//        mDialogMessageDivider = (ImageView) mDialogView.findViewById(R.id.dialog_message_divider);
        //按钮之间分割线
//        mDialogBtnDivider = (ImageView) mDialogView.findViewById(R.id.dialog_btn_divider);

        //默认分割线颜色
//        setViewBackgroundColor(mDialogTitleDivider, mDividerColor);
//        setViewBackgroundColor(mDialogMessageDivider, mDividerColor);
//        setViewBackgroundColor(mDialogBtnDivider, mDividerColor);
        //默认分割线宽度(高度)
        setDividedrSize(mDividerSize);

        //创建dialog
        if (mThemeResId == 0) {
            mDialog = new AlertDialog.Builder(mContext, R.style.dialogstyle_white).create();
        } else {
            mDialog = new AlertDialog.Builder(mContext, mThemeResId).create();
        }
    }

    //1.设置布局
    protected abstract int initLayoutId();

    //2.初始化控件
    protected abstract void initView(View view, Context context);

    //3.设置基本配置
    protected abstract void initConfig();


    /**
     * 显示dialog
     *
     * @param title          标题 长度不能超过20
     * @param message        内容
     * @param YES_name       确认按钮文字
     * @param NO_name        取消按钮文字
     * @param dialogCallBack 回调
     */
    public TextDialog showDialog(String title, String message, String YES_name, String NO_name, DialogCallBack dialogCallBack) {
        this.mDialogCallBack = dialogCallBack;
        mDialog.show();
        //设置基本配置 比如字体大小 字体颜色 分割线颜色
        initConfig();

        //设置按返回键是否可以取消
        setCancelable(mCancelable);

        //设置触摸外部区域是否可取消
        setCanceledOnTouchOutside(mCanceledOnTouchOutside);

        //设置标题
        setTitle(title);

        //设置消息内容
        setMessage(message);

        //设置确认按钮
        setCall(YES_name);

        //设置取消按钮\
        setCancle(NO_name);

        mDialogCall.setOnClickListener(this);
        mDialogCancle.setOnClickListener(this);

        //获取Window
        Window window = mDialog.getWindow();
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth() * showWidthScale); // 宽度设置为屏幕的0.8
        if (showHeighScale != -1f) {
            p.height = (int) (ScreenUtils.getScreenHeight() * showHeighScale);
        }else {
            p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        window.setAttributes(p);
        //设置布局
        window.setContentView(mDialogView);


        return null;
    }

    //设置标题内容
    private void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            mDialogTitle.setVisibility(View.GONE);
//            mDialogTitleDivider.setVisibility(View.GONE);
        } else {
            mDialogTitle.setVisibility(View.VISIBLE);
//            mDialogTitleDivider.setVisibility(View.VISIBLE);
            setTitleSize(mTitleSize);
//            setTitleColor(mTitleColor);
            setTitleBackgroundColor(mTitleBackgroundColor);
            if (title.length() < mOutTitleSize) {
                mDialogTitle.setText(title);
            } else {
                mDialogTitle.setText("标题文字太长,可能会显示不全");
            }
        }
    }

    /*---------------------------内部方法-----------------------------*/
    //设置消息内容
    private void setMessage(String message) {
        if (mDialogMessage == null) {
            return;
        }
        if (TextUtils.isEmpty(message)) {
            mDialogMessage.setVisibility(View.GONE);
//            mDialogMessageDivider.setVisibility(View.GONE);
        } else {
            mDialogMessage.setVisibility(View.VISIBLE);
//            mDialogMessageDivider.setVisibility(View.VISIBLE);

//            setMessageBackgroundColor(mMessageBackgroundColor);
            setTextStyle(mDialogMessage, message);
        }
    }

    //设置确认按钮
    private void setCall(String call) {
        if (TextUtils.isEmpty(call)) {
            mDialogCall.setVisibility(View.GONE);
//            mDialogBtnDivider.setVisibility(View.GONE);
        } else {
            mDialogCall.setVisibility(View.VISIBLE);
//            mDialogBtnDivider.setVisibility(View.VISIBLE);
            setCallSize(mCallSize);
//            setCallColor(mCallColor);
//            setCallBackgroundColor(mCallBackgroundColor);
            mDialogCall.setText(call);
        }
    }

    //设置取消按钮
    private void setCancle(String cancle) {
        if (TextUtils.isEmpty(cancle)) {
            mDialogCancle.setVisibility(View.GONE);
//            mDialogBtnDivider.setVisibility(View.GONE);
        } else {
            mDialogCancle.setVisibility(View.VISIBLE);
//            mDialogBtnDivider.setVisibility(View.VISIBLE);
            setCancleSize(mCancleSize);
//            setCancleColor(mCancleColor);
//            setCancleBackgroundColor(mCancleBackgroundColor);
            mDialogCancle.setText(cancle);
        }
    }

    /*---------------------------通用设置方法-----------------------------*/
    //设置文字大小
    private void setTextSize(TextView tv, float titleSize) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize * scale);
    }

    //设置文字颜色
    private void setTextColor(TextView tv, int coclor) {
        tv.setTextColor(coclor);
    }

    //设置背景颜色
    private void setViewBackgroundColor(View view, int coclor) {
        view.setBackgroundColor(coclor);
    }

    /**
     * 设置控件宽高
     *
     * @param view 控件
     * @param size 大小 单位是px计算
     * @param type 1 是设置高   2  是设置宽
     */
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

    /**
     * 在不绘制textView的情况下算出textView的高度，并且根据最大行数得到应该显示最后一个字符的下标，请在主线程顺序执行，
     * 第一个返回值是行数,
     * 第二个返回值是最后一个字符的下标，
     * 第三个返回值是高度
     *
     * @param textView
     * @param content  内容
     * @param width    宽度
     * @param maxLine  最大行数
     * @return
     */
    protected int[] measureTextViewHeight(TextView textView, String content, int width, int maxLine) {
        TextPaint textPaint = textView.getPaint();
        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false);
        int[] result = new int[3];
        int lineCount = staticLayout.getLineCount();
        result[0] = lineCount;
        if (lineCount > maxLine) {//如果行数超出限制
            int lastIndex = staticLayout.getLineStart(maxLine) - 1;
            result[1] = lastIndex;
            result[2] = new StaticLayout(content.substring(0, lastIndex), textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1, 0, false).getHeight();
            return result;
        } else {//如果行数没有超出限制
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


    /*---------------------------对外提供方法-----------------------------*/

    /**
     * 设置TextView样式  统一样式
     * 内容少 固定300px高度 内容多最高500px
     * 超过textview内容可滚动
     * 单独一行 内容居中显示
     * 多行 靠左显示
     *
     * @param tv
     * @param message
     */
    public void setTextStyle(TextView tv, String message) {
        setTextSize(tv, mMessageSize);
//        setTextColor(tv, mMessageColor);
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

    //设置分割线宽度(高度)
    public void setDividedrSize(int dividerSize) {
        mDividerSize = dividerSize;
//        setLayoutParams(mDialogTitleDivider, dividerSize, 1);
//        setLayoutParams(mDialogMessageDivider, dividerSize, 1);
//        setLayoutParams(mDialogBtnDivider, dividerSize, 2);
    }

    //设置分割线颜色
    public void setDividerBackgroundColor(int dividedrColor) {
//        mDividerColor = dividedrColor;
//        setViewBackgroundColor(mDialogTitleDivider, mDividerColor);
//        setViewBackgroundColor(mDialogMessageDivider, mDividerColor);
//        setViewBackgroundColor(mDialogBtnDivider, mDividerColor);
    }

    /*---------------------------填充区域-----------------------------*/
    //设置填充区域高度
    public void setLLHight(int lLHight) {
        setLayoutParams(mDialogLL, lLHight, 1);
    }

    /*---------------------------标题栏-----------------------------*/
    //设置标题栏文字大小
    public void setTitleSize(float titleSize) {
        mTitleSize = titleSize;
        setTextSize(mDialogTitle, mTitleSize);
    }

    //设置标题栏文字颜色
    public void setTitleColor(int titleColor) {
//        mTitleColor = titleColor;
//        setTextColor(mDialogTitle, mTitleColor);
    }

    //设置标题栏背景颜色
    public void setTitleBackgroundColor(int titleBackgroundColor) {
        mTitleBackgroundColor = titleBackgroundColor;
        setViewBackgroundColor(mDialogTitle, mTitleBackgroundColor);
    }


    /*---------------------------消息-----------------------------*/
    //设置消息文字大小
    public void setMessageSize(float messageSize) {
        if (mDialogMessage == null) {
            return;
        }
        mMessageSize = messageSize;
        setTextSize(mDialogMessage, mMessageSize);
    }

    //设置消息文字颜色
    public void setMessageColor(int messageColor) {
        if (mDialogMessage == null) {
            return;
        }
//        mMessageColor = messageColor;
//        setTextColor(mDialogMessage, mMessageColor);
    }

    //设置填充区域背景颜色
    public void setDialogLLBackgroundColor(int dialogLLBackgroundColor) {
        if (mDialogLL == null) {
            return;
        }
        mDialogLLBackgroundColor = dialogLLBackgroundColor;
        setViewBackgroundColor(mDialogLL, mDialogLLBackgroundColor);
    }

    /*---------------------------确认按钮-----------------------------*/
    //设置确认按钮文字大小
    public void setCallSize(float callSize) {
        mCallSize = callSize;
        setTextSize(mDialogCall, mCallSize);
    }

    //设置确认按钮文字颜色
    public void setCallColor(int callColor) {
//        mCallColor = callColor;
//        setTextColor(mDialogCall, mCallColor);
    }

    //设置确认按钮背景颜色
//    public void setCallBackgroundColor(int callBackgroundColor) {
//        mCallBackgroundColor = callBackgroundColor;
//        setViewBackgroundColor(mDialogCall, mCallBackgroundColor);
//    }

    /*---------------------------取消按钮-----------------------------*/
    //设置取消按钮文字大小
    public void setCancleSize(float cancleSize) {
        mCancleSize = cancleSize;
        setTextSize(mDialogCancle, mCancleSize);
    }

//    //设置取消按钮文字颜色
//    public void setCancleColor(int cancleColor) {
//        mCancleColor = cancleColor;
//        setTextColor(mDialogCancle, mCancleColor);
//    }
//
//    //设置取消按钮背景颜色
//    public void setCancleBackgroundColor(int cancleBackgroundColor) {
//        mCancleBackgroundColor = cancleBackgroundColor;
//        setViewBackgroundColor(mDialogCancle, mCancleBackgroundColor);
//    }

    //设置返回键是否可取消
    public void setCancelable(Boolean cancelable) {
        mCancelable = cancelable;
        if (mDialog != null) {
            mDialog.setCancelable(mCancelable);
        }
    }

    //设置触摸外部区域是否可取消
    public void setCanceledOnTouchOutside(boolean cancel) {
        mCanceledOnTouchOutside = cancel;
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(cancel);
        }
    }

    public void setShowWidthScale(float showWidthScale) {
        this.showWidthScale = showWidthScale;
    }

    public void setShowHeighScale(float showHeighScale) {
        this.showHeighScale = showHeighScale;
    }

//    //设置消息背景颜色
//    public void setMessageBackgroundColor(int messageBackgroundColor) {
//        if (mDialogMessage == null) {
//            return;
//        }
//        mDialogLLBackgroundColor = messageBackgroundColor;
//        setViewBackgroundColor(mDialogMessage, mMessageBackgroundColor);
//    }

    public TextView getmDialogMessage() {
        return mDialogMessage;
    }

    /**
     * 用于重置Dialog配置
     *
     * @return
     */
    public AlertDialog getDialog() {
        return mDialog;
    }

    public Context getContext() {
        return mContext;
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }


}
