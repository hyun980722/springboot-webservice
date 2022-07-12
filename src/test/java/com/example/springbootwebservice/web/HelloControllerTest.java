package com.example.springbootwebservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.springbootwebservice.web.HelloController;

@ExtendWith(SpringExtension.class)
/*
    테스트를 진행 할때 JUnit에 내장된 실행자 외의 다른 실행자를 실행 시킨다.
    여기서는 SpringRunner라는 스프링 실행자를 사용. 즉, 스프링 부트 테스트와 JUnit 사이의 연결자 역할을 한다.
*/
@WebMvcTest(controllers = HelloController.class)
/*
    여러 스프링 테스트 어노테이션 중, Web(spring MVC)에 집중할 수 있는 어노테이션
    선언 할 경우 @Controller, @ControllerAdvice 등을 사용 할 수 있다.
    단 @Service, @Component, @Repository 등은 사용 할 수 없다.
    여기서는 컨트롤러만 사용하기 때문에 선언한다.
*/
public class HelloControllerTest {
    @Autowired  //스프링이 관리하는 빈(Bean)을 주입 받습니다.
    private MockMvc mvc;
    /*
        웹 API를 테스트 할 떄 사용한다.
        스프링 MVC 테스트의 시작점이다.
        이 클래스를 통해 HTTP Get,Post 등에 대한 테스트를 할 수 있다.
     */
    
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        
        mvc.perform(get("/hello"))            //MockMvc를 통해 /hello 주소로 http Get 요청을 한다. 체이닝이 지원되어 여러 검증기능을 이어서 선언 가능
                .andExpect(status().isOk())             //mvc.perform의 결과, Http header의 status를 검증
                .andExpect(content().string(hello));    //mvc.perform의 결과와 응답 본문 내용을 검증, Controller에서 "hello"를 리턴하기에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws  Exception{
        String name = "hello";
        int amount = 10000;

        mvc.perform(get("/hello/dto")
                    .param("name",name)                                 //param : API 테스트 할 때 사용될 요청 파라미터를 설정
                    .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))            //jsonPaht : JSON 응닶갑을 필드별로 검증할 수 있는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));       //         : $를 기준으로 필드명을 명시한다.
    }

}
