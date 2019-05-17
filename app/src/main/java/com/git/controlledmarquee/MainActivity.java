package com.git.controlledmarquee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MarqueeTextView marqueeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeTv = findViewById(R.id.marquee_tv);
        //必要条件 xml中无效
        marqueeTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marqueeTv.setSingleLine(true);
        //点击监听
        marqueeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marqueeTv.startScroll();
            }
        });
    }
}
