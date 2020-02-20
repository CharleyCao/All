package cn.caoleix.all.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.caoleix.all.R;
import cn.caoleix.all.base.BaseActivity;
import cn.caoleix.all.databinding.ActivityArticleBinding;
import cn.caoleix.all.entity.TabsBean;
import cn.caoleix.all.entity.TestMvvm;
import cn.caoleix.all.utils.AssetsUtil;

/**
  * @author charley
  * @date 2020/2/12 22:51
  * @desc 文章页面
  */
@ContentView(R.layout.activity_article)
public class ArticleActivity extends BaseActivity {

    @ViewInject(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityArticleBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_article);
//        viewDataBinding.setHello(new TestMvvm("hello mvvm"));
        RichText.from(AssetsUtil.getAsString("markdown.md")).type(RichType.markdown).into(tv);
    }
}
