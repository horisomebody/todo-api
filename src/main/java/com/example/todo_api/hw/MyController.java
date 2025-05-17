package com.example.todo_api.hw;

import com.example.todo_api.bean.MySubBean;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;
    public void print() {
        System.out.println("controller");
        myService.print();
    }


}
