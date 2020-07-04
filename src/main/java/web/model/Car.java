package web.model;

import java.util.Objects;

public class Car {
    public long number;
    public String model;
    public long price;

    public Car() {
    }

    public Car(long number, String brand, long price) {
        this.number = number;
        this.model = brand;
        this.price = price;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getNumber() == car.getNumber() &&
                getPrice() == car.getPrice() &&
                getModel().equals(car.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getModel(), getPrice());
    }

    @Override
    public String toString() {
        return "Car: " +
                "number=" + number +
                ", model='" + model + '\'' +
                ", price=" + price;
    }
}
