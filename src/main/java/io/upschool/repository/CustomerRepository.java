package io.upschool.repository;

import io.upschool.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> getCustomersById(Long id);
    List<Customer> findAllByIdentityNo(Long identityNo);
}
