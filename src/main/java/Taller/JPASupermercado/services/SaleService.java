package Taller.JPASupermercado.services;


import Taller.JPASupermercado.entities.Customer;
import Taller.JPASupermercado.entities.Product;
import Taller.JPASupermercado.entities.Sale;
import Taller.JPASupermercado.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductService productService;

    public List<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Sale save(Sale sale, Customer customer, List<Integer> idProducts ){
        sale.setCustomer( customer );
        List<Product> productsSale = new ArrayList<Product>();

        for(Integer id:idProducts){
            Product product = productService.findById(id);
            if(product != null && product.getStock()>0){
                productsSale.add(product);
                product.setStock(product.getStock()-1);
                productService.updateById(product.getId(), product);
            }
        }

        sale.setProducts( productsSale );
        return saleRepository.save( sale );
    }

    public Sale findById(Integer id ){
        Optional<Sale> optional = saleRepository.findById( id );

        return optional.isPresent() ? optional.get() : null;
    }

    public void delete( Sale sale ){

        saleRepository.delete( sale );

    }

}
