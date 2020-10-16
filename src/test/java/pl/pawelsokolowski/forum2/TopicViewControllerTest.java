package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicViewControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private PostRepository postRepository;

    private Topic topicTest;
    private Post postTest2;

    @BeforeAll
    public void initialize() {
        String topicText = "Test Topic.";
        String postText = "Test Post.";
        String postTest2Text = "Next Test Post.";

        Topic topicTest = new Topic();
        topicTest.setTopic(topicText);
        this.topicRepository.save(topicTest);
        this.topicTest = topicTest;

        Post postTest = new Post();
        postTest.setText(postText);
        postTest.setTopic(topicTest);
        this.postRepository.save(postTest);

        this.postTest2 = new Post();
        this.postTest2.setText(postTest2Text);
        this.postTest2.setTopic(topicTest);
    }

    @Test
    public void shouldReturnTopicView() {
        //given
        String expectedContent = "Topic - " + this.topicTest.getTopic();

        //when
        Object response = this.testRestTemplate.getForObject(
                "http://localhost:" + this.port + "/topic/" + this.topicTest.getId()
                , String.class);
        String responseString = (String) response;

        //then
        Assertions.assertTrue(responseString.contains(expectedContent));
    }

    @Test
    public void shouldRedirectToTopicViewAfterAddingPost() throws URISyntaxException {
        //given
        HttpStatus expectedHttpStatus = HttpStatus.FOUND;
        HttpStatus receivedHttpStatus;
        URI expectedLocation = new URI("http://localhost:" + this.port + "/topic/" + this.topicTest.getId());
        URI receivedLocation;
        MultiValueMap<String, String> args = new LinkedMultiValueMap();
        args.add("new_post", this.postTest2.getText());

        //when
        ResponseEntity<String> response = this.testRestTemplate.postForEntity(
                "http://localhost:" + this.port + "/topic/" + this.topicTest.getId() + "/add_post"
                , args
                , String.class);
        receivedHttpStatus = response.getStatusCode();
        receivedLocation = response.getHeaders().getLocation();

        //then
        Assertions.assertEquals(expectedHttpStatus, receivedHttpStatus);
        Assertions.assertEquals(expectedLocation, receivedLocation);
    }

}
