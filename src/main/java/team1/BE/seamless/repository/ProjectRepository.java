package team1.BE.seamless.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team1.BE.seamless.DTO.ProjectDTO.ProjectDate;
import team1.BE.seamless.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findByIdAndUserEntityEmailAndIsDeletedFalse(Long id, String email);

    Page<ProjectEntity> findAllByUserEntityEmailAndIsDeletedFalse(Pageable pageable, String email);

    Optional<ProjectEntity> findByIdAndIsDeletedFalse(Long id);

}
