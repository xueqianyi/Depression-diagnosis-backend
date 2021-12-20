package com.yiyuplatform.service.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.yiyuplatform.form.ConsultMsgForm;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 大龙
 * \* Date: 2021/11/6
 * \* Time: 20:00
 * \* Description: 通信模块service
 * \
 */
public interface WebSocketService {
    /**
     *  启动服务器websocket服务器时会自动调用一次
     */
    void autoStart();

    /**
     *  获取客户端Token
     */
    String getClientKey(SocketIOClient cilent, String key);

    /**
     *  一个连接断开时被调用一次
     */
    void onDestroy();

    /**
     *  发送数据
     */
    int sendMsg(Object demo);
    /**
     * 全局广播
     * @param demo 发送给所有人
     * @return int
     */
    int sendAll(Object demo);
    /**
     * 向指定用户发信息
     * @param demo 发送的信息主体
     * @param receiverId 接收者id
     * @param event 发送事件名
     * @return int 0- 发送成功 1-发送失败（包括接收者不在线的情况）
     */
    int sendMsg(Map<String,Object> demo, String receiverId, String event);

    /***
     * 检查用户是否在线
     * @param userid
     * @return boolean
     */
    boolean IsUserOnline(String userid);

}
