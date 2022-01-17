package com.dawn.dlibrary.log;

import androidx.annotation.NonNull;

/**
 * @author: LXL
 * @description: 1.打印堆栈信息
 * 2.File输出
 * 3.模拟控制台
 * @date: 2021/12/10 10:00
 */
public class DiLog {
    public static void log(@DiLogType.TYPE int type, Object... contents) {
    }

    public static void log(@DiLogType.TYPE int type, @NonNull String tag, Object... contents) {
    }

    public static void v(Object... contents) {
        log(DiLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(DiLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(DiLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(DiLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(DiLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(DiLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(DiLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(DiLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(DiLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(DiLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(DiLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(DiLogType.A, tag, contents);
    }

}
