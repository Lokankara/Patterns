package patterns.observer.publisher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import patterns.observer.model.Topic;
import patterns.observer.subscriber.NewsSubscriber;
import patterns.observer.broker.Broker;
import patterns.observer.model.Article;
import patterns.observer.model.Message;
import patterns.observer.subscriber.Subscriber;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventPublisherRunnerTest {

    private Topic science;
    private Message message;
    private Article article;
    private Broker mockBroker;
    private Runnable mockRunnable;
    private Subscriber mockSubscriber;
    private Queue<Article> articleQueue;
    private EventPublisher eventPublisher;
    private EventPublisherRunner eventPublisherRunner;

    @BeforeEach
    public void setUp() {
        science = Topic.SCIENCE;
        mockBroker = mock(Broker.class);
        mockRunnable = mock(Runnable.class);
        mockSubscriber = new NewsSubscriber();
        message = new Message("New Message");
        article = new Article(science, "Science");
        eventPublisher = new EventPublisher(mockBroker);
        eventPublisherRunner = new EventPublisherRunner(mockBroker);
        Article scienceArticle = new Article(Topic.SCIENCE, "Science");
        Article sportArticle = new Article(Topic.SPORT, "Sports");
        articleQueue = new LinkedList<>(List.of(scienceArticle, sportArticle));
    }

    @Test
    void testPublishCheckInvocation() {
        eventPublisherRunner.publish(article);
        verify(mockBroker, times(1)).publishArticle(article);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testNotifyAllSubscribersWithTopicAndMessageCheckInvocation() {
        Topic topic = Topic.SPORT;
        Message message = new Message("Football");
        eventPublisherRunner.notifyAllSubscribers(topic, message);
        verify(mockBroker, times(1)).notifySubscribers(topic, message);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testAddSubscriberCheckInvocation() {
        Topic topic = Topic.SPORT;
        eventPublisherRunner.addSubscriber(topic, mockSubscriber);
        verify(mockBroker, times(1)).register(topic, mockSubscriber);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testRemoveSubscriberCheckInvocation() {
        Topic topic = Topic.SPORT;
        eventPublisherRunner.removeSubscriber(topic, mockSubscriber);
        verify(mockBroker, times(1)).unregister(topic, mockSubscriber);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testNotifyAllSubscribersWithTopicAndMessage() {
        Topic topic = Topic.SPORT;
        eventPublisherRunner.notifyAllSubscribers(topic, message);
        verify(mockBroker, times(1)).notifySubscribers(topic, message);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testAddSubscriber() {
        Topic topic = Topic.SPORT;
        eventPublisher.addSubscriber(topic, mockSubscriber);
        verify(mockBroker, times(1)).register(topic, mockSubscriber);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testRemoveSubscriber() {
        Topic topic = Topic.SPORT;
        eventPublisher.removeSubscriber(topic, mockSubscriber);
        verify(mockBroker, times(1)).unregister(topic, mockSubscriber);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testGetArticles() {
        when(mockBroker.getArticles(science)).thenReturn(articleQueue);
        List<Article> result = eventPublisherRunner.getArticles(science);
        verify(mockBroker, times(1)).getArticles(science);
        List<Queue<Article>> queue = List.of(articleQueue);
        assertEquals(queue.get(0).poll(), result.get(0));
    }

    @Test
    void testGetArticlesCheckInvocation() {
        when(mockBroker.getArticles(science)).thenReturn(articleQueue);
        eventPublisherRunner.getArticles(science);
        verify(mockBroker, times(1)).getArticles(science);
        verify(mockRunnable, never()).run();
    }

    @Test
    void testNotifyAllSubscriber() {
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        eventPublisherRunner.notifyAllSubscriber(science, mockRunnable);
        verify(mockBroker, times(1)).notifySubscribers(topicCaptor.capture(), messageCaptor.capture());
        assertEquals(science, topicCaptor.getValue());
        assertEquals("Message", messageCaptor.getValue().content());
        verify(mockRunnable, times(1)).run();
    }

    @Test
    void testPublish() {
        Article testArticle = new Article(science, "Science");
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        eventPublisherRunner.publish(testArticle, mockRunnable);
        verify(mockBroker, times(1)).publishArticle(articleCaptor.capture());
        assertEquals(testArticle, articleCaptor.getValue());
        verify(mockRunnable, times(1)).run();
    }

    @Test
    void testNotifySubscribers() {
        Topic testTopic = Topic.SCIENCE;
        Message testMessage = new Message("Test Message");
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        eventPublisherRunner.notifySubscribers(testTopic, testMessage, mockRunnable);
        verify(mockBroker, times(1)).notifySubscribers(topicCaptor.capture(), messageCaptor.capture());
        assertEquals(testTopic, topicCaptor.getValue());
        assertEquals(testMessage, messageCaptor.getValue());
        verify(mockRunnable, times(1)).run();
    }

    @Test
    void testAddSubscribers() {
        Subscriber testSubscriber = mock(Subscriber.class);
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        ArgumentCaptor<Subscriber> subscriberCaptor = ArgumentCaptor.forClass(Subscriber.class);
        eventPublisherRunner.addSubscriber(science, testSubscriber, mockRunnable);
        verify(mockBroker, times(1)).register(topicCaptor.capture(), subscriberCaptor.capture());
        assertEquals(science, topicCaptor.getValue());
        assertEquals(testSubscriber, subscriberCaptor.getValue());
        verify(mockRunnable, times(1)).run();
    }

    @Test
    void testRemoveSubscribers() {
        Subscriber testSubscriber = mock(Subscriber.class);
        ArgumentCaptor<Topic> topicCaptor = ArgumentCaptor.forClass(Topic.class);
        ArgumentCaptor<Subscriber> subscriberCaptor = ArgumentCaptor.forClass(Subscriber.class);
        eventPublisherRunner.removeSubscriber(science, testSubscriber, mockRunnable);
        verify(mockBroker, times(1)).unregister(topicCaptor.capture(), subscriberCaptor.capture());
        assertEquals(science, topicCaptor.getValue());
        assertEquals(testSubscriber, subscriberCaptor.getValue());
        verify(mockRunnable, times(1)).run();
    }
}
