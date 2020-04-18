package ru.khalitov.numberGenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.khalitov.numberGenerator.service.RandomNextLogic;

/**
 * Created by almaz-h on 11.04.2020.
 */
@Controller
public class RandomNextController {

    RandomNextLogic randomNextLogic;

    @Autowired
    public RandomNextController(RandomNextLogic randomNextLogic) {
        this.randomNextLogic = randomNextLogic;
    }

    //адрес http://localhost:8080/number/ стартовая страница
    @GetMapping(path = "/")
    public String getStartPage() {
        return "index";
    }

    /**
     * аргумент randomNumber , в нее сохраняем наш сгенерированный номер в компоненте
     * RandomNextLogic, так же его передаём в представление "random_number" - это наш шаблон,
     * используем шаблонизатор "Thymeleaf" . В случае перехода на главную страницу, и повторном
     * нажатии на ссылку http://localhost:8080/number/random , генерируется новый номер,
     * @return  представление клиенту , случайный номер авто
     */
    @GetMapping(path = "/random")
    public String getRandom(String randomNumber, Model model) {
        randomNumber = randomNextLogic.getMyNumberRandom();
        if (randomNumber != null & randomNumber.equals(randomNextLogic.getMyNumberRandom())) {
            randomNumber = randomNextLogic.createRandomNumber();
        }
        model.addAttribute("randomNumber", randomNumber);
        return "views/random_number";
    }

    /**
     * метод getNext(аргумент - наш случайный номер) , генерирует следующий числовой номер
     * в случае повтороного нажатия на ссылку http://localhost:8080/number/next ,
     * В метод генерации следующего номера ( createNextNumber ) передается последний элемент коллекции
     * "numberObject", в которую сохраняются все сгенерированные номера
     * @return представление на клиента
     */
    //адрес http://localhost:8080/number/next возвращает следующий номер авто
    @GetMapping(path = "/next")
    public String getNext(String nextNumber, Model model) {
        nextNumber = randomNextLogic.getMyNumberNext();
        if (nextNumber != null & nextNumber.equals(randomNextLogic.getMyNumberNext())) {
            nextNumber = randomNextLogic.createNextNumber(randomNextLogic.getNumberObjects()
                    .get(randomNextLogic.getNumberObjects().size() - 1));
        }
        model.addAttribute("nextNumber", nextNumber);
        return "views/next_number";
    }
}
