package com.example.todo_api.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class MyRepository {
    public void print() {
        System.out.println("repository");
    }
}
