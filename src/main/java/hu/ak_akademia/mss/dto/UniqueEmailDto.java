package hu.ak_akademia.mss.dto;

import java.util.StringJoiner;

public record UniqueEmailDto(String email) {

    @Override
    public String toString() {
        return new StringJoiner(", ", UniqueEmailDto.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .toString();
    }
}
