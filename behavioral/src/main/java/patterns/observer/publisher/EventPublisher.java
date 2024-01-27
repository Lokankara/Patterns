package patterns.observer.publisher;

import lombok.AllArgsConstructor;
import patterns.observer.broker.Broker;
import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.model.Message;
import patterns.observer.subscriber.Subscriber;

import java.util.List;

@AllArgsConstructor
public class EventPublisher
        implements Publisher {

    private final Broker broker;

    @Override
    public void publish(Article article) {
        Message message = new Message("New article published: " + article.content());
        broker.notifySubscribers(article.topic(), message);
    }

    @Override
    public void notifyAllSubscribers(Topic topic) {
        Message message = new Message("New notifyAll Subscribers published");
        notifyAllSubscribers(topic, message);
    }

    @Override
    public void notifyAllSubscribers(Topic topic, Message message) {
        broker.notifySubscribers(topic, message);
    }

    @Override
    public List<Article> getArticles(Topic topic) {
        return broker.getArticles(topic)
                .stream()
                .filter(article -> article.topic().equalsIgnoreCase(topic))
                .toList();
    }

    @Override
    public void addSubscriber(Topic topic, Subscriber subscriber) {
        subscriber.subscribe(topic, broker);
    }

    @Override
    public void removeSubscriber(Topic topic, Subscriber subscriber) {
        subscriber.unsubscribe(topic, broker);
    }
}
