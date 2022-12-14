package domain.model;

public enum Team {
    ALPHA("Alpha"), BETA("Beta"), GAMMA("Gamma"), DELTA("Delta"), EPSILON("Epsilon");

    private final String stringValue;

    Team(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}