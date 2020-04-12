package ru.khalitov.numberGenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /** аргумент randomNumber , в нее сохраняем наш сгенерированный номер в компоненте
     * RandomNextLogic, так же его передаём в представление "random_number" - это наш шаблон,
     * используем шаблонизатор "Thymeleaf" . В случае перехода на главную страницу, и повторном
     * нажатии на ссылку , генерируется новый номер
     * http://localhost:8080/number/random возвращает представление клиенту , случайный номер авто
     */
    @GetMapping(path = "/random")
    public String getRandom(String randomNumber, Model model) {
        randomNumber = randomNextLogic.getMyNumberRandom();
        if (randomNumber!=null & randomNumber.equals(randomNextLogic.getMyNumberRandom())){
            randomNumber= randomNextLogic.getRandomNumber();
        }
        model.addAttribute("randomNumber",randomNumber );
        return "views/random_number";
    }

    /** в методе getNext будет номер следующий за случайным, планируется проработать метод,
     * выдающий последовательные цифры
     * @return
     */
    //адрес http://localhost:8080/number/next возвращает следующий номер авто
    @GetMapping(path = "/next")
    public String getNext() {
        return "views/next_number";
    }
}
