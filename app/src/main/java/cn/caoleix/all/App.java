package cn.caoleix.all;

import org.xutils.x;

import cn.caoleix.all.base.BaseApp;
import cn.caoleix.all.utils.CImageLoader;

public class App extends BaseApp {

    private static App appInstance;

    public static App get() {
        return appInstance;
    }

    @Override
    protected void init() {
        super.init();
        appInstance = this;

        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(BuildConfig.DEBUG);

        CImageLoader.init(this);
    }
}
