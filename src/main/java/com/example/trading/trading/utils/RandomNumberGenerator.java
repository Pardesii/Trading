package com.example.trading.trading.utils;
import java.util.*;

public class RandomNumberGenerator {
    public static long generate(long min, long max) {
        Long aLong = new Double((Math.random() * (max - min) + min)).longValue();
        return aLong;
    }
}
