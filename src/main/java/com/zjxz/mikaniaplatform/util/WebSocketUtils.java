package com.zjxz.mikaniaplatform.util;

import cn.hutool.json.JSONUtil;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocketUtils {

    /**
     * 定义存储Session的容器
     */
    private final static CopyOnWriteArraySet<WebSocketSession> SESSION_SETS = new CopyOnWriteArraySet<>();

    /**
     * 添加会话
     */
    public static void add(WebSocketSession socketSession) {
        SESSION_SETS.add(socketSession);
    }

    /**
     * 移除会话
     */
    public static void remove(WebSocketSession socketSession) {
        SESSION_SETS.remove(socketSession);
    }

    /**
     * 发送消息
     */
    public static void sendMessage(Object msg) {
        TextMessage textMessage = new TextMessage(JSONUtil.toJsonStr(msg));
        try {
            for (WebSocketSession socketSession : SESSION_SETS) {
                socketSession.sendMessage(textMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}