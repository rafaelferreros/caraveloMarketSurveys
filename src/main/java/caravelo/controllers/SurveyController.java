package caravelo.controllers;

import caravelo.entities.Provider;
import caravelo.entities.Survey;
import caravelo.repositories.ProviderRepository;
import caravelo.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/{providerId}/surveys")
public class SurveyController {

    private final ProviderRepository providerRepository;

    private final SurveyRepository surveyRepository;

    @Autowired
    SurveyController(ProviderRepository providerRepository,
                           SurveyRepository surveyRepository) {
        this.providerRepository = providerRepository;
        this.surveyRepository = surveyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Survey> readSurveys(@PathVariable Long providerId) {
        this.validateProvider(providerId);
        return this.surveyRepository.findByProviderId(providerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{surveyId}")
    Survey readSurvey(@PathVariable Long providerId, @PathVariable Long surveyId) {
        this.validateProvider(providerId);
        return this.surveyRepository.findOne(surveyId);
    }

    private void validateProvider(Long providerId) {
        Provider provider = this.providerRepository.findOne(providerId);
        if(provider == null)
            throw new ProviderNotFoundException(providerId.toString());
    }
}