package cn.caoleix.all.utils;

import android.app.Activity;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * @author charley
  * @date 2020/2/11 2:31
  * @desc 自定义Activity栈帮助类
  */
public class ActivityStack {

    /************单例开始************/
    private static ActivityStack instance = new ActivityStack();

    private ActivityStack() { }

    public static ActivityStack get() {
        return instance;
    }
    /************单例结束************/

    /**
     * Activity栈
     */
    private Deque<Activity> activities = new ArrayDeque<>();

    /**
     * 将参数activity压站
     * @param activity
     */
    public void push(Activity activity) {
        activities.push(activity);
    }

    /**
     * 获取栈顶的Activity, 并弹栈
     * @return
     */
    public Activity pop() {
        return activities.pop();
    }

    /**
     * 获取栈顶的Activity
     * @return
     */
    public Activity top() {
        return activities.peek();
    }

    /**
     * 栈是否为空
     * @return
     */
    public boolean empty() {
        return activities.size() == 0;
    }

}
