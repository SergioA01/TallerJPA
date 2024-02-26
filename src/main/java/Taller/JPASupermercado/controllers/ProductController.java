package Taller.JPASupermercado.controllers;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Person;
import Taller.JPASupermercado.entities.Product;
import Taller.JPASupermercado.repositories.ProductRepository;
import Taller.JPASupermercado.responses.ResponseHandler;
import Taller.JPASupermercado.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Product> result = productService.findAll();

            if (result != null) {
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            } else {
                return ResponseHandler.generateResponse("No products found", HttpStatus.NOT_FOUND, null);
            }
        } catch(Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Product product){
        try{
            Product result = productService.save(product);

            return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result);

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducto(@PathVariable Integer id, @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateById(id,product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Product product = productService.findById( id );
            if( product != null ){

                productService.delete( product );

                return ResponseHandler.generateResponse("Succes", HttpStatus.ACCEPTED, product );
            }

            return ResponseHandler.generateResponse("Success Product",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Product product = productService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, product);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

}
