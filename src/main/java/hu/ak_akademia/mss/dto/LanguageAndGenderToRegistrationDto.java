package hu.ak_akademia.mss.dto;

import java.util.List;
import java.util.StringJoiner;

public class LanguageAndGenderToRegistrationDto {

    private final List<GenderDto> genders;

    private final List<LanguageDto> languages;

    public LanguageAndGenderToRegistrationDto(List<GenderDto> genders, List<LanguageDto> languages) {
        this.genders = genders;
        this.languages = languages;
    }

    public List<GenderDto> getGendersDto() {
        return genders;
    }

    public List<LanguageDto> getLanguagesDto() {
        return languages;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LanguageAndGenderToRegistrationDto.class.getSimpleName() + "[", "]")
                .add("genders=" + genders)
                .add("languages=" + languages)
                .toString();
    }
}
