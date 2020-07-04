package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.service.CarService;

import java.io.UnsupportedEncodingException;

@Controller//
@RequestMapping("/")// дефолтный ЮРЛ, ниже уточнение
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "cars", method = RequestMethod.GET)//Value — это адрес в браузере; Method — метод запроса
    public String printCars(ModelMap model) throws UnsupportedEncodingException {//
        model.addAttribute("carList", carService.getCars());
//        String mess = "cars.title";
//        model.addAttribute("mess", mess);

        return "cars";//вернет строку-название html / jsp страницы
    }
}
