package team1.be.seamless.mapper;

import org.springframework.stereotype.Component;
import team1.be.seamless.dto.MemberRequestDTO.CreateMember;
import team1.be.seamless.dto.MemberRequestDTO.UpdateMember;
import team1.be.seamless.dto.MemberResponseDTO;
import team1.be.seamless.entity.MemberEntity;
import team1.be.seamless.entity.ProjectEntity;

@Component
public class MemberMapper {

    public MemberEntity toEntity(CreateMember create, ProjectEntity project) {

        return new MemberEntity(
            create.getName(),
            "팀원",
            create.getEmail(),
            "",
            project
        );
    }

    public MemberEntity toUpdate(MemberEntity member, UpdateMember update) {
        member.setEmail(update.getEmail());
        member.setImageURL(update.getImageURL());
        member.setRole(update.getRole());
        member.setName(update.getName());
        return member;
    }

    public MemberResponseDTO toGetResponseDTO(MemberEntity memberEntity) {
        return new MemberResponseDTO("성공적으로 조회되었습니다.",
            memberEntity.getName(),
            memberEntity.getRole(),
            memberEntity.getEmail(),
            memberEntity.getId()
        );
    }

    public MemberResponseDTO toDeleteResponseDTO(MemberEntity memberEntity) {
        return new MemberResponseDTO("성공적으로 삭제되었습니다.",
            memberEntity.getName(),
            memberEntity.getRole(),
            memberEntity.getEmail());
    }

    public MemberResponseDTO toCreateResponseDTO(MemberEntity memberEntity, String attendURL) {
        return new MemberResponseDTO("성공적으로 생성되었습니다.",
            memberEntity.getName(),
            memberEntity.getRole(),
            memberEntity.getEmail(),
            attendURL);
    }

    public MemberResponseDTO toPutResponseDTO(MemberEntity memberEntity) {
        return new MemberResponseDTO("성공적으로 수정되었습니다.",
            memberEntity.getName(),
            memberEntity.getRole(),
            memberEntity.getEmail());
    }

}
