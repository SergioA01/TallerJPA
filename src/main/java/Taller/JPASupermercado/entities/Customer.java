package Taller.JPASupermercado.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String placeResidence;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 20)
    private Long phoneNumber;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Sale> sales;

    public Customer() {
        sales = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceResidence() {
        return placeResidence;
    }

    public void setPlaceResidence(String placeResidence) {
        this.placeResidence = placeResidence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales){
        this.sales = sales;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
