package com.dawn.dlibrary.log;

/**
 * @author: LXL
 * @description: log配置
 * @date: 2021/12/10 10:24
 */
public abstract class DiLogConfig {
    //全局Tag
    public String getGlobalTag(){
        return "DiLog";
    }

    //是否启用
    public boolean enable(){
        return true;
    }
}
