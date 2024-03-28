package patterns.observer.subscriber;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import patterns.observer.model.Topic;
import patterns.observer.broker.Broker;
import patterns.observer.model.Message;

@Slf4j
@Getter
public class NewsSubscriber
        implements Subscriber {

    private Message lastMessage;

    @Override
    public void update(Message message) {
        log.info("Received message: " + message.content());
        lastMessage = message;
    }

    @Override
    public void getArticles(
            Topic topic,
            Broker broker) {
        broker.getArticles(topic)
              .forEach(article -> article
                      .topic().equalsIgnoreCase(topic));
    }

    @Override
    public void subscribe(
            Topic topic,
            Broker broker) {
        broker.register(topic, this);
    }

    @Override
    public void unsubscribe(
            Topic topic,
            Broker broker) {
        broker.unregister(topic, this);
    }
}
