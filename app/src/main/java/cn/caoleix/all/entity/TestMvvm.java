package cn.caoleix.all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestMvvm {

    private String hello;

    public String getHello() {
        return hello;
    }

}
