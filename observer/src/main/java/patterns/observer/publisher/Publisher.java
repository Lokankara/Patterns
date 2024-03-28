package patterns.observer.publisher;

import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.model.Message;
import patterns.observer.subscriber.Subscriber;

import java.util.List;

public interface Publisher  {

    void publish(Article article);

    void notifyAllSubscribers(Topic topic);

    void notifyAllSubscribers(Topic topic, Message message);

    List<Article> getArticles(Topic topic);

    void addSubscriber(Topic topic, Subscriber subscriber);

    void removeSubscriber(Topic topic, Subscriber subscriber);
}
