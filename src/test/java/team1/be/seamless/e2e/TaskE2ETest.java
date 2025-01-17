package team1.be.seamless.e2e;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team1.be.seamless.dto.TaskDTO;
import team1.be.seamless.dto.TaskDTO.TaskCreate;
import team1.be.seamless.entity.enums.Priority;
import team1.be.seamless.entity.enums.Role;
import team1.be.seamless.entity.enums.TaskStatus;
import team1.be.seamless.service.ProjectService;
import team1.be.seamless.service.TaskService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskE2ETest {

    @LocalServerPort
    private int port;
    private String url = "http://localhost:";
    private final TestRestTemplate restTemplate;
    private String token;
    private HttpHeaders headers = new HttpHeaders();
    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public TaskE2ETest(TestRestTemplate restTemplate, TaskService taskService,
                       ProjectService projectService) {
        this.restTemplate = restTemplate;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @BeforeEach
    public void setUp() {
        HttpEntity<Long> requestEntity = new HttpEntity<>(null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/test/userToken/1",
                POST,
                requestEntity, String.class);

        int startIndex = responseEntity.getBody().indexOf("\"token\":\"") + "\"token\":\"".length();
        int endIndex = responseEntity.getBody().indexOf("\"", startIndex);

        token = responseEntity.getBody().substring(startIndex, endIndex);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
    }

    @Test
    public void 태스크_생성() {
        TaskCreate body = new TaskCreate("태스크1", "첫번째 태스크입니다.",
                null, LocalDateTime.of(2024, 10, 10, 0, 0),
                LocalDateTime.of(2025, 5, 3, 1, 0, 0),
                Priority.HIGH, TaskStatus.IN_PROGRESS, 1);

        HttpEntity<Long> requestEntity = new HttpEntity(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/project/1/task", POST, requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void 태스크_시작_시간이_프로젝트_일정_범위보다_이를_경우_실패() {
        TaskCreate body = new TaskCreate("태스크1", "첫번째 태스크입니다.",
                1L, LocalDateTime.of(2001, 10, 10, 0, 0),
                LocalDateTime.of(2025, 5, 3, 1, 0, 0),
                Priority.HIGH, TaskStatus.IN_PROGRESS, 1);

        HttpEntity<Long> requestEntity = new HttpEntity(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/project/2/task", POST, requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @Test
    public void 태스크_마감_시간이_프로젝트_일정_범위보다_늦을_경우_실패() {
        TaskCreate body = new TaskCreate("태스크1", "첫번째 태스크입니다.",
                1L, LocalDateTime.of(2024, 12, 1, 0, 0),
                LocalDateTime.of(2100, 5, 3, 1, 0, 0),
                Priority.HIGH, TaskStatus.IN_PROGRESS, 1);

        HttpEntity<Long> requestEntity = new HttpEntity(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/project/2/task", POST, requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(BAD_REQUEST);
    }

    @DirtiesContext
    @Test
    public void 프로젝트_삭제시_태스크_조회_실패() {
        projectService.deleteProject(2L, "user1@google.com", Role.USER.toString());

        HttpEntity<Long> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/project/task/2", GET, requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    public void 태스크_수정_성공() {
        TaskDTO.TaskUpdate body = new TaskDTO.TaskUpdate(
                "수정된 태스크",
                "수정된 태스크입니다.",
                50,
                1L,
                LocalDateTime.of(2024, 12, 1, 0, 0, 0),
                LocalDateTime.of(2024, 12, 2, 0, 0, 0),
                Priority.HIGH,
                TaskStatus.IN_PROGRESS);

        HttpEntity<Long> requestEntity = new HttpEntity(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + port + "/api/project/task/1", PUT, requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
    }

}