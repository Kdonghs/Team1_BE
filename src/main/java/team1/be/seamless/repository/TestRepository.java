package team1.be.seamless.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team1.be.seamless.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    Page<TestEntity> findAll(Pageable pageable);
}
