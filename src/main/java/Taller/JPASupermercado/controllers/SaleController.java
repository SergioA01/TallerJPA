package Taller.JPASupermercado.controllers;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Product;
import Taller.JPASupermercado.entities.Sale;
import Taller.JPASupermercado.responses.ResponseHandler;
import Taller.JPASupermercado.services.CustomerService;
import Taller.JPASupermercado.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Sale> result = saleService.findAll();

            return ResponseHandler.generateResponse("Success",HttpStatus.OK, result );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody SaleCustomer saleCustomer){
        try{
            Customer customer = customerService.findById( saleCustomer.idCustomer );
            System.out.print("customer: " + customer);
            if( customer != null ){

                Sale result = saleService.save(saleCustomer.sale, customer, saleCustomer.idProducts);

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);
            }
            System.out.print("cliente no encontrado");
            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){
            System.out.print("error en el servidor");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Sale sale = saleService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, sale);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Sale sale = saleService.findById( id );
            if( sale != null ){

                sale.getProducts().size();

                saleService.delete( sale );

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, sale );
            }

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    public static class SaleCustomer {
        private Sale sale;
        private List<Integer> idProducts;
        private Integer idCustomer;

        public Sale getSale() {
            return sale;
        }

        public void setSale(Sale sale) {
            this.sale = sale;
        }

        public List<Integer> getIdProducts() {
            return idProducts;
        }

        public void setIdProducts(List<Integer> idProducts) {
            this.idProducts = idProducts;
        }

        public Integer getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(Integer idCustomer) {
            this.idCustomer = idCustomer;
        }
    }
}


