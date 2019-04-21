package com.weather.Temperature;


import lombok.Data;

@Data
public abstract class Temperature {

    public int value;
    public String type;
    public int unitType;

    public Temperature(int value, String type, int unitType) {
        this.value = value;
        this.type = type;
        this.unitType = unitType;
    }

}
