package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void shouldReturnNewPostPage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/new_post.html", String.class))
                .contains("<title>Forum - New Post</title>");
    }

    @Test
    public void shouldReturnSaved() {
        //given
        URI uri = URI.create("http://localhost:" + port + "/add");
        MultiValueMap<String, String> post = new LinkedMultiValueMap<>();
        post.add("topic", "Test.");
        post.add("text", "Test text.");

        //when
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, post, String.class);

        //then
        Assertions.assertTrue(responseEntity.getBody().contains("Saved"));
    }

    @Test
    public void shouldReturnTopicsListPage() {
        //given

        //when
        Object response = this.restTemplate.getForObject("http://localhost:" + port, String.class);
        String responseString = (String) response;

        //then
        Assertions.assertTrue(responseString.contains("<title>Forum - topics list</title>"));


    }

    @Test
    public void shouldReturnNewTopicView() {
        //given
        String expectedResponseContent = "<title>Forum - New Topic</title>";

        //when
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/new_topic.html", String.class);

        //then
        Assertions.assertTrue(response.contains(expectedResponseContent));

    }

    @Test
    public void shouldReturn_Created_AsResponse() {
        //given
        String expectedResponse = "Forum - topics list";
        MultiValueMap<String, String> args = new LinkedMultiValueMap();
        args.add("topic", "Test.");
        args.add("first_post", "My first post test.");

        //when
        String response = this.restTemplate.postForObject("http://localhost:" + port + "/topic/add", args, String.class);

        //then
        Assertions.assertTrue(response.contains(expectedResponse));
    }


}
