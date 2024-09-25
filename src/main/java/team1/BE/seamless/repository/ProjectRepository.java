package team1.BE.seamless.repository;

import org.springframework.data.jpa.repository.Query;
import team1.BE.seamless.DTO.ProjectDTO.ProjectPeriod;
import team1.BE.seamless.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Page<ProjectEntity> findAll(Pageable pageable);

    @Query("SELECT new ProjectPeriod(p.id, p.name, p.startDate, p.endDate) FROM Project p")
    Page<ProjectPeriod> findProjectPeriod(Pageable pageable);
}
