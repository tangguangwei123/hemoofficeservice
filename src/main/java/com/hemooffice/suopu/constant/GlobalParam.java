package com.hemooffice.suopu.constant;

import java.util.ArrayList;
import java.util.Arrays;

public class GlobalParam {
    //redis存储生命期
    public final static long CACHETIME = 1800;
    //公钥文件名
    public final static String PUBKEY = "/key/pub.txt";
    //密钥文件名
    public final static String PRIKEY = "/key/pri.txt";
    //不需要刷新登录过期时间的路径
    public final static ArrayList<String> IGNOREREFRESHSERVLETPATH = new ArrayList<>(Arrays.asList("/api/message/unreadmsgcount"));
}
