package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicListControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnTopicsListPage_1() {
        //given

        //when
        Object response = this.testRestTemplate.getForObject("http://localhost:" + port, String.class);
        String responseString = (String) response;

        //then
        Assertions.assertTrue(responseString.contains("<title>Forum - topics list</title>"));

    }

    @Test
    public void shouldReturnTopicsListPage_2() {
        //given

        //when
        Object response = this.testRestTemplate.getForObject("http://localhost:" + port + "/topic_list", String.class);
        String responseString = (String) response;

        //then
        Assertions.assertTrue(responseString.contains("<title>Forum - topics list</title>"));

    }
}
