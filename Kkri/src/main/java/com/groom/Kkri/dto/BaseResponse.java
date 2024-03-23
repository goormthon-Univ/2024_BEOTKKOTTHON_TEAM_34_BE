package com.groom.Kkri.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL) //data 는 null이면 노출 x
@FieldDefaults(level = AccessLevel.PRIVATE) //private 을 다 붙여줌
@AllArgsConstructor(access = AccessLevel.PUBLIC) //모든 변숫값을 가진 생성자에 private
@Data
public class BaseResponse<T> {
    HttpStatus status;
    String message;
    T result;

    public static <T> BaseResponse<T> response(T result){
        if (result == null) {
            return new BaseResponse<>(HttpStatus.FORBIDDEN,"fail",result);
        }else{
            return new BaseResponse<>(HttpStatus.OK,"success",result);
        }
    }

}
