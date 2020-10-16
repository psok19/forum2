package pl.pawelsokolowski.forum2;

import org.junit.jupiter.api.Assertions;
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
public class NewTopicControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnRedirectionToRoot() throws URISyntaxException {
        //given
        HttpStatus expectedResponse = HttpStatus.FOUND;
        HttpStatus receivedResponse;
        URI expectedLocation = new URI("http://localhost:" + port + "/");
        URI receivedLocation;
        MultiValueMap<String, String> args = new LinkedMultiValueMap();
        args.add("topic", "Test.");
        args.add("first_post", "My first post test.");


        //when
        ResponseEntity<String> response = this.testRestTemplate.postForEntity("http://localhost:" + port + "/topic/add", args, String.class);
        receivedResponse = response.getStatusCode();
        receivedLocation = response.getHeaders().getLocation();

        //then
        Assertions.assertEquals(expectedResponse,receivedResponse);
        Assertions.assertEquals(expectedLocation, receivedLocation);
    }



}
