package ru.khalitov.numberGenerator.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by almaz-h on 11.04.2020.
 */
public class RandomNextLogicTest {


    RandomNextLogic randomNextLogic = new RandomNextLogic();

    @Test
    public void getRandomNumberLength() {
        String line = "A111AA 116 RUS";
        String s = randomNextLogic.getMyNumberRandom();
        Assert.assertEquals(s.length(), line.length());
    }
}