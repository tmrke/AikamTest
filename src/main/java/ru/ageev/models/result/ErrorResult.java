package ru.ageev.models.result;

import ru.ageev.service.Type;


public class ErrorResult implements Result {
    private final String type = Type.error.name();
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    public ErrorResult(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }
}
