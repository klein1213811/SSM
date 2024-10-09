package com.yt.furn.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 前端和后端之间的Json交互信息，本质就是一个协议
 */
@Setter
@Getter
public class Msg {
    // 状态码
    private int code;
    private String msg;

    // 返回给客户端的数据
    private Map<String, Object> map = new HashMap<>();

    // 编写几个常用的方法
    public static Msg success(){
        Msg msg= new Msg();
        msg.setCode(200);
        msg.setMsg("success");
        return msg;
    }

    public static Msg fail(){
        Msg msg= new Msg();
        msg.setCode(400);
        msg.setMsg("fail");
        return msg;
    }

    // 给返回的msg设置数据
    public Msg add(String key, Object value){
        map.put(key, value);
        return this;
    }
}
