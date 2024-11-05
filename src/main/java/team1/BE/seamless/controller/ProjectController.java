package team1.BE.seamless.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.BE.seamless.DTO.MemberResponseDTO;
import team1.BE.seamless.DTO.OptionDTO.OptionDetail;
import team1.BE.seamless.DTO.ProjectDTO;
import team1.BE.seamless.DTO.ProjectDTO.ProjectDetail;
import team1.BE.seamless.DTO.ProjectDTO.ProjectDate;
import team1.BE.seamless.service.ProjectService;
import team1.BE.seamless.util.auth.ParsingPram;
import team1.BE.seamless.util.page.ListResult;
import team1.BE.seamless.util.page.PageMapper;
import team1.BE.seamless.util.page.PageResult;
import team1.BE.seamless.util.page.SingleResult;

@Tag(name = "프로젝트")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ParsingPram parsingPram;

    @Autowired
    public ProjectController(ProjectService projectService, ParsingPram parsingPram) {
        this.projectService = projectService;
        this.parsingPram = parsingPram;
    }

    @Operation(summary = "프로젝트 리스트 조회")
    @GetMapping
    public PageResult<ProjectDetail> getProjectList(@Valid ProjectDTO.getList param, HttpServletRequest req) {
        return PageMapper.toPageResult(projectService.getProjectList(param, parsingPram.getEmail(req)));
    }

    @Operation(summary = "프로젝트 조회")
    @GetMapping("/{projectId}")
    public SingleResult<ProjectDetail> getProject(@Valid @PathVariable("projectId") Long id) {
        return new SingleResult<>(projectService.getProject(id));
    }

    @Operation(summary = "프로젝트 기간 리스트 조회")
    @GetMapping("/date")
    public PageResult<ProjectDate> getProjectDate(@Valid ProjectDTO.getList param, HttpServletRequest req) {
        return PageMapper.toPageResult(
            projectService.getProjectDate(param, parsingPram.getEmail(req)));
    }


    /**
     * 이부분은 memberService에도 같은 기능이 있어서 나중에 삭제를 하거나 주석처리를 해도 무방함
     */
    @Operation(summary = "프로젝트 멤버 조회")
    @GetMapping("/{projectId}/members")
    public ListResult<MemberResponseDTO> getProjectMembers(@Valid @PathVariable("projectId") Long id) {
        return new ListResult<>(projectService.getProjectMembers(id));
    }

    @Operation(summary = "프로젝트 옵션 조회")
    @GetMapping("/{projectId}/options")
    public ListResult<OptionDetail> getProjectOptions(@Valid @PathVariable("projectId") Long id) {
        return new ListResult<>(projectService.getProjectOptions(id));
    }

    @Operation(summary = "프로젝트 생성")
    @PostMapping
    public SingleResult<ProjectDetail> createProject(
        @Valid @RequestBody ProjectDTO.ProjectCreate create, HttpServletRequest req) {
        return new SingleResult<>(projectService.createProject(create, parsingPram.getEmail(req)));
    }

    @Operation(summary = "프로젝트 설정 수정")
    @PutMapping("/{projectId}")
    public SingleResult<ProjectDetail> updateProject(
        @Valid @RequestBody ProjectDTO.ProjectUpdate update,
        @PathVariable("projectId") Long id) {
        return new SingleResult<>(projectService.updateProject(id, update));
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    public SingleResult<Long> deleteProject(@Valid @PathVariable("projectId") Long id) {
        return new SingleResult<>(projectService.deleteProject(id));
    }

}
