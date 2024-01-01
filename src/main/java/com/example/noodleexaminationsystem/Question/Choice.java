package com.example.noodleexaminationsystem.Question;

public enum Choice {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);

    private int value;

    Choice(int i) {
        this.value = i;
    }

    public int getValue(){
        return value;
    }

    public static Choice getByValue(int value) {
        for (Choice number : Choice.values()) {
            if (number.getValue() == value) {
                return number;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
