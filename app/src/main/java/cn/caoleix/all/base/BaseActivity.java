package cn.caoleix.all.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xutils.x;

/**
  * @author charley
  * @date 2020/2/11 2:45
  * @desc activity基类
  */
public class BaseActivity extends AppCompatActivity {

    protected BaseActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mContext = this;
    }

}
