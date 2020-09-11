package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = Forum2Application.class)
public class TopicRepositoryTest {
    @Autowired
    private TopicRepository topicRepository;

    private static Topic testTopic1;
    private static Topic testTopic2;

    @BeforeAll
    public static void initialize() {
        testTopic1 = new Topic();
        testTopic1.setTopic("Test Topic 1.");
        testTopic2 = new Topic();
        testTopic2.setTopic("Test Topic 2.");
    }


    @Test
    @Order(1)
    public void shouldIdBeNotNull() {
        topicRepository.deleteAll();
        topicRepository.save(testTopic1);
        System.out.println(testTopic1.getId().toString());
        Assertions.assertNotNull(testTopic1.getId());
    }

    @Test
    @Order(2)
    public void shouldFindItem() {
        Optional<Topic> item = topicRepository.findById(testTopic1.getId());
        System.out.println(testTopic1.getId().toString());
        Assertions.assertTrue(item.isPresent());
    }

    @Test
    @Order(3)
    public void shouldFind2Items(){
        //given
        int sizeActual = 0;
        int sizeExpected = 2;

        //when
        topicRepository.save(testTopic2);
        Iterable<Topic> topics = topicRepository.findAll();
        Iterator<Topic> topicIterator = topics.iterator();
        while (topicIterator.hasNext()){
            sizeActual++;
            topicIterator.next();
        }

        //then
        Assertions.assertTrue(sizeActual == sizeExpected);

    }

    @Test
    @Order(99)
    public void shouldDeleteItem() {
        topicRepository.delete(testTopic1);
        Optional<Topic> item = topicRepository.findById(testTopic1.getId());

        Assertions.assertFalse(item.isPresent());
    }

    @Test
    @Order(100)
    public void shouldNotFindItem() {
        Optional<Topic> item = topicRepository.findById(testTopic1.getId());

        Assertions.assertFalse(item.isPresent());
    }

}
