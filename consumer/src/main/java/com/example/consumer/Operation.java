package com.example.consumer;

public enum Operation {
    Create("c"), Update("u"), Delete("d");

    private final String value;

    Operation(String op) {
        value=op;
    }

    public static Operation fromString(String s){
        for (Operation op:Operation.values()){
            if (op.value.equals(s)) return op;
        }
        throw new IllegalArgumentException("s is not match any enum value !");
    }

    @Override
    public String toString() {
        return value;
    }
}
