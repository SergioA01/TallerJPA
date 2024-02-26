package Taller.JPASupermercado.services;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import Taller.JPASupermercado.entities.Sale;
import Taller.JPASupermercado.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){

        return customerRepository.findAll();
    }

    public Customer findById(Integer id ){
        Optional<Customer> optional = customerRepository.findById(id);

        return optional.isPresent() ? optional.get() : null;
    }

    public Customer save(Customer customer){

        return customerRepository.save(customer);
    }


    public List<Sale> getSales(Customer customer){
        return customer.getSales();
    }

    public void delete( Customer customer){
        customerRepository.delete(customer);
    }

    public Customer updateCustomerById(Integer id, Customer newCustomer){
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()){
            Customer oldCustomer = optional.get();
            oldCustomer.setPlaceResidence(newCustomer.getPlaceResidence());
            oldCustomer.setAddress(newCustomer.getAddress());
            oldCustomer.setEmail(newCustomer.getEmail());
            oldCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
            return customerRepository.save(oldCustomer);
        }
        return null;
    }

    public List<Customer> findByPlaceResidence( String place) {

        return customerRepository.findByPlaceResidence(place);
    }

}

