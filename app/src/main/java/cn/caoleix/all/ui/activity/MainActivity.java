package cn.caoleix.all.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.caoleix.all.R;
import cn.caoleix.all.base.BaseActivity;
import cn.caoleix.all.entity.TabsBean;
import cn.caoleix.all.http.RequestManager;
import cn.caoleix.all.http.RetrofitHelper;
import cn.caoleix.all.http.Service;
import cn.caoleix.all.ui.fragment.TabFragment;
import cn.caoleix.all.utils.CLogger;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.tab_layout)
    private TabLayout tabLayout;

    @ViewInject(R.id.view_pager)
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Service service = RetrofitHelper.retrofit().create(Service.class);
        Observable<TabsBean> observable = service.tabsList();
        RequestManager.instance().async(observable, new Observer<TabsBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                CLogger.e("onSubscribe");
            }

            @Override
            public void onNext(TabsBean tabsBean) {
                CLogger.e(tabsBean.toString());
                List<String> titles = new ArrayList<>();
                List<Fragment> fragments = new ArrayList<>();

                for (int i = 0; i < tabsBean.getData().size(); i++) {
                    TabsBean.Child item = tabsBean.getData().get(i);

                    titles.add(item.getTitle());
                    fragments.add(TabFragment.from(item.getId()));
                }

                Adapter adapter = new Adapter(getSupportFragmentManager());
                adapter.setData(fragments, titles);
                viewPager.setAdapter(adapter);

                tabLayout.setupWithViewPager(viewPager);
                viewPager.setOffscreenPageLimit(fragments.size());
            }

            @Override
            public void onError(Throwable e) {
                CLogger.e(e);
            }

            @Override
            public void onComplete() {
                CLogger.e("onComplete");
            }
        });
    }

    private class Adapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;
        private List<String> titles;

        public Adapter(@NonNull FragmentManager fm) {
            super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        public void setData(List<Fragment> fragments, List<String> titles) {
            this.fragments = fragments;
            this.titles = titles;

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return this.titles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
