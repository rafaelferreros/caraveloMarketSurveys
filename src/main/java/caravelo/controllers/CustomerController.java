package caravelo.controllers;

import caravelo.entities.Customer;
import caravelo.entities.Subscription;
import caravelo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customer", produces = "application/hal+json")
@ExposesResourceFor(Customer.class)
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Customer>> getCustomers() {

        return customerToResource(customerRepository.findAll());

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Customer> getCustomer(@PathVariable Long id) {

        return customerToResource(customerRepository.findOne(id));
    }

    private Resources<Resource<Customer>> customerToResource(List<Customer> customers) {

        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel();

        List<Resource<Customer>> customerResources = customers.stream().map(customer -> customerToResource(customer)).collect(Collectors.toList());

        return new Resources<>(customerResources, selfLink);

    }

    private Resource<Customer> customerToResource(Customer customer) {
        Link selfLink   = linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel();

        Link allSubscriptionLink = entityLinks.linkToCollectionResource(Subscription.class).withRel("all-subscriptions");
        Link subscriptionLink = linkTo(methodOn(SubscriptionController.class).getSubscriptionByCustomerId(customer.getId())).withRel("subscription");

        return new Resource<>(customer, selfLink,  subscriptionLink, allSubscriptionLink);

    }
}