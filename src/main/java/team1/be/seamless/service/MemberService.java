package team1.be.seamless.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.be.seamless.dto.MemberRequestDTO;
import team1.be.seamless.dto.MemberRequestDTO.UpdateMember;
import team1.be.seamless.dto.MemberRequestDTO.getMemberList;
import team1.be.seamless.dto.MemberResponseDTO;
import team1.be.seamless.entity.MemberEntity;
import team1.be.seamless.entity.ProjectEntity;
import team1.be.seamless.entity.enums.Role;
import team1.be.seamless.mapper.MemberMapper;
import team1.be.seamless.repository.MemberRepository;
import team1.be.seamless.repository.ProjectRepository;
import team1.be.seamless.util.MailSend;
import team1.be.seamless.util.Util;
import team1.be.seamless.util.auth.AesEncrypt;
import team1.be.seamless.util.errorException.BaseHandler;

import java.time.LocalDateTime;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final ProjectRepository projectRepository;
    private final AesEncrypt aesEncrypt;
    private final MailSend mailSend;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper,
                         ProjectRepository projectRepository, AesEncrypt aesEncrypt, MailSend mailSend) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.projectRepository = projectRepository;
        this.aesEncrypt = aesEncrypt;
        this.mailSend = mailSend;
    }

    public MemberResponseDTO getMyMember(Long projectId, String email, String role) {

        if (role == null || role.isEmpty()) {
            throw new BaseHandler(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        MemberEntity memberEntity = memberRepository.findByProjectEntityIdAndEmailAndIsDeleteFalse(
                        projectId, email)
                .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));

        if (memberEntity.getProjectEntity().isExpired()) {
            throw new BaseHandler(HttpStatus.BAD_REQUEST, "프로젝트는 종료되었습니다.");
        }

        return memberMapper.toGetResponseDTO(memberEntity);
    }

    public MemberResponseDTO getMember(Long projectId, Long memberId, String role) {
        if (role == null || role.isEmpty()) {
            throw new BaseHandler(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        MemberEntity memberEntity = memberRepository.findByProjectEntityIdAndIdAndIsDeleteFalse(
                        projectId, memberId)
                .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));

        if (memberEntity.getProjectEntity().isExpired()) {
            throw new BaseHandler(HttpStatus.BAD_REQUEST, "프로젝트는 종료되었습니다.");
        }

        return memberMapper.toGetResponseDTO(memberEntity);
    }

    public Page<MemberResponseDTO> getMemberList(Long projectId,
                                                 getMemberList memberList, String role) {

        if (role == null || role.isEmpty()) {
            throw new BaseHandler(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }

        return memberRepository.findAllByProjectEntityIdAndIsDeleteFalse(projectId,
                memberList.toPageable()).map(memberMapper::toGetResponseDTO);

    }


    @Transactional
    public MemberResponseDTO createMember(MemberRequestDTO.CreateMember create) {


        Long projectId = Long.parseLong(aesEncrypt.decrypt(create.getAttendURL()).split("_")[0]);
        LocalDateTime exp = Util.parseDate(aesEncrypt.decrypt(create.getAttendURL()).split("_")[1]);

        if (exp.isBefore(LocalDateTime.now())) {
            throw new BaseHandler(HttpStatus.FORBIDDEN, "초대 코드가 만료되었습니다.");
        }

        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 프로젝트가 존재하지 않습니다."));

        if (project.isExpired()) {
            throw new BaseHandler(HttpStatus.BAD_REQUEST, "프로젝트는 종료되었습니다.");
        }

        for (MemberEntity member : project.getMemberEntities()) {
            if (member.getEmail().equals(create.getEmail()) && Boolean.FALSE.equals(
                    member.getIsDelete())) {
                throw new BaseHandler(HttpStatus.CONFLICT, "이메일이 중복 됩니다.");
            }
        }

        MemberEntity member = memberMapper.toEntity(create, project);
        memberRepository.save(member);

        String code = aesEncrypt.encrypt(member.getId() + "_" + project.getId());


        String subject = "[프로젝트 초대] 프로젝트 '" + project.getName() + "'에 참여하세요!";
        String message = """
                안녕하세요,
                            
                %s님.
                프로젝트 '%s'에 초대되었습니다.
                            
                프로젝트에 참여하려면 초대 코드를 사용하여 입장해주세요.
                            
                감사합니다.
                            
                참여 코드는 다음과 같습니다:
                %s""".formatted(create.getName(), project.getName(), code);

        mailSend.send(create.getEmail(), subject, message);

        return memberMapper.toCreateResponseDTO(member, code);
    }


    @Transactional
    public MemberResponseDTO updateMember(Long projectId, Long memberId, UpdateMember update,
                                          String role) {

        if (Role.MEMBER.isRole(role)) {
            throw new BaseHandler(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다.");
        }

        MemberEntity member = memberRepository.findByProjectEntityIdAndIdAndIsDeleteFalse(
                        projectId, memberId)
                .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));

        if (member.getProjectEntity().isExpired()) {
            throw new BaseHandler(HttpStatus.BAD_REQUEST, "프로젝트는 종료되었습니다.");
        }

        MemberEntity memberEntity = memberMapper.toUpdate(member, update);
        return memberMapper.toPutResponseDTO(memberEntity);
    }

    @Transactional
    public MemberResponseDTO deleteMember(Long projectId, Long memberId, String role) {

        if (Role.MEMBER.isRole(role)) {
            throw new BaseHandler(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }

        MemberEntity member = memberRepository.findByProjectEntityIdAndIdAndIsDeleteFalse(
                        projectId, memberId)
                .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당 멤버가 존재하지 않습니다."));

        if (member.getProjectEntity().isExpired()) {
            throw new BaseHandler(HttpStatus.BAD_REQUEST, "프로젝트는 종료되었습니다.");
        }

        member.setDelete(true);

        return memberMapper.toDeleteResponseDTO(member);
    }
}
