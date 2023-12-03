package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.AreaOfExpertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AreaOfExpertiseRepository extends JpaRepository<AreaOfExpertise, Integer> {

    @Query("SELECT a FROM AreaOfExpertise a WHERE a.name = :name")
    Optional<AreaOfExpertise> getByname(@Param("name") String name);
}
