package io.ascending.training.domain;

/**
 * Created By.
 * User: hanqinghang
 */
public class JsView {
    //new jsonview category
    public static class Anonymous {}
    public static class Other extends Anonymous{}
    public static class User extends Other{}
    public static class Admin extends User {}
}
