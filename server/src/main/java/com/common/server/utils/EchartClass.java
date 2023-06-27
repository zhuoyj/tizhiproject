package com.common.server.utils;

public class EchartClass {
    private Integer value;
    private String name;

    public EchartClass() {
    }

    public EchartClass(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
