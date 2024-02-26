package Taller.JPASupermercado.controllers;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import Taller.JPASupermercado.entities.Sale;
import Taller.JPASupermercado.responses.ResponseHandler;
import Taller.JPASupermercado.services.CustomerService;
import Taller.JPASupermercado.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<Customer> result = customerService.findAll();

            return ResponseHandler.generateResponse("Success",HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Customer customer = customerService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, customer);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @Autowired
    private PersonService personService;
    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody Customer customer, @PathVariable Long id ){
        try {
            Person person = personService.getPersonById(id);
            customer.setPerson(person);
            Customer result = customerService.save( customer );

            return  ResponseHandler.generateResponse("Success",HttpStatus.CREATED,result);

        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }


    @GetMapping("/sales/{id}")
    public ResponseEntity<Object> getBooks(@PathVariable Integer id){
        try {
            Customer customer = customerService.findById(id);
            if (customer != null) {
                List<Sale> sales = customerService.getSales(customer);

                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("customer", customer);
                responseMap.put("sales", sales);

                return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseMap);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Customer customer = customerService.findById( id );
            if( customer != null){
                customerService.delete(customer);

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, customer);
            }
            return ResponseHandler.generateResponse("Succes customer",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            if (customer != null) {
                Customer updatedCustomer = customerService.updateCustomerById(id, customer);
                return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }

    @GetMapping("/placeResidence/{placeResidence}")
    public ResponseEntity<Object> findByPlaceResidence(@PathVariable  String placeResidence){
        try {
            List<Customer> result = customerService.findByPlaceResidence( placeResidence );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
