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
import team1.BE.seamless.DTO.MemberRequestDTO;
import team1.BE.seamless.DTO.MemberResponseDTO;
import team1.BE.seamless.entity.MemberEntity;
import team1.BE.seamless.service.MemberService;
import team1.BE.seamless.util.page.PageMapper;
import team1.BE.seamless.util.page.PageResult;
import team1.BE.seamless.util.page.SingleResult;

import java.lang.reflect.Member;


@Tag(name = "팀원 관리")
@RequestMapping("/api/project/{projectId}/member")
@RestController
public class MemberController {

    MemberService memberService;

    @Autowired
    MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "팀원 개별 조회")
    @GetMapping("/{memberId}")
    public SingleResult<MemberResponseDTO> getMember(
        @Valid @PathVariable("projectId") Long projectId,
        @Valid @PathVariable("memberId") Long memberId) {
        return new SingleResult<>(memberService.getMember(projectId, memberId));
    }

    @Operation(summary = "팀원 전체 조회")
    @GetMapping
    public PageResult<MemberEntity> getMemberList(@Valid @PathVariable("projectId") Long projectId,
        @Valid MemberRequestDTO.getMemberList memberListRequestDTO) {
        return PageMapper.toPageResult(
            memberService.getMemberList(projectId, memberListRequestDTO));
    }


    @Operation(summary = "새 팀원 추가")
    @PostMapping
    public SingleResult<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO.CreateMember Create) {
        return new SingleResult<>(memberService.createMember(Create));
    }

    @Operation(summary = "팀원 정보 수정")
    @PutMapping("/{memberId}")
    public SingleResult<MemberResponseDTO> updateMember(
        @PathVariable("projectId") Long projectId,
        @PathVariable("memberId") Long memberId,
        @RequestBody MemberRequestDTO.UpdateMember update,
        HttpServletRequest req) {
        return new SingleResult<>(memberService.updateMember(projectId, memberId, update, req));
    }

    @Operation(summary = "팀원 삭제")
    @DeleteMapping("/{memberId}")
    public SingleResult<MemberResponseDTO> deleteMember(
        @PathVariable("projectId") Long projectId,
        @PathVariable("memberId") Long memberId,
        HttpServletRequest req) {

        return new SingleResult<>(memberService.deleteMember(projectId, memberId, req));
    }
}
