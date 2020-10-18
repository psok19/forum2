package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    public void initialize(){
        testPost = new Post();
        testTopic = new Topic();

        testTopic.setTopic("Test topic.");
        testPost.setTopic(testTopic);
        testPost.setText("Test text44.");
    }


    @Test
    @Order(10)
    public void shouldIdBeNotNull(){
        topicRepository.save(testTopic);
        postRepository.save(testPost);

        Assertions.assertNotNull(testPost.getPostId());
    }

    @Test
    @Order(20)
    public void shouldFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getPostId());
        Assertions.assertTrue(item.isPresent());
    }

    @Test
    @Order(30)
    public void shouldFindItemByTopicId(){
        //given
        int expectedTopicId = testTopic.getId();
        int actualTopicId;
        int expectedNumberOfItems = 1;
        int actualNumberOfItems;


        //when
        Iterable<Post> actualPosts = postRepository.findAllPostsByTopicId(expectedTopicId);
        ArrayList<Post> actualPostsList = (ArrayList<Post>)actualPosts;
        actualTopicId = actualPostsList.get(0).getTopic().getId();
        actualNumberOfItems = actualPostsList.size();

        //then
        Assertions.assertEquals(expectedNumberOfItems, actualNumberOfItems);
        Assertions.assertEquals(expectedTopicId, actualTopicId);

    }

    @Test
    @Order(40)
    public void shouldDeleteItem(){
        postRepository.delete(testPost);
        topicRepository.delete(testTopic);
        Optional<Post> item = postRepository.findById(testPost.getPostId());

        Assertions.assertFalse(item.isPresent());
    }

    @Test
    @Order(50)
    public void shouldNotFindItem(){
        Optional<Post> item = postRepository.findById(testPost.getPostId());

        Assertions.assertFalse(item.isPresent());
    }

    @AfterAll
    public void clear(){
        postRepository.deleteAll();
        topicRepository.deleteAll();
    }
    
}
