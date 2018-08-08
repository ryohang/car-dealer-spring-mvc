package io.ascending.training.blueprint;

import com.tobedevoured.modelcitizen.annotation.Blueprint;
import com.tobedevoured.modelcitizen.annotation.Default;
import io.ascending.training.domain.Car;

/**
 * Created By.
 * User: hanqinghang
 */
@Blueprint(Car.class)
public class CarBlueprint {
    @Default
    private String brand="Toyota";
    @Default
    private String model="XLE";
}
