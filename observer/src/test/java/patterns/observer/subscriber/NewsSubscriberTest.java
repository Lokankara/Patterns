package patterns.observer.subscriber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.observer.broker.MessageBroker;
import patterns.observer.model.Article;
import patterns.observer.model.Topic;
import patterns.observer.model.Message;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewsSubscriberTest {

    private NewsSubscriber subscriber;
    private MessageBroker broker;
    private Topic sport;

    @BeforeEach
    void setUp() {
        subscriber = new NewsSubscriber();
        broker = new MessageBroker();
        sport = Topic.SPORT;
    }

    @Test
    void testSubscribeAndUnsubscribe() {
        subscriber.subscribe(sport, broker);
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));

        subscriber.unsubscribe(sport, broker);
        assertFalse(broker.getSubscribers().get(sport).contains(subscriber));
    }

    @Test
    void testUpdated() {
        Message message = new Message("New article published: Sports News");
        subscriber.update(message);
        assertEquals(message, subscriber.getLastMessage());
    }

    @Test
    void testGetArticlesForTopic() {
        Article sportsNews = new Article(sport, "Sports News");
        broker.publishArticle(sportsNews);
        subscriber.subscribe(sport, broker);
        subscriber.getArticles(sport, broker);
        Article news = new Article(sport, "Premier League Transfer News");
        broker.publishArticle(news);
        subscriber.getArticles(sport, broker);
        Queue<Article> articles = broker.getArticles(sport);
        assertEquals(2, articles.size(), "Number of articles does not match expected number");
        articles.forEach(article -> assertEquals(sport, article.topic(), "Article topic does not match expected topic"));
        assertEquals("Message[content=Premier League Transfer News]", subscriber.getLastMessage().toString());
    }

    @Test
    void testSubscribeAndUnsubscribes() {
        subscriber.subscribe(sport, broker);
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));
        subscriber.unsubscribe(sport, broker);
        assertFalse(broker.getSubscribers().get(sport).contains(subscriber));
    }
}
