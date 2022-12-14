package domain.model;

public enum Role {
    DIRECTOR("Director"), TEAMLEADER("Teamleader"), EMPLOYEE("Employee");

    private final String stringValue;

    Role(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}