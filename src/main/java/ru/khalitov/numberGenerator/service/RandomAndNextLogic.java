package ru.khalitov.numberGenerator.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by almaz-h on 11.04.2020.
 */
@Component
public class RandomAndNextLogic {
    /**
     * @param REGION, константа, добавляется к случайному номеру
     * @param numberObject, лист , сохраняем сгенерированный номер
     * @param myNumberRandom, это случайный номер выводимый на клиента в контроллере, с регионом
     * @param myNumberNext, это следующий номер с регионом
     * @param numberSymbol, возможные символы в номере
     */
    private final String REGION = " 116 RUS";
    private final String numberSymbol = "AETOPHYKXCBM";
    private List<String> numberObjects = new ArrayList<>();
    private String myNumberRandom = createRandomNumber();
    private String myNumberNext = createNextNumber(numberObjects.get(numberObjects.size() - 1));

    public List<String> getNumberObjects() {
        return numberObjects;
    }

    public String getMyNumberRandom() {
        return myNumberRandom;
    }

    public String getMyNumberNext() {
        return myNumberNext;
    }

    public RandomAndNextLogic() {
    }

    /**
     * метод создает и возвращает клиенту случайно сгенерированный номер состаящий из 6 символов,
     * 2,3,4 символ - это цифра, 1,5,6 - это буква, к каждому номеру добавляется регион
     *
     * @return строка с готовым автомобильным номером для представления клиенту
     */
    public String createRandomNumber() {
        String resultMyNumber;
        String randomCharResult;
        char[] myNumb = new char[6];
        int rand;
        char randLine;
        for (int i = 0; i < myNumb.length; i++) {
            /*rand - это случайное число от 1 до 9ти в каждом цикле, сохраняем только во 2ую,3ью и 4ую ячейку,
            согласно стандартну присвоения автомобильным номерам
            */
            rand = (int) (Math.random() * 9) + 1;
            /*randLine - это случайный символ из строки numberSymbol, элементов 12,
            выбираем случайный в каждом цикле сохраняем только во 1ую,5ую и 6ую ячейку,
            согласно стандартну присвоения автомобильным номерам
            */
            randLine = numberSymbol.charAt((int) (Math.random() * 11) + 1);
            switch (i) {
                case 0:
                case 4:
                case 5:
                    myNumb[i] = randLine;
                    break;
                case 1:
                case 2:
                case 3:
                    myNumb[i] = (char) (48 + rand);
                    break;
            }
        }
        /*конвертируем наш массив "myNumb", в котором сгенерированный случайно номер,
         в строку "randomCharResult"
         */
        randomCharResult = String.valueOf(myNumb);
        resultMyNumber = randomCharResult + REGION;
        /*повторяющихся номеров не должно быть, для этого каждый сгенерированный клиентом номер,
        сохраняется в массив "numberObject", проверяем , есть ли такой номер, если да,
        запускаем генерацию заного, если нет сохраняем
         */
        if (!numberObjects.isEmpty()) {
            for (String numberObject : numberObjects) {
                if (numberObject.equals(resultMyNumber)) {
                    createRandomNumber();
                }
            }
        }
        numberObjects.add(resultMyNumber);
        /* возвращаем клиенту готовый номер с регионом
         */
        return resultMyNumber;
    }

    /**
     * метод генерации следующего за случайным номером по принципу , если клиенту сгенерирован номер
     * Х287ЕМ, то следующий Х288ЕМ
     *
     * @param myNumberRandom передаем в метод случайный номер, для преобразования
     * @return возвращаем строку, следующий номер
     */
    public String createNextNumber(String myNumberRandom) {
        String resultFollowForRanNumRegion;
        char[] numberRandomArray = myNumberRandom.toCharArray();
        int elem2;
        int elem3;
        int elem4;
        for (int i = 0; true; ) {
            elem2 = numberRandomArray[1] - 48;
            elem3 = numberRandomArray[2] - 48;
            elem4 = numberRandomArray[3] - 48;
            /* elem4 - это четвертый элемент в нашем массиве, если он меньше 9, можно уеличить на единицу,
            и сохранить новое число как элемент массива
            */
            if (elem4 < 9) {
                ++elem4;
                numberRandomArray[3] = (char) (48 + elem4);
                break;
                /* если elem4 равен 9, и одновременно предыдущий элемент массива
                 elem3 не равен 9, присваиваем четвертому элементу 0, а elem3
            увеличиваем на единицу и так далее
            */
            } else if (elem4 == 9 & elem3 < 9) {
                elem4 = 0;
                numberRandomArray[3] = (char) (48 + elem4);
                ++elem3;
                numberRandomArray[2] = (char) (48 + elem3);
                break;
            } else if (elem4 == 9 & elem3 == 9) {
                elem4 = 0;
                numberRandomArray[3] = (char) (48 + elem4);
                elem3 = 0;
                numberRandomArray[2] = (char) (48 + elem3);
                if (elem2 < 9) {
                    ++elem2;
                } else {
                    elem2 = 0;
                }
                numberRandomArray[1] = (char) (48 + elem2);
                break;
            }
        }
        /* проверяем нашу коллецию, если значение уже присутствует, значит кончились возможные вариации генерации
         последовательных чисел, вызываем метод рандомной генерации чисел
         */
        resultFollowForRanNumRegion = String.valueOf(numberRandomArray);
        if (!numberObjects.isEmpty()) {
            for (String numberObject : numberObjects) {
                if (numberObject.equals(resultFollowForRanNumRegion)) {
                    createRandomNumber();
                }
            }
        }
        numberObjects.add(resultFollowForRanNumRegion);
        return resultFollowForRanNumRegion;
    }
}