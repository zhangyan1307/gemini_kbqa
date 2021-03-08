package com.gemini.admin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @Author: XXY
 * @Date: 2021/3/1 23:04
 */

@Getter
@Setter
public class WsSessionDto {
    private Long sessionId;

    private String session;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WsSessionDto that = (WsSessionDto)o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, session);
    }
}
