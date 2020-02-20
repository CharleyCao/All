package cn.caoleix.all.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.caoleix.all.R;
import cn.caoleix.all.base.BaseFragment;
import cn.caoleix.all.constants.ItemType;
import cn.caoleix.all.entity.ItemBean;
import cn.caoleix.all.ui.activity.ArticleActivity;
import cn.caoleix.all.ui.adapter.TabAdapter;

/**
  * @author charley
  * @date 2020/2/11 22:57
  * @desc
  */
@ContentView(R.layout.fragment_tab)
public class TabFragment extends BaseFragment {

    @ViewInject(R.id.recycler_view)
    private RecyclerView recyclerView;

    private List<ItemBean.Child> data = new ArrayList<>();

    private int code;

    public static TabFragment from(int code) {
        TabFragment tabFragment = new TabFragment();
        tabFragment.code = code;
        return tabFragment;
    }

    @Override
    protected void init() {
        data.add(new ItemBean.Child(0, "http://i2.w.yun.hjfile.cn/doc/201303/54c809bf-1eb2-400b-827f-6f024d7d599b_01.jpg", "title", "subTitle", ItemType.article));
        data.add(new ItemBean.Child(0, "http://i2.w.yun.hjfile.cn/doc/201303/54c809bf-1eb2-400b-827f-6f024d7d599b_01.jpg", "title", "subTitle", ItemType.article));
        data.add(new ItemBean.Child(0, "http://i2.w.yun.hjfile.cn/doc/201303/54c809bf-1eb2-400b-827f-6f024d7d599b_01.jpg", "title", "subTitle", ItemType.article));
        data.add(new ItemBean.Child(0, "http://i2.w.yun.hjfile.cn/doc/201303/54c809bf-1eb2-400b-827f-6f024d7d599b_01.jpg", "title", "subTitle", ItemType.article));
        data.add(new ItemBean.Child(0, "http://i2.w.yun.hjfile.cn/doc/201303/54c809bf-1eb2-400b-827f-6f024d7d599b_01.jpg", "title", "subTitle", ItemType.article));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        TabAdapter adapter = new TabAdapter(data);
        adapter.setOnItemClickListener(new TabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(mContext, ArticleActivity.class));
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
