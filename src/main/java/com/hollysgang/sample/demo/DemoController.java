package com.hollysgang.sample.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @GetMapping("/statistic")
    public String statistic(){
        return "통계 정보";
    }

    @GetMapping("/session")
    public String sessionManagement(){
        return "세션 조회";
    }

    @PutMapping("/session")
    public String sessionManagementUpdate(){
        return "세션 업데이트";
    }

    @GetMapping("/user")
    public String user(){
        return "사용자 조회";
    }

    @PostMapping("/user")
    public String userCreate(){
        return "사용자 생성";
    }

    @PutMapping("/user")
    public String userUpdate(){
        return "사용자 수정";
    }

    @DeleteMapping("/user")
    public String userDelete(){
        return "사용자 삭제";
    }

    @GetMapping("/work")
    public String works(){
        return "업무 목록";
    }

    @PostMapping("/work")
    public String workCreate(){
        return "업무 생성";
    }

    @DeleteMapping("/work")
    public String workDelete(){
        return "업무 삭제";
    }

    @PutMapping("/work")
    public String workUpdate(){
        return "업무 수정";
    }

    @GetMapping("/hr")
    public String hr(){
        return "인사 목록";
    }

    @PutMapping("/hr")
    public String hrUpdate(){
        return "인사 수정";
    }

    @GetMapping("/secret")
    public String secret(){
        return "기밀 목록";
    }

    @PostMapping("/secret")
    public String secretCreate(){
        return "기밀 생성";
    }
}
