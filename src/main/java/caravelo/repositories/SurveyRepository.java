package caravelo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import caravelo.entities.Survey;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    Collection<Survey> findByProviderId(Long providerId);

}
