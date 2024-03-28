package patterns.observer.publisher;

import lombok.AllArgsConstructor;
import patterns.observer.broker.Broker;
import patterns.observer.model.Article;
import patterns.observer.model.Message;
import patterns.observer.model.Topic;
import patterns.observer.subscriber.Subscriber;

import java.util.List;

@AllArgsConstructor
public class EventPublisherRunner
        implements Publisher {

    private final Broker broker;

    void publish(
            final Article article,
            final Runnable runnable) {
        broker.publishArticle(article);
        if (runnable != null) {
            runnable.run();
        }
    }

    void notifyAllSubscriber(
            final Topic topic,
            final Runnable runnable) {
        broker.notifySubscribers(topic, new Message("Message"));
        if (runnable != null) {
            runnable.run();
        }
    }

    void notifySubscribers(
            final Topic topic,
            final Message message,
            final Runnable runnable) {
        broker.notifySubscribers(topic, message);
        if (runnable != null) {
            runnable.run();
        }
    }

    void addSubscriber(
            final Topic topic,
            final Subscriber subscriber,
            final Runnable runnable) {
        broker.register(topic, subscriber);
        if (runnable != null) {
            runnable.run();
        }
    }

    void removeSubscriber(
            final Topic topic,
            final Subscriber subscriber,
            final Runnable runnable) {
        broker.unregister(topic, subscriber);
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override
    public void publish(final Article article) {
        publish(article, null);
    }

    @Override
    public void notifyAllSubscribers(
            final Topic topic) {
        notifyAllSubscriber(topic, null);
    }

    @Override
    public void notifyAllSubscribers(
            final Topic topic,
            final Message message) {
        notifySubscribers(topic, message, null);
    }

    @Override
    public List<Article> getArticles(
            final Topic topic) {
        return broker
                .getArticles(topic)
                .stream()
                .filter(article -> article.topic().equalsIgnoreCase(topic))
                .toList();
    }

    @Override
    public void addSubscriber(
            final Topic topic,
            final Subscriber subscriber) {
        addSubscriber(topic, subscriber, null);
    }

    @Override
    public void removeSubscriber(
            final Topic topic,
            final Subscriber subscriber) {
        removeSubscriber(topic, subscriber, null);
    }
}
