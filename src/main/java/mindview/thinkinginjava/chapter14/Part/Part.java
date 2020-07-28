package net.mindview.chapter14.Part;

import net.mindview.chapter14.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> list = new ArrayList<>();

    static {
        list.add(new AirFilter.Factory());
        list.add(new CabinAirFilter.Factory());
        list.add(new FanBelt.Factory());
        list.add(new FuelFilter.Factory());
        list.add(new GeneratorBelt.Factory());
        list.add(new OilFilter.Factory());
        list.add(new PowerSteeringBelt.Factory());
    }

    private static Random random = new Random(47);

    public static Part create() {
        int n = random.nextInt(list.size());
        return list.get(n).create();
    }

}
