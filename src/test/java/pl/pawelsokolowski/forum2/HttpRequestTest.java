package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


//    @Test
//    public void shouldReturnSaved() {
//        //given
//        URI uri = URI.create("http://localhost:" + port + "/add");
//        MultiValueMap<String, String> post = new LinkedMultiValueMap<>();
//        post.add("topic", "Test.");
//        post.add("text", "Test text.");
//
//        //when
//        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(uri, post, String.class);
//
//        //then
//        Assertions.assertTrue(responseEntity.getBody().contains("Saved"));
//    }


//    @Test
//    public void shouldReturnNewTopicView() {
//        //given
//        String expectedResponseContent = "<title>Forum - New Topic</title>";
//
//        //when
//        String response = this.restTemplate.getForObject("http://localhost:" + port + "/new_topic.html", String.class);
//
//        //then
//        Assertions.assertTrue(response.contains(expectedResponseContent));
//
//    }



}
