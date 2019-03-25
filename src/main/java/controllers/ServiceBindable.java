package controllers;

public interface ServiceBindable<T> {
    void setService(T service);
}
