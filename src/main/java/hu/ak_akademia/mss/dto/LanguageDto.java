package hu.ak_akademia.mss.dto;

import java.util.StringJoiner;

public class LanguageDto {

    private final int languageId;

    private final String language;

    public LanguageDto(int languageId, String language) {
        this.languageId = languageId;
        this.language = language;
    }

    public int getLanguageId() {
        return languageId;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LanguageDto.class.getSimpleName() + "[", "]")
                .add("languageId=" + languageId)
                .add("language='" + language + "'")
                .toString();
    }
}
