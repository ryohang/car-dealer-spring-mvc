package io.ascending.training.blueprint;

import com.tobedevoured.modelcitizen.annotation.Blueprint;
import com.tobedevoured.modelcitizen.annotation.Default;
import io.ascending.training.domain.Car;
import io.ascending.training.domain.Image;

/**
 * Created By.
 * User: hanqinghang
 */
@Blueprint(Image.class)
public class ImageBlueprint {
    @Default
    private String title="front";
    @Default
    private String url="http://ascending.dc/car.png";
}
