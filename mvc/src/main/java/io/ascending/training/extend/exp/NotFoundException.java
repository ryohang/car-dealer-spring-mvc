package io.ascending.training.extend.exp;

/**
 * Created By. User: hanqinghang
 */
/**
 * Signals that something could not be found.
 */
public class NotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
