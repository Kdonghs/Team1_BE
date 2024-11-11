package team1.BE.seamless.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import team1.be.seamless.dto.OptionDTO.OptionDetail;
import team1.be.seamless.dto.ProjectDTO;
import team1.be.seamless.dto.ProjectDTO.ProjectDate;
import team1.be.seamless.dto.ProjectDTO.ProjectDetail;
import team1.be.seamless.entity.OptionEntity;
import team1.be.seamless.entity.ProjectEntity;
import team1.be.seamless.entity.ProjectOptionEntity;
import team1.be.seamless.entity.UserEntity;
import team1.be.seamless.entity.enums.Role;
import team1.be.seamless.mapper.OptionMapper;
import team1.be.seamless.mapper.ProjectMapper;
import team1.be.seamless.repository.OptionRepository;
import team1.be.seamless.repository.ProjectOptionRepository;
import team1.be.seamless.repository.ProjectRepository;
import team1.be.seamless.repository.UserRepository;
import team1.be.seamless.service.ProjectService;
import team1.be.seamless.util.errorException.BaseHandler;


@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private ProjectOptionRepository projectOptionRepository;

    @Mock
    private ProjectMapper projectMapper;

    @Mock
    private OptionMapper optionMapper;

    private String email;

    private String role;

    private UserEntity userEntity;

    private OptionEntity optionEntity1;

    private OptionEntity optionEntity2;

    private List<ProjectOptionEntity> projectOptions;


    @BeforeEach
    void setUp() {
        email = "user@example.com";
        role = Role.USER.getKey();

        userEntity = new UserEntity(
            "사용자1",
            email,
            "https://example.com/user1.jpg"
        );

        optionEntity1 = new OptionEntity("Option1", "Description1", "TypeA");
        optionEntity2 = new OptionEntity("Option2", "Description2", "TypeB");

        ProjectOptionEntity projectOption1 = new ProjectOptionEntity(optionEntity1);
        ProjectOptionEntity projectOption2 = new ProjectOptionEntity(optionEntity2);
        projectOptions = List.of(projectOption1, projectOption2);
    }

    @Test
    void 프로젝트_리스트_페이지네이션_반환_검증() {
        //Given
        ProjectDTO.getList param = new ProjectDTO.getList();
        ProjectEntity projectEntity1 = new ProjectEntity();
        ProjectEntity projectEntity2 = new ProjectEntity();
        Page<ProjectEntity> projects = new PageImpl<>(List.of(projectEntity1, projectEntity2));
        given(projectRepository.findAllByUserEntityEmailAndIsDeletedFalse(param.toPageable(), email)).willReturn(projects);

        // When
        Page<ProjectDetail> result = projectService.getProjectList(param, email, role);

        // Then
        assertThat(result).isNotNull();
        then(projectRepository).should().findAllByUserEntityEmailAndIsDeletedFalse(param.toPageable(), email);
    }

    @Test
    void 존재_하지_않는_프로젝트_조회_시_예외() {
        // Given
        long projectId = 1L;
        given(projectRepository.findByIdAndIsDeletedFalse(1L)).willReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> projectService.getProject(projectId, role))
            .isInstanceOf(BaseHandler.class);
        then(projectRepository).should().findByIdAndIsDeletedFalse(projectId);
    }

    @Test
    void 프로젝트에_적용된_옵션들_조회() {
        // Given
        long projectId = 1L;
        ProjectEntity projectEntity = new ProjectEntity();
        given(projectRepository.findByIdAndIsDeletedFalse(1L)).willReturn(Optional.of(projectEntity));

        // When
        List<OptionDetail> result = projectService.getProjectOptions(projectId, role);

        // Then
        assertThat(result).isNotNull();
        then(projectRepository).should().findByIdAndIsDeletedFalse(projectId);
    }

    @Test
    void 프로젝트_기간_조회() {
        //Given
        ProjectDTO.getList param = new ProjectDTO.getList();
        ProjectEntity projectEntity1 = new ProjectEntity();
        ProjectEntity projectEntity2 = new ProjectEntity();
        Page<ProjectEntity> projects = new PageImpl<>(List.of(projectEntity1, projectEntity2));
        given(projectRepository.findAllByUserEntityEmailAndIsDeletedFalse(param.toPageable(), email)).willReturn(projects);

        ProjectDate projectDate1 = new ProjectDate();
        ProjectDate projectDate2 = new ProjectDate();
        given(projectMapper.toDate(projectEntity1)).willReturn(projectDate1);
        given(projectMapper.toDate(projectEntity2)).willReturn(projectDate2);

        // When
        Page<ProjectDate> result = projectService.getProjectDate(param, email, role);

        // Then
        assertThat(result).isNotNull();
        then(projectRepository).should().findAllByUserEntityEmailAndIsDeletedFalse(param.toPageable(), email);
    }


    @Test
    void createProject_생성_성공() {
        // Given
        ProjectDTO.ProjectCreate create = new ProjectDTO.ProjectCreate(
            "New Project",
            "Description",
            "https://example.com/project1.jpg",
            List.of(1L, 2L),
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0)
        );

        given(userRepository.findByEmailAndIsDeleteFalse(email)).willReturn(Optional.of(userEntity));

        given(optionRepository.findById(1L)).willReturn(Optional.of(optionEntity1));
        given(optionRepository.findById(2L)).willReturn(Optional.of(optionEntity2));

        given(optionRepository.findByIdIn(create.getOptionIds())).willReturn(List.of(optionEntity1, optionEntity2));

        ProjectEntity projectEntity = new ProjectEntity(
            create.getName(),
            create.getDescription(),
            create.getImageURL(),
            userEntity,
            projectOptions,
            create.getStartDate(),
            create.getEndDate()
        );

        given(projectMapper.toEntity(any(ProjectDTO.ProjectCreate.class), any(UserEntity.class), anyList())).willReturn(projectEntity);
        given(projectRepository.save(projectEntity)).willReturn(projectEntity);

        ProjectDTO.ProjectDetail projectDetail = new ProjectDTO.ProjectDetail(
            1L,
            "New Project",
            "Description",
            "https://example.com/project1.jpg",
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0),
            List.of(1L, 2L),
            0,
            null
        );
        given(projectMapper.toDetail(projectEntity)).willReturn(projectDetail);

        // When
        ProjectDTO.ProjectDetail result = projectService.createProject(create, email, role);

        // Then
        assertThat(result).isEqualTo(projectDetail);
        then(userRepository).should().findByEmailAndIsDeleteFalse(email);
        then(optionRepository).should().findByIdIn(create.getOptionIds());
        then(projectRepository).should().save(projectEntity);
    }

    @Test
    void 프로젝트_수정_검증() {
        // Given
        ProjectDTO.ProjectUpdate update = new ProjectDTO.ProjectUpdate(
            "New Project 2",
            "Description 2",
            "https://example.com/project2.jpg",
            List.of(1L, 2L),
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0)
        );

        given(optionRepository.findById(1L)).willReturn(Optional.of(optionEntity1));
        given(optionRepository.findById(2L)).willReturn(Optional.of(optionEntity2));

        given(optionRepository.findByIdIn(update.getOptionIds())).willReturn(List.of(optionEntity1, optionEntity2));


        ProjectEntity exitsProjectEntity = new ProjectEntity(
            "New Project",
            "Description",
            "https://example.com/project1.jpg",
            userEntity,
            projectOptions,
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0)
        );

        ProjectEntity updatedProjectEntity = new ProjectEntity(
            "New Project 2",
            "Description 2",
            "https://example.com/project2.jpg",
            userEntity,
            projectOptions,
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0)
        );

        ProjectDTO.ProjectDetail projectDetail = new ProjectDTO.ProjectDetail(
            1L,
            "New Project 2",
            "Description 2",
            "https://example.com/project2.jpg",
            LocalDateTime.of(2024,11,21,0,0,0),
            LocalDateTime.of(2025,11,21,0,0,0),
            List.of(1L, 2L),
            0,
            null
        );

        given(projectRepository.findByIdAndIsDeletedFalse(1L)).willReturn(Optional.of(exitsProjectEntity));

        given(projectMapper.toUpdate(any(ProjectEntity.class), any(ProjectDTO.ProjectUpdate.class), anyList())).willReturn(updatedProjectEntity);

        given(projectMapper.toDetail(any(ProjectEntity.class))).willReturn(projectDetail);

        // When
        ProjectDTO.ProjectDetail result = projectService.updateProject(1L, update, role);

        // Then
        assertThat(result).isEqualTo(projectDetail);
        assertThat(result.getName()).isEqualTo("New Project 2");
        assertThat(result.getDescription()).isEqualTo("Description 2");
        assertThat(result.getImageURL()).isEqualTo("https://example.com/project2.jpg");
        then(projectOptionRepository).should().deleteByProjectEntity(exitsProjectEntity);
        then(optionRepository).should().findByIdIn(update.getOptionIds());
    }

    @Test
    void 프로젝트_삭제_검증() {
        // Given
        long projectId = 1L;
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectId);
        projectEntity.setIsDeleted(false);

        given(projectRepository.findByIdAndIsDeletedFalse(projectId)).willReturn(Optional.of(projectEntity));

        // When
        Long result = projectService.deleteProject(projectId, role);

        // Then
        assertThat(result).isEqualTo(projectId);
        assertThat(projectEntity.getIsDeleted()).isTrue();
        then(projectRepository).should().findByIdAndIsDeletedFalse(projectId);
    }

}
