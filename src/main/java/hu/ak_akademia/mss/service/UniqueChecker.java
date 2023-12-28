package hu.ak_akademia.mss.service;

import java.util.StringJoiner;

public final class UniqueChecker {

    private static boolean uniqueEmail;

    public static void setUniqueEmail(boolean uniqueEmail) {
        UniqueChecker.uniqueEmail = uniqueEmail;
    }

    public static boolean isUniqueEmail() {
        return uniqueEmail;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UniqueChecker.class.getSimpleName() + "[", "]")
                .add("uniqueEmail=" + uniqueEmail)
                .toString();
    }
}
