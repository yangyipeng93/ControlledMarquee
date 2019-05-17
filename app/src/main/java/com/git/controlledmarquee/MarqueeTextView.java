package com.git.controlledmarquee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *  create by yyp
 */
@SuppressLint({"AppCompatCustomView"})
public class MarqueeTextView extends TextView implements Runnable {
    private boolean isMeasure = false;
    private int currentScrollX;
    private int textWidth;
    private static int CurrentStatus;
    private static int MarQuee_STOP = 0;
    private static int MarQuee_RUNNING = 1;
    private static int MarQuee_ISBACK = 2;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.isMeasure) {
            this.getTextWidth();
            this.isMeasure = true;
        }

    }

    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getText().toString();
        this.textWidth = (int)paint.measureText(str);
    }

    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        this.isMeasure = false;
    }

    public void run() {
        this.scrollTo(this.currentScrollX, 0);
        ++this.currentScrollX;
        if (CurrentStatus != MarQuee_STOP) {
            if (this.getScrollX() >= this.textWidth) {
                this.scrollTo(-this.getWidth(), 0);
                this.currentScrollX = -this.getWidth();
                CurrentStatus = MarQuee_ISBACK;
            }

            if (CurrentStatus == MarQuee_ISBACK && this.currentScrollX >= 0) {
                this.scrollTo(0, 0);
                this.currentScrollX = 0;
                CurrentStatus = MarQuee_STOP;
            }

            this.postDelayed(this, 10);
        }
    }

    public void startScroll() {
        if (CurrentStatus == MarQuee_STOP) {
            CurrentStatus = MarQuee_RUNNING;
            this.removeCallbacks(this);
            this.post(this);
        } else {
            this.currentScrollX = 0;
            CurrentStatus = MarQuee_STOP;
        }

    }
}
