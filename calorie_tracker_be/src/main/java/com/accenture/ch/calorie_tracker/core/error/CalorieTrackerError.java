package com.accenture.ch.calorie_tracker.core.error;

public class CalorieTrackerError extends RuntimeException{

    public CalorieTrackerError(String message) {
        super(message);
    }

    @Override
    public final String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public final synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public final StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

}
