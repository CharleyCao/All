package cn.caoleix.all.utils;

import com.google.gson.Gson;

public class JsonUtil {

    public static <T> T parse(String string, Class<T> clz) {
        return new Gson().fromJson(string, clz);
    }

}
