package patterns.observer.subscriber;

import patterns.observer.broker.Broker;
import patterns.observer.model.Topic;
import patterns.observer.model.Message;

public interface Subscriber {
    void subscribe(
            Topic topic,
            Broker broker);

    void unsubscribe(
            Topic topic,
            Broker broker);

    void getArticles(
            Topic sport,
            Broker broker);

    void update(Message message);

    Message getLastMessage();
}
