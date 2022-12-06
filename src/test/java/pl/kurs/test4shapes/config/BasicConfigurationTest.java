package pl.kurs.test4shapes.config;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kurs.test4shapes.Test4ShapesApplication;

import javax.annotation.security.RunAs;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test4ShapesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application.properties")
class BasicConfigurationIntegrationTest {

    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort int port;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("admin", "admin");
        base = new URL("http://localhost:" + port);
    }

//    @Test
//    public void whenLoggedUserRequestsHomePage_ThenSuccess()
//            throws IllegalStateException, IOException {
//        ResponseEntity<String> response =
//                restTemplate.getForEntity(base.toString(), String.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody().contains("Baeldung"));
//    }

    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage()
            throws Exception {

        restTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().contains("Unauthorized"));
    }
}