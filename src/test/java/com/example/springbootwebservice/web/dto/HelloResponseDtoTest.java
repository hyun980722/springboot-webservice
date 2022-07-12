package com.example.springbootwebservice.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트() {
        //given
        String name = "test";
        int amount = 10000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /*
            1. assertThat
                 assertj라는 테스트 검증 라이브러리의 검증 메소드
                 검증하고 싶은 대상의 메소드 인자로 받는다.
                 메소드 체이닝이 지원되어 isEqualTo와 같이메소드를 이어서 사용할 수 있다.
            2. isEqualTo
                 assertj의 동등 비교 메소드
                 assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공
         */
    }
}
