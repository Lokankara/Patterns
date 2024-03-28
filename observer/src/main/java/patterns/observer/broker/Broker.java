package patterns.observer.broker;

import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.subscriber.Subscriber;
import patterns.observer.model.Message;

import java.util.Queue;

public interface Broker  {

    void register(Topic topic, Subscriber subscriber);

    void unregister(Topic topic, Subscriber subscriber);

    void notifySubscribers(Topic topic, Message message);

    void publishArticle(Article article);

    Queue<Article> getArticles(Topic topic);

    Article getNextArticle(Topic topic);
}
