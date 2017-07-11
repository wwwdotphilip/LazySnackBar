package au.com.bywave.snacky;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static au.com.bywave.snacky.R.layout.snacky;

public class Snacky extends RelativeLayout {

    private LayoutInflater mInflater;
    private RelativeLayout parent;
    private TextView textView;
    private Button button;
    private View mView;

    private int mDuration = Duration.getDuration(Duration.SHORT);
    private int colorCode = Type.getColorCode(Type.SUCCESS);
    private int mSpeed = 500;

    private Action mAction;
    private boolean isShowing = false;

    private Handler handler;
    private Runnable runnable;

    private ArrayList<String> messageList = new ArrayList<>();

    public interface Action {
        void onClick();
    }

    public Snacky(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init(context, null);
    }

    public Snacky(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init(context, null);
    }

    public Snacky(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init(context, null);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        Snacky<br/>
     * <br/>
     *
     * <br/>

     * @param context Applicaotin context
     * @param viewById e.g. (ViewGroup) findViewById(android.R.id.content)
     */
    public Snacky(Context context, ViewGroup viewById) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init(context, viewById);
    }

    public void init(Context context, ViewGroup viewGroup) {
        mView = mInflater.inflate(snacky, this, true);
        parent = mView.findViewById(R.id.rlParent);
        parent.setVisibility(INVISIBLE);
        parent.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        textView = mView.findViewById(R.id.tvText);
        textView.setTextColor(Color.WHITE);
        button = mView.findViewById(R.id.btnAction);
        button.setTextColor(Color.WHITE);
        button.setVisibility(GONE);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAction != null) {
                    mAction.onClick();
                    hide();
                }
            }
        });

        if (viewGroup != null) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(parent.getLayoutParams());
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            parent.setLayoutParams(params);
            viewGroup.addView(mView);
        }

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                hide();
            }
        };

        mView.animate().alpha(0.0f).setDuration(0).translationY(80).start();
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        addAction<br/>
     * <br/>
     * Add specific action for snackbar to perform when button is pressed.
     * <br/>
     *
     * @param title  name of action
     * @param action action to perform
     */
    public void addAction(String title, Action action) {
        if (title != null) {
            button.setText(title);
            button.setVisibility(VISIBLE);
        }
        if (action != null) {
            mAction = action;
        }
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setTextColor<br/>
     * <br/>
     * Set text color for snackbar message
     * <br/>
     *
     * @param color int
     */
    public void setTextColor(int color) {
        textView.setTextColor(color);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        addMessage<br/>
     * <br/>
     * Add message to be displayed
     * <br/>
     *
     * @param message String
     */
    public void addMessage(String message) {
        messageList.add(message);
        StringBuilder sBuilder = new StringBuilder();
        for (String item : messageList) {
            sBuilder.append(item).append("\t\t\t");
        }
        setMessage(sBuilder);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        addMessage<br/>
     * <br/>
     * Add message to be displayed
     * <br/>
     *
     * @param messages ArrayList
     */
    public void addMessage(ArrayList<String> messages){
        for (String item : messages) {
            messageList.add(item);
        }
        StringBuilder sBuilder = new StringBuilder();
        for (String item : messageList) {
            sBuilder.append(item).append("\t\t\t");
        }
        setMessage(sBuilder);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setMessage<br/>
     * <br/>
     * Place all messages inside textbox
     * <br/>
     *
     * @param message String
     */
    private void setMessage(StringBuilder message) {
        textView.setText(message);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine(true);
        textView.setMarqueeRepeatLimit(-1);
        textView.setSelected(true);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        getMessages<br/>
     * <br/>
     * Get list of message from list
     * <br/>
     *
     * @return ArrayList
     */
    public ArrayList<String> getMessages() {
        return messageList;
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        removeMessage<br/>
     * <br/>
     * Get list of massage stored
     * <br/>
     *
     * @param index int
     */
    public void removeMessage(int index) {
        messageList.remove(index);
        StringBuilder sBuilder = new StringBuilder();
        for (String item : messageList) {
            sBuilder.append(item).append("\t");
        }
        setMessage(sBuilder);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setBackgroundColor<br/>
     * <br/>
     * set background color for message
     * <br/>
     *
     * @param color int
     */
    public void setBackgroundColor(int color) {
        colorCode = color;
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setDuration<br/>
     * <br/>
     * Set duration for snackbar to hide.
     * <br/>
     *
     * @param duration Duration
     */
    public void setDuration(Duration duration) {
        mDuration = Duration.getDuration(duration);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setDuration<br/>
     * <br/>
     * Set duration for snackbar to hide.
     * <br/>
     *
     * @param duration int in milliseconds
     */
    public void setDuration(int duration) {
        mDuration = duration;
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        setSlideSpeed<br/>
     * <br/>
     * Set how fast snackbar will slide
     * <br/>

     * @param speed int milliseconds
     */
    public void setSlideSpeed(int speed){
        mSpeed = speed;
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        show<br/>
     * <br/>
     * Show snackbar
     * <br/>
     */
    public void show() {
        isShowing = true;
        parent.setVisibility(VISIBLE);
        mView.animate().alpha(1.0f).setDuration(mSpeed).translationY(0).start();
        if (mDuration > 0) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, mDuration);
        }
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        hide<br/>
     * <br/>
     * Hide snackbar
     * <br/>
     */
    public void hide() {
        if (isShowing) {
            isShowing = false;
            handler.removeCallbacks(runnable);
            mView.animate().alpha(0.0f).setDuration(mSpeed).translationY(80).start();
        }
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        type<br/>
     * <br/>
     * Set type of snackbar
     * <br/>
     *
     * @param type Type
     */
    public void type(Type type) {
        colorCode = Type.getColorCode(type);
        parent.setBackgroundColor(colorCode);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        attach<br/>
     * <br/>
     * Attached snackbar to rootview. <b>Note make sure you attached snackbar in order to for snackbar to be displayed.</b><br>
     * e.g. <b>(ViewGroup) findViewById(android.R.id.content)</b>
     * <br/>
     *
     * @param rootView ViewGroup
     */
    public void attach(ViewGroup rootView) {
        rootView.addView(mView);
    }

    /**
     * DEVELOPER:       John<br/>
     * LAST MODIFIED:   7/11/2017<br/>
     * IN CLASS:        isShowing<br/>
     * <br/>
     * Check if snackbar is showing
     * <br/>
     *
     * @return boolean
     */
    public boolean isShowing() {
        return isShowing;
    }
}
