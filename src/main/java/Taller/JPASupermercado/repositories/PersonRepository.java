package Taller.JPASupermercado.repositories;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    @Query("SELECT a FROM Person a WHERE a.firstName LIKE CONCAT('%',:name,'%')")
    public List<Person> findByName(String name);

    public List<Person> findByPlaceOfBirth(String placeOfBirth);

}
