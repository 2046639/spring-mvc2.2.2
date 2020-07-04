package web.service;


import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1, "Hover", 1));
        carList.add(new Car(2, "BMW", 2));
        carList.add(new Car(3, "Toyota", 3));
        carList.add(new Car(4, "Opel", 4));
        return carList;
    }
}
