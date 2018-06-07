package io.ascending.training.domain;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by ryo on 5/20/18.
 */
@Entity
@Table(name="images")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="images_id_seq")
    @SequenceGenerator(name="images_id_seq", sequenceName="images_id_seq", allocationSize=1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
