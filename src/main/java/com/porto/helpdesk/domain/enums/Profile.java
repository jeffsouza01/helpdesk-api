package com.porto.helpdesk.domain.enums;

public enum Profile {
    ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), TECHNICIAN(2, "ROLE_TECH");

    private Integer cod;
    private String description;


    Profile(Integer cod, String description) {
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

    public static Profile toEnum(Integer cod){
        if(cod == null) {
            return null;
        }
        for (Profile x : Profile.values()){
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Profile!");
    }

}
