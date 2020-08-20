package mindview.thinkinginjava.chapter14.case08.demo;

import mindview.thinkinginjava.chapter14.case08.factory.PetsFactory;

public class PetCount2 {
    public static void main(String[] args){
        PetCount.countPets(PetsFactory.creator);
    }
}
