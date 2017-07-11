package au.com.bywave.snacky;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Snacky extends RelativeLayout {

    private LayoutInflater mInflater;
    private RelativeLayout parent;
    private TextView textView;
    private Button button;
    private View mView;

    private int mDuration = Duration.getDuration(Duration.SHORT);
    private int colorCode = Type.getColorCode(Type.SUCCESS);

    private Action mAction;
    private boolean isShowing = false;

    private Handler handler;
    private Runnable runnable;

    public interface Action{
        void onClick();
    }

    public Snacky(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public Snacky(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public Snacky(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        mView = mInflater.inflate(R.layout.snacky, this, true);
        parent = mView.findViewById(R.id.rlParent);
        parent.setVisibility(INVISIBLE);
        textView = mView.findViewById(R.id.tvText);
        textView.setTextColor(Color.WHITE);
        button = mView.findViewById(R.id.btnAction);
        button.setTextColor(Color.WHITE);
        button.setVisibility(GONE);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAction != null){
                    mAction.onClick();
                }
            }
        });
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hide();
            }
        };
        mView.animate().setDuration(0).translationY(65).start();
    }

    public void addAction(String title, Action action) {
        if (title != null) {
            button.setText(title);
            button.setVisibility(VISIBLE);
        }
        if (action != null){
            mAction = action;
        }
    }

    public void setTextColor(int color){
        textView.setTextColor(color);
    }

    public void setMessage(String message){
        textView.setText(message);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine(true);
        textView.setMarqueeRepeatLimit(-1);
        textView.setSelected(true);
    }

    public void setBackgroundColor(int color){
        colorCode = color;
    }

    public void setDuration(Duration duration){
        mDuration = Duration.getDuration(duration);
    }

    public void setDuration(int duration){
        mDuration = duration;
    }

    public void show() {
        isShowing = true;
        parent.setVisibility(VISIBLE);
        mView.animate().setDuration(500).translationY(0).start();
        if (mDuration > 0) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, mDuration);
        }
    }

    public void hide(){
        if (isShowing) {
            isShowing = false;
            handler.removeCallbacks(runnable);
            mView.animate().setDuration(500).translationY(65).start();
        }
    }

    public void type(Type type){
        colorCode = Type.getColorCode(type);
        parent.setBackgroundColor(colorCode);
    }

    public boolean isShowing(){
        return isShowing;
    }
}
