package io.ascending.training.enumdef;

/**
 * Created by ryo on 5/16/15.
 */
public enum UserConfirmStatus {

    CREATED(0),
    INVITED(1),
    CONFIRMED(2),
    FOLLOWEDUP(3);

    private int value;

    private UserConfirmStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
