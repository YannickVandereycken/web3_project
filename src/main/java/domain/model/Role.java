package domain.model;

public enum Role {
    DIRECTOR("Director"), TEAMLEADER("Teamleader"), EMPLOYEE("Employee");

    private String stringValue;

    private Role(String stringValue){
        this.stringValue=stringValue;
    }

    public String getStringValue() {return stringValue;}
}
