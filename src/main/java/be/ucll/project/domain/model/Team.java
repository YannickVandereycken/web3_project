package be.ucll.project.domain.model;

public enum Team {
    ALPHA("Alpha"), BETA("Beta"), GAMMA("Gamma"), DELTA("Delta"), EPSILON("Epsilon");

    private String stringValue;

    private Team(String stringValue){
        this.stringValue=stringValue;
    }

    public String getStringValue() {return stringValue;}
}
