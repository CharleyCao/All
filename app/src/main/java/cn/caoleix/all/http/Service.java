package cn.caoleix.all.http;

import cn.caoleix.all.entity.TabsBean;
import io.reactivex.Observable;
import retrofit2.http.POST;

/**
  * @author charley
  * @date 2020/2/12 1:57
  * @desc
  */
public interface Service {

    @POST("tabs/list")
    Observable<TabsBean> tabsList();

}
