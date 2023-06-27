package com.common.server.utils;


import com.common.server.domain.Actions;
import com.common.server.domain.Msg;
import com.common.server.domain.User;
import com.common.server.service.ActionsService;
import com.common.server.service.MsgService;
import com.common.server.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Liby
 * @date 2022-04-25 16:21
 * @description:
 * @version:
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocket {
    @Autowired
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static MsgService msgService;
    @Autowired
    public void LiaotianService (MsgService msgService){
        WebSocket.msgService= msgService;
    }
    private static UserService userService;
    @Autowired
    public void usersService (UserService userService){
        WebSocket.userService= userService;
    }
    private static ActionsService actionsService;
    @Autowired
    public void actionsService (ActionsService actionsService){
        WebSocket.actionsService= actionsService;
    }
    private final static Logger logger = LogManager.getLogger(WebSocket.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */

    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private Session session;
    private String userId;

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        //加入map
        webSocketMap.put(userId, this);
        addOnlineCount();           //在线数加1
        logger.info("用户{}连接成功,当前在线人数为{}", userId, getOnlineCount());
//        try {
//            sendMessage("<p style=\"text-align:center;\">连接成功</p>");
//            sendMessage(String.valueOf(this.session.getQueryString()));
//        } catch (IOException e) {
//            logger.error("IO异常");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        webSocketMap.remove(userId);
        subOnlineCount();           //在线数减1
        logger.info("用户{}关闭连接！当前在线人数为{}", userId, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端用户：{} 消息:{}", userId, message);

//        try {
//            Map<String, String> map = MAPPER.readValue(message, new TypeReference<Map<String, String>>() {
//            });
//            String aid = "";
//            String status = "";
//            String rid = "";
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                if (entry.getKey().equals("status")) {
//                    status = entry.getValue();
//                }
//                if (entry.getKey().equals("aid")) {
//                    aid = entry.getValue();
//                }
//                if (entry.getKey().equals("rid")) {
//                    rid = entry.getValue();
//                }
//            }
//            Actions actions = actionsService.findOne(Long.valueOf(aid));
//            User user = userService.findOne(Long.valueOf(userId));
//            String msg=user.getName()+ "给您推送了"+actions.getName()+"的详细信息";
//            List<User> users= userService.sendMsg(userId,Long.valueOf(aid),Long.valueOf(status),Long.valueOf(rid));
//
//            WebsocketClass websocketClass=new WebsocketClass();
//            websocketClass.setPrints(msg);
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            websocketClass.setTimes(sdf.format(new Date()));
//            websocketClass.setUname(aid);
//            for (User u : users) {
//                //群发消息
//                for (String item : webSocketMap.keySet()) {
//                    try {
//                        if (u.getId().intValue()==Long.valueOf(item).intValue()){
//                            webSocketMap.get(item).sendMessage(MAPPER.writeValueAsString(websocketClass));
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            //this.sendMessageByUserId(toUid, MAPPER.writeValueAsString(websocketClass));
//        } catch (IOException  e) {
//            throw new RuntimeException(e);
//        }

    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 通过userId向客户端发送消息
     */
    public void sendMessageByUserId(String userId, String message) throws IOException {
        logger.info("服务端发送消息到{},消息：{}", userId, message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            logger.error("用户{}不在线", userId);
        }

    }

}


