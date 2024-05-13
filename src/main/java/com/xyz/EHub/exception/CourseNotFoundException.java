package com.xyz.EHub.exception;

public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String courseIdIsNotPresent) {
        super(courseIdIsNotPresent);
    }
}
