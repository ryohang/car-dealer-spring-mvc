package io.ascending.training.enumdef;

/**
 * Created By. User: xshen
 */
public enum EmailCategory {

    NoCategory(0),
    RegistrationEmail(1);


    private int value;

    private EmailCategory(int value) {
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
