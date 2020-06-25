package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = Forum2Application.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private static Post testPost ;

    @BeforeAll
    public static void initialize(){
        testPost = new Post();
        testPost.setTopic("Test44.");
        testPost.setText("Test text44.");
    }


    @Test
    @Order(1)
    public void shouldIdBeNotNull(){
        postRepository.save(testPost);

        Assertions.assertNotNull(testPost.getId());
    }

    @Test
    @Order(2)
    public void shouldFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getId());
        Assertions.assertTrue(item.isPresent());
    }

    @Test
    @Order(4)
    public void shouldDeleteItem(){
        postRepository.delete(testPost);
        Optional<Post> item = postRepository.findById(testPost.getId());

        Assertions.assertFalse(item.isPresent());
    }

    @Test
    @Order(5)
    public void shouldNotFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getId());

        Assertions.assertFalse(item.isPresent());
    }

    @Test
    @Order(3)
    public void shouldReturnTopic(){
        List<String> topics = postRepository.findDistinctTopic();

        Assertions.assertTrue(topics.contains(testPost.getTopic()));
    }

}
