package com.alperen.project.model.exception.notfound;


public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("notfound.user");
    }
}
