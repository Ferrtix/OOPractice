package exceptions;

public class InconsistentFunctionsException extends RuntimeException{

    public InconsistentFunctionsException(){}
    public InconsistentFunctionsException(String mes){
        super(mes);
    }
}
