package cn.caoleix.all.entity;

import java.util.List;

import cn.caoleix.all.base.Bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TabsBean extends Bean<List<TabsBean.Child>> {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Child {

        private int id;
        private String title;

    }

}
