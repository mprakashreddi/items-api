package integration.com.noname.items;

import com.nonname.items.Application;
import com.nonname.items.client.database.Item;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes=Application.class,
        webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int port;

    private Item newItem = new Item();
    private static final Long SAMPLE_ID = 999L;
    private static Long id;

    @Before
    public void setup() {
        newItem.setName("test");
    }

    @Test
    public void test1_delete_customer_failure() {
        webTestClient.delete().uri("/items/{id}", SAMPLE_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    public void test2_add_customer_done() {
        WebClient webClient = WebClient.create("http://localhost:" + port);
        Item item = webClient.post().uri("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(newItem))
                .retrieve()
                .bodyToMono(Item.class)
                .block();

        id = item.getId();
       // assertThat(item.getName(), is(newItem.getName()));
    }

    @Test
    public void test3_find_customer_done() {
        webTestClient.get().uri("/items/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name")
                .isEqualTo(newItem.getName());
    }

    @Test
    public void test4_findAll_customer_done() {
        Flux<Item> customers = webTestClient.get().uri("/items")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Item.class).getResponseBody();

        StepVerifier
                .create(customers)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void test5_delete_customer_done() {
        webTestClient.delete().uri("/items/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void test6_find_customer_failure() {
        webTestClient.get().uri("/items/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
