package caravelo.repositories;

import caravelo.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Collection<Subscription> findByCustomerId(Long customerId);
}
