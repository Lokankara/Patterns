package patterns.observer.broker;

import lombok.Getter;
import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.publisher.EventPublisher;
import patterns.observer.publisher.Publisher;
import patterns.observer.model.Message;
import patterns.observer.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

@Getter
public class MessageBroker implements Broker {

    private final EnumMap<Topic, Publisher> publishers;
    private final EnumMap<Topic, Queue<Article>> articles;
    private final EnumMap<Topic, List<Subscriber>> subscribers;

    public MessageBroker() {
        articles = new EnumMap<>(Topic.class);
        publishers = new EnumMap<>(Topic.class);
        subscribers = new EnumMap<>(Topic.class);
        for (Topic topic : Topic.values()) {
            articles.put(topic, new LinkedList<>());
            subscribers.put(topic, new ArrayList<>());
            publishers.put(topic, new EventPublisher(this));
        }
    }

    @Override
    public void register(
            Topic topic,
            Subscriber subscriber) {
        subscribers.computeIfAbsent(topic, k ->
                new ArrayList<>()).add(subscriber);

    }

    @Override
    public void unregister(
            Topic topic,
            Subscriber subscriber) {
        if (subscribers.get(topic) != null) {
            subscribers.get(topic).remove(subscriber);
        }
    }

    @Override
    public void notifySubscribers(
            Topic topic,
            Message message) {
        if (subscribers.get(topic) != null) {
            subscribers.get(topic).forEach(observer -> observer.update(message));
        }
    }

    @Override
    public void publishArticle(Article article) {
        Topic topic = article.topic();
        articles.computeIfAbsent(topic, k -> new LinkedList<>()).add(article);
        subscribers.computeIfAbsent(topic, k -> new ArrayList<>()).forEach(
                subscriber -> subscriber.update(new Message(article.content())));
    }

    @Override
    public Queue<Article> getArticles(Topic topic) {
        return articles.getOrDefault(topic, new LinkedList<>());
    }

    @Override
    public Article getNextArticle(Topic topic) {
        return articles.getOrDefault(topic, new LinkedBlockingDeque<>()).poll();
    }
}
