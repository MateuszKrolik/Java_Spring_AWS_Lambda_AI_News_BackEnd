package org.example.data;
import org.example.dtos.City;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CityData {
    private static final List<City> cities = new ArrayList<>();

    public static List<City> getCities() {
        return cities;
    }

    static {
        cities.add(new City("New York", "NY"));
        cities.add(new City("Los Angeles", "CA"));
        cities.add(new City("Arizona", "AZ"));
        cities.add(new City("Florida", "FL"));
    }

}