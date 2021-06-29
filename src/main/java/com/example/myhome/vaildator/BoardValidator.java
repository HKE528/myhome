package com.example.myhome.vaildator;

import com.example.myhome.domain.Board;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {

        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Board b = (Board) target;

        if(b.getContent().length() < 1) {
            errors.rejectValue("content", "key", "내용을 입력하세요");
        } else if (b.getContent().length() > 5000) {
            errors.rejectValue("content", "key", "글자수가 너무 많습니다.");
        }
    }
}
