package com.example.jamie.sentwinel;


public class Polarity {

    private float positive;
    private float negative;
    private float neutral;

    public Polarity (float positive, float negative, float neutral){
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
    }

    public float getPositive() {
        return positive;
    }

    public float getNegative() {
        return negative;
    }

    public float getNeutral() {
        return neutral;
    }



}


