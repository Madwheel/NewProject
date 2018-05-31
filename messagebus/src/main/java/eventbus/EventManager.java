package eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：消息总线管理器
 * 作者：小辉
 * 时间：2018/04/27
 */

public class EventManager implements IEventManager {
    private static EventManager eventManager;
    private List<Object> messageList = new ArrayList<>();

    public EventManager() {
    }

    //获得EventManager的单例
    public static EventManager getEventManager() {
        if (eventManager == null) {
            synchronized (EventManager.class) {
                if (eventManager == null) {
                    eventManager = new EventManager();
                    EventBus.getDefault().register(eventManager);
                }
            }
        }
        return eventManager;
    }

    @Override
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    @Override
    public void event(Object event, OnMessageEvent onMessageEvent) {
        for (int i = 0; i < messageList.size(); i++) {
            Object messageInfo = messageList.get(i);
            Object eventMessage = messageInfo;
            if (messageInfo == null || !event.getClass().getName().equals(messageInfo.getClass().getName())) {
                eventMessage = null;
            }
            if (onMessageEvent != null) {
                onMessageEvent.event(eventMessage);
            }
        }
    }

    @Override
    public void eventOnly(Object event, OnMessageEvent onMessageEvent) {
        for (int i = 0; i < messageList.size(); i++) {
            Object messageInfo = messageList.get(i);
            Object eventMessage = messageInfo;
            if (messageInfo == null || !event.getClass().getName().equals(messageInfo.getClass().getName())) {
                eventMessage = null;
            }
            if (onMessageEvent != null) {
                onMessageEvent.event(eventMessage);
                if (eventMessage != null) {
                    messageList.remove(i);
                }
            }
        }
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object messageEvent) {
        if (messageEvent != null) {
            messageList.add(messageEvent);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        EventBus.getDefault().unregister(eventManager);
        super.finalize();
    }
}
