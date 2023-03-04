package com.example.commerce.exception;

public class MemberAuthFailureHandler extends RuntimeException {
    public MemberAuthFailureHandler(String error) {
        super(error);
    }
}
