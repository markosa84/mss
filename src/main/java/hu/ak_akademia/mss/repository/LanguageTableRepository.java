package hu.ak_akademia.mss.repository;

import hu.ak_akademia.mss.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageTableRepository extends JpaRepository<Languages, Integer> {
}
