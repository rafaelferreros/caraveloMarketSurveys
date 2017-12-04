package caravelo.controllers;

import caravelo.entities.Provider;
import caravelo.entities.Survey;
import caravelo.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/provider", produces = "application/hal+json")
@ExposesResourceFor(Provider.class)
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Provider>> getProviders() {

        return providerToResource(providerRepository.findAll());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Provider> getProvider(@PathVariable Long id) {

        return providerToResource(providerRepository.findOne(id));
    }

    private Resources<Resource<Provider>> providerToResource(List<Provider> providers) {

        Link selfLink = linkTo(methodOn(ProviderController.class).getProviders()).withSelfRel();

        List<Resource<Provider>> providerResources = providers.stream().map(provider -> providerToResource(provider)).collect(Collectors.toList());

        return new Resources<>(providerResources, selfLink);

    }

    private Resource<Provider> providerToResource(Provider provider) {
        Link selfLink   = linkTo(methodOn(ProviderController.class).getProvider(provider.getId())).withSelfRel();

        Link allSurveyLink = entityLinks.linkToCollectionResource(Survey.class).withRel("all-surveys");
        Link surveyLink = linkTo(methodOn(SurveyController.class).getSurveyByProviderId(provider.getId())).withRel("survey");

        return new Resource<>(provider, selfLink,  surveyLink, allSurveyLink);

    }
}
