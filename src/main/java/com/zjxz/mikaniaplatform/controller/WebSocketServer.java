package com.zjxz.mikaniaplatform.controller;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author hzzzzzy
 * @date 2023/5/6
 * @description WebSocket服务端
 * WebSocket服务器为 ws://localhost:7529/api/websocket
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 线程安全Set，存放每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> WEBSOCKET_SET = new CopyOnWriteArraySet<>();

    /**
     * 客户端的连接会话
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 会话
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        WEBSOCKET_SET.add(this);
        sendMessage("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        WEBSOCKET_SET.remove(this);
        log.info("连接关闭");
    }

    /**
     * 连接发生错误调用方法
     *
     * @param error 错误
     */
    @OnError
    public void onError(Throwable error) {
        log.error("连接发生错误");
        error.printStackTrace();
    }

    /**
     * 服务器主动推送，发送信息
     */
    public void sendMessage(Object message) {
        try {
            this.session.getBasicRemote().sendText(JSONUtil.toJsonStr(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return WEBSOCKET_SET;
    }
}
