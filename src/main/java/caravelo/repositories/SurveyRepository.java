package caravelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import caravelo.entities.Survey;

import java.util.Collection;

public interface SurveyRepository extends JpaRepository<Survey, Long>{
    Collection<Survey> findByProviderId(Long providerId);
}
