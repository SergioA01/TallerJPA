package Taller.JPASupermercado.services;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import Taller.JPASupermercado.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public Person findById(Long id ){
        Optional<Person> optional = personRepository.findById(id);

        return optional.isPresent() ? optional.get() : null;
    }

    public List<Person> findByName(String firstName ){

        return personRepository.findByName( firstName );
    }

    public Person createPerson(Person person) {

        return personRepository.save(person);
    }

    public void delete( Person person ){

        personRepository.delete( person );

    }

    public Person updatePersonById(Long id, Person newPerson){
        Optional<Person> optional = personRepository.findById(id);
        if (optional.isPresent()){
            Person oldPerson = optional.get();
            oldPerson.setIdentification(newPerson.getIdentification());
            oldPerson.setFirstName(newPerson.getFirstName());
            oldPerson.setLastName(newPerson.getLastName());
            oldPerson.setDateOfBirth(newPerson.getDateOfBirth());
            oldPerson.setPlaceOfBirth(newPerson.getPlaceOfBirth());
            return personRepository.save(oldPerson);
        }
        return null;
    }

    public List<Person> findByPlaceOfBirth( String place) {

        return personRepository.findByPlaceOfBirth(place);
    }
}
