package team1.BE.seamless.mapper;


import java.util.List;
import org.springframework.stereotype.Component;
import team1.BE.seamless.DTO.ProjectDTO;
import team1.BE.seamless.DTO.ProjectDTO.ProjectDetail;
import team1.BE.seamless.DTO.ProjectDTO.ProjectDate;
import team1.BE.seamless.entity.OptionEntity;
import team1.BE.seamless.entity.ProjectEntity;
import team1.BE.seamless.entity.ProjectOption;
import team1.BE.seamless.entity.UserEntity;

@Component
public class ProjectMapper {

    public ProjectEntity toEntity(ProjectDTO.ProjectCreate create, UserEntity userEntity,
        List<ProjectOption> projectOptions) {
        return new ProjectEntity(
            create.getName(),
            userEntity,
            projectOptions,
            create.getStartDate(),
            create.getEndDate()
        );
    }

    public ProjectDetail toDetail(ProjectEntity projectEntity) {
        return new ProjectDTO.ProjectDetail(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate(),
            projectEntity.getProjectOptions().stream().map(ProjectOption::getOptionEntity).map(OptionEntity::getId).toList()
        );
    }

    public ProjectDate toPeriod(ProjectEntity projectEntity) {
        return new ProjectDate(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate()
        );
    }

}
