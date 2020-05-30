package ru.khalitov.numberGenerator.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by almaz-h on 11.04.2020.
 */
public class RandomAndNextLogicTest {

    private String myTestLine = "А993АА 116 RUS";
    private String randomNumber;
    private String nextNumber;
    RandomAndNextLogic randomNextLogic = new RandomAndNextLogic();

    @Test
    public void getRandomNumberLength() {
        String line = "A111AA 116 RUS";
        randomNumber = randomNextLogic.createRandomNumber();
        Assert.assertEquals(randomNumber.length(), line.length());
    }

    @Test
    public void createRandomNumber() {
        randomNumber = randomNextLogic.createRandomNumber();
        System.out.println(randomNumber);
    }

    @Test
    public void createNextNumber() {
        nextNumber = randomNextLogic.createNextNumber(myTestLine);
        System.out.println(nextNumber);
    }

    @Test
    public void createRandomAndNextNumber() {
        randomNumber = randomNextLogic.createRandomNumber();
        System.out.println(randomNumber);
        nextNumber = randomNextLogic.createNextNumber(randomNumber);
        System.out.println(nextNumber);
    }
}