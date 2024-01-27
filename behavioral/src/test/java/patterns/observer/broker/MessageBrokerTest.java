package patterns.observer.broker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patterns.observer.model.Article;
import patterns.observer.model.Message;
import patterns.observer.model.Topic;
import patterns.observer.publisher.EventPublisher;
import patterns.observer.publisher.Publisher;
import patterns.observer.subscriber.NewsSubscriber;
import patterns.observer.subscriber.Subscriber;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageBrokerTest {

    private Topic sport;
    private Message message;
    private Article sportsNews;
    private Publisher publisher;
    private MessageBroker broker;
    private Subscriber subscriber;

    @BeforeEach
    void setUp() {
        sport = Topic.SPORT;
        broker = new MessageBroker();
        subscriber = new NewsSubscriber();
        publisher = new EventPublisher(broker);
        sportsNews = new Article(sport, "Sports News");
        message = new Message("Articles of Sport Subscriber");
    }

    @Test
    void testSubscribeAndNotify() {
        subscriber.subscribe(sport, broker);
        broker.notifySubscribers(sport, message);
        Assertions.assertEquals(message, subscriber.getLastMessage());
        Assertions.assertEquals(subscriber, broker.getSubscribers().get(sport).get(0));
    }

    @Test
    void testPublishSport() {
        subscriber.subscribe(sport, broker);
        publisher.publish(sportsNews);
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));
    }

    @Test
    void testSubscribeAndNotifySport() {
        subscriber.subscribe(sport, broker);
        broker.notifySubscribers(sport, message);
        Assertions.assertEquals(message, subscriber.getLastMessage());
        Assertions.assertEquals(subscriber, broker.getSubscribers().get(sport).get(0));
    }

    @Test
    void testSubscribeAndNotifies() {
        subscriber.subscribe(sport, broker);
        broker.notifySubscribers(sport, message);
        Assertions.assertEquals(message, subscriber.getLastMessage());
    }

    @Test
    void testPublisher() {
        publisher.publish(sportsNews);
        subscriber.subscribe(sport, broker);
        assertTrue(broker.getArticles().containsKey(sport));
        assertTrue(broker.getSubscribers().containsKey(sport));
        assertTrue(broker.getPublishers().containsKey(sport));
    }

    @Test
    void testPublishAndSubscribe() {
        Topic science = Topic.SCIENCE;
        Topic tech = Topic.TECHNOLOGY;
        Article technology = new Article(tech, "Latest Tech Updates");
        Article scientific = new Article(science, "Scientific Discoveries");

        publisher.publish(sportsNews);
        publisher.publish(scientific);
        publisher.publish(technology);
        subscriber.subscribe(sport, broker);
        subscriber.subscribe(tech, broker);
        broker.publishArticle(sportsNews);
        subscriber.subscribe(sport, broker);
        broker.notifySubscribers(sport, message);

        assertEquals(sportsNews, broker.getArticles().get(sport).peek());
        assertTrue(broker.getArticles().containsKey(sport));
        assertTrue(broker.getSubscribers().containsKey(sport));
        Assertions.assertEquals(subscriber, broker.getSubscribers().get(sport).get(0));
        assertFalse(broker.getSubscribers().get(sport).isEmpty());
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));
        assertTrue(broker.getPublishers().containsKey(tech));
        assertTrue(broker.getSubscribers().containsKey(sport));
    }

    @Test
    void testGetSubscribers() {
        publisher.publish(sportsNews);
        subscriber.subscribe(sport, broker);
        assertTrue(broker.getSubscribers().containsKey(sport));
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));
    }

    @Test
    void testUnregisterSubscriber() {
        broker.register(sport, subscriber);
        assertTrue(broker.getSubscribers().get(sport).contains(subscriber));
        broker.unregister(sport, subscriber);
        assertFalse(broker.getSubscribers().get(sport).contains(subscriber));
    }

    @Test
    void testGetArticlesForTopic() {
        broker.publishArticle(sportsNews);
        Queue<Article> articles = broker.getArticles(sport);
        assertNotNull(articles);
        assertTrue(articles.contains(sportsNews));
    }

    @Test
    void testGetNextArticleForTopic() {
        broker.publishArticle(sportsNews);
        Article nextArticle = broker.getNextArticle(sport);
        assertNotNull(nextArticle);
        assertEquals(sportsNews, nextArticle);
    }

    @Test
    void testGetNextArticleForNonExistentTopic() {
        Topic technology = Topic.TECHNOLOGY;
        Article nextArticle = broker.getNextArticle(technology);
        assertNull(nextArticle);
    }
}
