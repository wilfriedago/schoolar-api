package dev.thewlabs.schoolar.domain.groups;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer;

    public GroupControllerTest() {
        try (PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")) {

            container.withDatabaseName("schoolar");
            container.withUsername("schoolar");
            container.withPassword("schoolar"); // TODO: Use env vars

            container.start();

            GroupControllerTest.postgreSQLContainer = container;
        } catch (Exception e) {
            throw new RuntimeException("Failed to start PostgreSQL container", e);
        }
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        System.out.println("Hello world");
        // TODO: Create a group, and add a student to it
    }

    @Test
    void testListGroups() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/groups")) // TODO: Use constant for api path and version
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
