package com.porto.helpdesk.domain.enums;

public enum Status {
    OPEN(0, "OPEN"), InProgress(1, "PROGRESS"), CLOSED(2, "CLOSED");

    private Integer cod;
    private String description;


    Status(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Status toEnum(Integer cod){
        if(cod == null) {
            return null;
        }
        for (Status x : Status.values()){
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Status!");
    }

}
