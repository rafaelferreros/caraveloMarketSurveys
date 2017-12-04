package caravelo.repositories;

import caravelo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepostory extends JpaRepository<Customer, Long> {
}
