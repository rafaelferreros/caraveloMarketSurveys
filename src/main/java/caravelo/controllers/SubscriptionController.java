package caravelo.controllers;

import caravelo.entities.Customer;
import caravelo.entities.Subscription;
import caravelo.repositories.CustomerRepository;
import caravelo.repositories.ProviderRepository;
import caravelo.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/subscription", produces = "application/hal+json")
@ExposesResourceFor(Subscription.class)
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProviderRepository providerRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
    public Resources<Resource<Subscription>> getSubscriptionByCustomerId(@PathVariable Long customerId) {

        Link selfLink = linkTo(methodOn(SubscriptionController.class).getSubscriptionByCustomerId(customerId)).withSelfRel();

        return subscriptionToResource(subscriptionRepository.findByCustomerId(customerId), selfLink);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer/{customerId}")
    public Resource<Subscription> addSubscription(@PathVariable Long customerId, @RequestBody Subscription subscription) {

       Customer customer = customerRepository.getOne(customerId);

       if(customer != null)
       {
           subscription.setCustomer(customer);
           subscriptionRepository.save(subscription);

           return subscriptionToResource(subscription);
       }
       else{
           throw new RuntimeException("Customer not found");
       }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Subscription>> getAllSubscriptionByCustomerId() {

        Link selfLink = linkTo(methodOn(SubscriptionController.class).getAllSubscriptionByCustomerId()).withSelfRel();

        return subscriptionToResource(subscriptionRepository.findAll(), selfLink);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Subscription> getSubscriptionById(@PathVariable Long id) {

        Subscription subscription = subscriptionRepository.findOne(id);
          return subscriptionToResource(subscription);

    }

    private Resources<Resource<Subscription>> subscriptionToResource(Collection<Subscription> subscriptions, Link selfLink) {

        List<Resource<Subscription>> subscriptionResources = subscriptions.stream().map(SubscriptionController::subscriptionToResource).collect(Collectors.toList());

        return new Resources<>(subscriptionResources, selfLink);

    }

    private static Resource<Subscription> subscriptionToResource(Subscription subscription) {
        Link selfLink = linkTo(methodOn(SubscriptionController.class).getSubscriptionById(subscription.getId())).withSelfRel();

        return new Resource<>(subscription, selfLink);
    }

}