package io.ascending.training.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by ryo on 5/20/18.
 */
@Entity
@Table(name="cars")
public class Car implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="cars_id_seq")
    @SequenceGenerator(name="cars_id_seq", sequenceName="cars_id_seq", allocationSize=1)
    private Long id;

    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    @JsonView({JsView.User.class})
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car",cascade = CascadeType.ALL)
    @JsonView({JsView.Admin.class})
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
}
