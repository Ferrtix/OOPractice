package exceptions;

public class DifferentLengthOfArraysException extends RuntimeException{

    public DifferentLengthOfArraysException(){}
    public DifferentLengthOfArraysException(String mes){
        super(mes);
    }
}
