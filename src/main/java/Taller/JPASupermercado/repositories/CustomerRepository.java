package Taller.JPASupermercado.repositories;

import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public List<Customer> findByPlaceResidence(String placeResidence);

}
