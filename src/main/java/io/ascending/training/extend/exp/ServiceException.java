/**
 * 
 */
package io.ascending.training.extend.exp;


/**
 *
 * @author GuoqingHuang
 * 
 * @since
 * 
 */
public class ServiceException extends Exception {

   /**
    * 
    */
   private static final long serialVersionUID = -6897595479404403230L;
   
   
   public ServiceException(String msg) {
      super(msg);
   }
   
   public ServiceException(String msg, Throwable cause) {
      super(msg, cause);
   }
}
