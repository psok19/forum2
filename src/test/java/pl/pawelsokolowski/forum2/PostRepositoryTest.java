package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = Forum2Application.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private  TopicRepository topicRepository;

    private static Post testPost ;
    private static Topic testTopic;

    @BeforeAll
    public static void initialize(){
        testPost = new Post();
        testTopic = new Topic();

        testTopic.setTopic("Test topic.");
        testPost.setTopic(testTopic);
        testPost.setText("Test text44.");
    }


    @Test
    @Order(1)
    public void shouldIdBeNotNull(){
        topicRepository.save(testTopic);
        postRepository.save(testPost);

        Assertions.assertNotNull(testPost.getPostId());
    }

    @Test
    @Order(2)
    public void shouldFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getPostId());
        Assertions.assertTrue(item.isPresent());
    }

    @Test
    @Order(4)
    public void shouldDeleteItem(){
        postRepository.delete(testPost);
        topicRepository.delete(testTopic);
        Optional<Post> item = postRepository.findById(testPost.getPostId());

        Assertions.assertFalse(item.isPresent());
    }

    @Test
    @Order(5)
    public void shouldNotFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getPostId());

        Assertions.assertFalse(item.isPresent());
    }
    
}
