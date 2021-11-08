package com.zlq.blog.exception;



/**
 * Create by lanqzhou on 2020.10.28
 */
public class IllegalOperationException extends RuntimeException{

    private static final long serialVersionUID = 7034897190720280946L;
    private String code;

    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperationException(Throwable cause) {
        super(cause);
    }

    public IllegalOperationException(String message, Throwable cause,
                                     boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


/*    public static void main(String[]args){
        try {
            if(1==1){
                throw new IllegalOperationException("111111");
            }
        } catch (IllegalOperationException e) {
            System.out.println(e.getMessage());
        }
    }*/

}
