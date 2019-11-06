package com.byinal.rockpaperscissors.domain.model.converter;

import com.byinal.rockpaperscissors.domain.model.rule.Move;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MoveConverter implements Converter<String, Move> {

    @Override
    public Move convert(String type) {
        return Move.valueOf(type);
    }
}
