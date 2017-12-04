package caravelo.repositories;

import caravelo.entities.Survey;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SurveySpecification implements Specification<Survey> {

    private Survey survey;
    private Long providerId;


    public SurveySpecification(Survey survey, Long providerId){
        this.survey = survey;
        this.providerId = providerId;
    }

    @Override
    public Predicate toPredicate
            (Root<Survey> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();


        predicates.add(builder.equal(root.get("provider").get("id"), providerId));

        if(!survey.getTargetGender().equals(Survey.TargetGender.ALL))
            predicates.add(builder.equal(root.get("targetGender"), survey.getTargetGender()));

        if(survey.getTargetAgeL() != null)
        predicates.add(builder.greaterThanOrEqualTo(root.get("targetAgeL"), survey.getTargetAgeL()));

        if(survey.getTargetAgeH() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("targetAgeH"), survey.getTargetAgeH()));

        if(!survey.getTargetIncomeCurrency().equals(Survey.Currency.ALL))
            predicates.add(builder.equal(root.get("targetIncomeCurrency"), survey.getTargetIncomeCurrency()));

        if(survey.getTargetIncomeL() != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("targetIncomeL"), survey.getTargetIncomeL()));

        if(survey.getTargetIncomeH() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("targetIncomeH"), survey.getTargetIncomeH()));


        return builder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
