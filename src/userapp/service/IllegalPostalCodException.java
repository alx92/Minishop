package userapp.service;

public class IllegalPostalCodException extends Exception
{
    public IllegalPostalCodException(){
        super("Illegal postal code!");
    }
}
