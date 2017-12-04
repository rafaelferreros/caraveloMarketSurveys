//@RequestMapping("/{providerId}/surveys")
package caravelo.controllers;

import caravelo.entities.Survey;
import caravelo.repositories.SurveyRepository;
import caravelo.repositories.SurveySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
    @RequestMapping(value = "/api/survey", produces = "application/hal+json")
@ExposesResourceFor(Survey.class)
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/provider/{providerId}")
    public Resources<Resource<Survey>> getSurveyByProviderId(@PathVariable Long providerId) {

        Link selfLink = linkTo(methodOn(SurveyController.class).getSurveyByProviderId(providerId)).withSelfRel();

        return surveyToResource(surveyRepository.findByProviderId(providerId), selfLink);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/provider/{providerId}/request")
    public Resources<Resource<Survey>>
    requestSurveyInfo(@PathVariable Long providerId,
                      @RequestParam(value="targetGender", defaultValue = "ALL") Survey.TargetGender targetGender,
                      @RequestParam(value="targetAgeL", required = false) Integer targetAgeL,
                      @RequestParam(value="targetAgeH", required = false) Integer targetAgeH,
                      @RequestParam(value="targetIncomeCurrency", defaultValue = "ALL") Survey.Currency targetIncomeCurrency,
                      @RequestParam(value="targetIncomeL", required = false) Integer targetIncomeL,
                      @RequestParam(value="targetIncomeH", required = false) Integer targetIncomeH
                      ) {

        Link selfLink = linkTo(methodOn(SurveyController.class)
                .requestSurveyInfo( providerId,
                                    targetGender,
                                    targetAgeL,
                                    targetAgeH,
                                    targetIncomeCurrency,
                                    targetIncomeL,
                                    targetIncomeH
                                    )).withSelfRel();

        Survey querySurvey = new Survey(null, null);
        querySurvey.setTargetGender(targetGender);
        querySurvey.setTargetAgeL(targetAgeL);
        querySurvey.setTargetAgeH(targetAgeH);

        querySurvey.setTargetIncomeCurrency(targetIncomeCurrency);
        querySurvey.setTargetIncomeL(targetIncomeL);
        querySurvey.setTargetIncomeH(targetIncomeH);

        SurveySpecification surveySpecification = new SurveySpecification(querySurvey, providerId);

        List<Survey> values = surveyRepository.findAll(surveySpecification);

        return surveyToResource(values, selfLink);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Survey>> getAllSurveyByProviderId() {

        Link selfLink = linkTo(methodOn(SurveyController.class).getAllSurveyByProviderId()).withSelfRel();

        return surveyToResource(surveyRepository.findAll(), selfLink);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Survey> getSurveyById(@PathVariable Long id) {

        Survey survey = surveyRepository.findOne(id);
        return surveyToResource(survey);

    }

    private Resources<Resource<Survey>> surveyToResource(Collection<Survey> surveys, Link selfLink) {

        List<Resource<Survey>> surveyResources = surveys.stream().map(SurveyController::surveyToResource).collect(Collectors.toList());

        return new Resources<>(surveyResources, selfLink);

    }

    private static Resource<Survey> surveyToResource(Survey survey) {
        Link selfLink = linkTo(methodOn(SurveyController.class).getSurveyById(survey.getId())).withSelfRel();

        return new Resource<>(survey, selfLink);
    }

}