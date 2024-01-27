package patterns.observer.publisher;

import lombok.AllArgsConstructor;
import patterns.observer.broker.Broker;
import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.model.Message;
import patterns.observer.subscriber.Subscriber;

import java.util.List;

@AllArgsConstructor
public class EventPublisherRunner
        implements Publisher {

    private final Broker broker;

    void publish(Article article, Runnable runnable) {
        broker.publishArticle(article);
        if (runnable != null) {
            runnable.run();
        }
    }

    void notifyAllSubscriber(Topic topic, Runnable runnable) {
        broker.notifySubscribers(topic, new Message("Message"));
        if (runnable != null) {
            runnable.run();
        }
    }

    void notifySubscribers(
            Topic topic,
            Message message,
            Runnable runnable) {
        broker.notifySubscribers(topic, message);
        if (runnable != null) {
            runnable.run();
        }
    }

    void addSubscriber(
            Topic topic,
            Subscriber subscriber,
            Runnable runnable) {
        broker.register(topic, subscriber);
        if (runnable != null) {
            runnable.run();
        }
    }

    void removeSubscriber(
            Topic topic,
            Subscriber subscriber,
            Runnable runnable) {
        broker.unregister(topic, subscriber);
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override
    public void publish(Article article) {
        publish(article, null);
    }

    @Override
    public void notifyAllSubscribers(Topic topic) {
        notifyAllSubscriber(topic, null);
    }

    @Override
    public void notifyAllSubscribers(
            Topic topic,
            Message message) {
        notifySubscribers(topic, message, null);
    }

    @Override
    public List<Article> getArticles(Topic topic) {
        return broker
                .getArticles(topic)
                .stream()
                .filter(article -> article.topic().equalsIgnoreCase(topic))
                .toList();
    }

    @Override
    public void addSubscriber(
            Topic topic,
            Subscriber subscriber) {
        addSubscriber(topic, subscriber, null);
    }

    @Override
    public void removeSubscriber(
            Topic topic,
            Subscriber subscriber) {
        removeSubscriber(topic, subscriber, null);
    }
}
