package mindview.thinkinginjava.chapter14.case08.demo;


import mindview.thinkinginjava.chapter14.case08.creator.ForNameCreator;
import mindview.thinkinginjava.chapter14.case08.creator.PetCreator;
import mindview.thinkinginjava.chapter14.case08.pet.Pet;

import java.util.HashMap;

public class PetCount {
    static class PetCounter extends HashMap<String, Integer> {
        public void count(String type) {
            Integer quantity = get(type);
            if (quantity == null) {
                put(type, 1);
            } else {
                put(type, quantity + 1);
            }
        }
    }

    public static void countPets(PetCreator petCreator) {
        PetCounter counter = new PetCounter();
        StringBuilder stringBuilder = new StringBuilder();
        for (Pet pet : petCreator.createArray(20)) {
            String className = pet.getClass().getSimpleName();
            if (stringBuilder.length() == 0) {
                stringBuilder.append(className);
            } else {
                stringBuilder.append(" ").append(className);
            }
            counter.count(className);
        }
        System.out.println(stringBuilder.toString());
        System.out.println(counter);
    }

    public static void main(String[] args){
        countPets(new ForNameCreator());
    }
}
