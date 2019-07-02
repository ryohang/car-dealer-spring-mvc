package io.ascending.training.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by ryo on 5/20/18.
 */
@Entity
@Table(name="cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="cars_id_seq")
    @SequenceGenerator(name="cars_id_seq", sequenceName="cars_id_seq", allocationSize=1)
    private Long id;

    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;

    @Column(name="created_date")
    private LocalDate createdDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car",cascade = CascadeType.ALL)
    private List<Image> images;

    public Car(){}

    public Car(Long id){
        this.id = id;
    }

    public Car(String brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }


    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Image> getImages() {
        return images==null ? new ArrayList<>() : images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
