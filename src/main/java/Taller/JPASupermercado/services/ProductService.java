package Taller.JPASupermercado.services;


import Taller.JPASupermercado.entities.Product;
import Taller.JPASupermercado.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save( Product product ){

        return productRepository.save(product);
    }

    public Product findById(Integer id ){
        Optional<Product> optional = productRepository.findById( id );

        return optional.isPresent() ? optional.get() : null;
    }
    public Product updateById(Integer id, Product newProduct){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()){
            Product oldProduct = optional.get();
            oldProduct.setName(newProduct.getName());
            oldProduct.setPrice(newProduct.getPrice());
            oldProduct.setStock(newProduct.getStock());
            return productRepository.save(oldProduct);
        }
        return null;
    }

    public void delete( Product product ){

        productRepository.delete( product );

    }
}
