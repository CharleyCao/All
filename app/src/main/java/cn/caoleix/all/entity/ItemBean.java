package cn.caoleix.all.entity;

import cn.caoleix.all.base.Bean;
import cn.caoleix.all.constants.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ItemBean extends Bean<ItemBean.Child> {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Child {

        private int id;
        private String logo;
        private String title;
        private String subTitle;
        private ItemType type;

    }

}
