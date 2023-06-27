package com.common.server.utils;

public class SortHelp implements Comparable<SortHelp> {
    private Long key;
    private Object value;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SortHelp() {
    }

    public SortHelp(Long key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public int compareTo(SortHelp o) {
        return (int) (this.key-o.getKey());
    }
}
