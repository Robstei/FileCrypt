package edu.junit5.quickstart.model;

public class OperationResult {
    private boolean success;
    private String message;

    public OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public OperationResult(boolean success) {
        this(success, "");
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
