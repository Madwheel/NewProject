package eventbus;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/04/27
 */

public interface IEventManager {

    //发送消息
    void post(Object event);

    //发送粘性消息（APP启动时已经注册，意义不大）
    void postSticky(Object event);

    //接收消息（可以被所有人接收）
    void event(Object event, OnMessageEvent onMessageEvent);

    //接收消息（使用该方法接收之后，其他人无法接收）
    void eventOnly(Object event, OnMessageEvent onMessageEvent);

}
