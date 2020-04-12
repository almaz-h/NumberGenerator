package ru.khalitov.numberGenerator.service;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by almaz-h on 11.04.2020.
 */
@Component
public class RandomNextLogic {
    /** @param region, константа, добавляется к случайному номеру
     * @param randomNumberObject, лист , сохраняем сгенерированный номер
     * @param myNumberRandom, это случайный номер выводимый на клиента в контроллере, с регионом
     */
    private final String REGION = " 116 RUS";
    List<String> randomNumberObject = new ArrayList<>();
    String myNumberRandom = getRandomNumber();

    public String getMyNumberRandom() {
        return myNumberRandom;
    }

    public RandomNextLogic() {
    }
    /**метод создает и возвращает клиенту случайно сгенерированный номер состаящий из 6 символов,
     * 2,3,4 символ - это цифра, 1,5,6 - это буква, к каждому номеру добавляется регион
     * @return строка с готовым автомобильным номером для представления клиенту
     */
    public String getRandomNumber() {
        String resultMyNumber;
        String randomCharResult;
        String numberSymbol = "AETOPHYKXCBM";
        char[] myNumb = new char[6];
        int rand;
        char randLine;
        Random random = new Random();
        for (int i = 0; i < myNumb.length; i++) {
            /*rand - это случайное число от 1 до 9ти в каждом цикле, сохраняем только во 2ую,3ью и 4ую ячейку,
            согласно стандартну присвоения автомобильным номерам
            */
            rand = random.nextInt(9) + 1;
            /*randLine - это случайный символ из строки numberSymbol, элементов 12,
            выбираем случайный в каждом цикле сохраняем только во 1ую,5ую и 6ую ячейку,
            согласно стандартну присвоения автомобильным номерам
            */
            randLine = numberSymbol.charAt(random.nextInt(11)+1);
            switch (i){
                case 0:
                case 4:
                case 5:
                    myNumb[i] = randLine;
                    break;
                case 1:
                case 2:
                case 3:
                    myNumb[i] = (char)(48+rand);
                    break;
            }
        }
        /*конвертируем наш массив "myNumb", в котором сгенерированный случайно номер,
         в строку "randomCharResult"
         */
        randomCharResult = String.valueOf(myNumb);
        /*повторяющихся номеров не должно быть, для этого каждый сгенерированный клиентом номер,
        сохраняется в массив "randomNumberObject", проверяем , есть ли такой номер, если да,
        запускаем генерацию заного, если нет сохраняем
         */
        if (!randomNumberObject.isEmpty()) {
            for (int j = 0; j < randomNumberObject.size(); j++) {
                if (randomNumberObject.get(j).equals(randomCharResult)) {
                    getRandomNumber();
                }
            }
            randomNumberObject.add(randomCharResult);
        }else randomNumberObject.add(randomCharResult);
        resultMyNumber = randomCharResult+REGION;
        /* возвращаем клиенту готовый номер с регионом
         */
        return resultMyNumber;
    }

}