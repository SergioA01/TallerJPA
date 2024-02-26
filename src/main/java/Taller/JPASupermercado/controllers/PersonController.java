package Taller.JPASupermercado.controllers;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import Taller.JPASupermercado.entities.Sale;
import Taller.JPASupermercado.responses.ResponseHandler;
import Taller.JPASupermercado.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Long id ){
        try {
            Person person = personService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,person );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }


    @GetMapping("/name/{firstName}")
    public ResponseEntity<Object> findByName(@PathVariable  String firstName){
        try {
            List<Person> result = personService.findByName( firstName );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }



    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id ){
        try{
            Person person = personService.getPersonById( id );
            if( person != null ){

                personService.delete( person );

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, person );
            }

            return ResponseHandler.generateResponse("Success Person",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        try {
            if( person != null ){
            Person updatedPerson = personService.updatePersonById(id, person);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/placeOfBirth/{placeOfBirth}")
    public ResponseEntity<Object> findByPlaceOfBirth(@PathVariable  String placeOfBirth){
        try {
            List<Person> result = personService.findByPlaceOfBirth( placeOfBirth );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}