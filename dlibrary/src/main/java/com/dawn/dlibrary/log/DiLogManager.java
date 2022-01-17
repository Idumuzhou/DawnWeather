package com.dawn.dlibrary.log;

import androidx.annotation.NonNull;

/**
 * @author: LXL
 * @description: Log管理
 * @date: 2021/12/10 10:26
 */
public class DiLogManager {
    private DiLogConfig config;
    private static DiLogManager instance;

    private DiLogManager(DiLogConfig config){
        this.config = config;
    }

    public static DiLogManager getInstance(){
        return instance;
    }

    public static void init(@NonNull DiLogConfig config){
        instance = new DiLogManager(config);
    }

    public DiLogConfig getConfig() {
        return config;
    }
}
