package mindview.thinkinginjava.chapter14.case08.demo;


import mindview.thinkinginjava.chapter14.case08.creator.LiteralPetCreator;
import mindview.thinkinginjava.chapter14.case08.factory.PetsFactory;
import mindview.thinkinginjava.chapter14.case08.pet.Pet;
import mindview.thinkinginjava.util.MapData;

import java.util.LinkedHashMap;
import java.util.Map;

public class PetCount3 {

    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }

        public void count(Pet pet) {
            for (Map.Entry<Class<? extends Pet>, Integer> entry : this.entrySet()) {
                if (entry.getKey().isInstance(pet)) {
                    put(entry.getKey(), entry.getValue() + 1);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> entry : this.entrySet()) {
                stringBuilder.append(entry.getKey().getSimpleName())
                        .append(" = ").append(entry.getValue()).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }


    public static void main(String[] args) {
        PetCounter petCounter = new PetCounter();
        for (Pet pet : PetsFactory.createArray(20)) {
            petCounter.count(pet);
        }
        System.out.println(petCounter);
    }


}
