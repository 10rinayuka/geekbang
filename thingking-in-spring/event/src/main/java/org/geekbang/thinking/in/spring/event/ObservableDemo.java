package org.geekbang.thinking.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @author riku
 * @Classname ObservableDemo
 * @Date 2021/2/9 22:02
 * @Description {@link Observer} 示例
 * @see Observer
 */
public class ObservableDemo {

    public static void main(String[] args) {

        EventObservable observable = new EventObservable();

        // 添加 观察者/监听者
        observable.addObserver(new EventObserver());
        // 发布消息/事件
        observable.notifyObservers("Hello world");
    }

    static class EventObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {
        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件: " + eventObject);
        }
    }
}
