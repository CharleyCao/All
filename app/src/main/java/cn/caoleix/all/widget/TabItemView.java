package cn.caoleix.all.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.caoleix.all.R;
import cn.caoleix.all.utils.CImageLoader;

public class TabItemView extends LinearLayout {
    public TabItemView(@NonNull Context context) {
        this(context, null);
    }

    public TabItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private ImageView iv;
    private TextView tv;
    private ImageView ivLogo;

    private void init(Context context) {
        inflate(context, R.layout.tab_view_item, this);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        ivLogo = findViewById(R.id.iv_logo);
    }

    public void setText(String text) {
        tv.setText(text);
    }

    public void setIcon(int resId) {
        iv.setImageResource(resId);
    }

    public void setIcon(String url) {
        CImageLoader.newInstance().url(url, iv);
    }

    public void setLogo(String url) {
        CImageLoader.newInstance().url(url, ivLogo);
        ivLogo.setVisibility(View.VISIBLE);
        iv.setVisibility(View.GONE);
        tv.setVisibility(View.GONE);
    }

}
