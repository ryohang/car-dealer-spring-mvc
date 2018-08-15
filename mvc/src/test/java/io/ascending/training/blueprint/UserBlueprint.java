package io.ascending.training.blueprint;

import com.tobedevoured.modelcitizen.annotation.Blueprint;
import com.tobedevoured.modelcitizen.annotation.Default;
import com.tobedevoured.modelcitizen.callback.FieldCallback;
import io.ascending.training.domain.User;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

/**
 * Created By.
 * User: hanqinghang
 */
@Blueprint(User.class)
public class UserBlueprint {
    Random generator = new Random( System.currentTimeMillis() );
    @Default
    FieldCallback<String> email = new FieldCallback() {
        @Override
        public String get(Object model) {
           return "ascending+test"+generator.nextInt()+"@gmail.com";
        }

    };
    @Default
    FieldCallback<String> username = new FieldCallback() {
        @Override
        public String get(Object model) {
            return "ryotest"+generator.nextInt();
        }
    };

    @Default
    private Instant createdAt = Instant.now();

    @Default
    private Integer confirmStatus = 1;

    @Default
    private Instant lastLoginAt = Instant.now();

    @Default
    private Instant confirmAt = Instant.now();
}
