package com.byinal.rockpaperscissors.domain.model.converter;

import com.byinal.rockpaperscissors.domain.model.game.GameType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GameTypeConverter implements Converter<String, GameType> {

    @Override
    public GameType convert(String type) {
        return GameType.valueOf(type);
    }
}
