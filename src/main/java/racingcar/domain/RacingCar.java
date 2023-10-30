package racingcar.domain;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCar {
    private final List<Car> cars;

    public RacingCar(List<String> names) {
        this.cars = names.stream()
                .map(n -> new Car(n, 0))
                .collect(Collectors.toList());
    }

    public void playRacing() {
        this.cars.forEach(Car::forward);
    }

    public String getResultMessage() {
        return this.cars
                .stream()
                .map(Car::resultMessage)
                .collect(Collectors.joining("\n"));
    }

    public String getWinnerMessage() {
        int maxMovementCount = getMaxCarMovementCount();

        return this.cars.stream()
                .filter(c -> c.getMovementCount() == maxMovementCount)
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    private int getMaxCarMovementCount() {
        return this.cars.stream()
                .max(Comparator.comparing(Car::getMovementCount))
                .map(Car::getMovementCount)
                .orElseGet(() -> 0);
    }

}
