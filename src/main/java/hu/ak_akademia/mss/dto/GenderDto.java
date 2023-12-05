package hu.ak_akademia.mss.dto;

import java.util.StringJoiner;

public record GenderDto(int genderId, String gender) {

    @Override
    public String toString() {
        return new StringJoiner(", ", GenderDto.class.getSimpleName() + "[", "]")
                .add("genderId=" + genderId)
                .add("gender='" + gender + "'")
                .toString();
    }
}
