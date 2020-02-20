package cn.caoleix.all.base;

import lombok.Data;

@Data
public class Bean<T> {

    private int code;

    private String message;

    private T data;

    public boolean success() {
        return code == Code.SUCCESS;
    }

    public static class Code {
        public static int SUCCESS = 2000;
    }

}
