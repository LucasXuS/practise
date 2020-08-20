package mindview.thinkinginjava.chapter14.case08.demo;


import mindview.thinkinginjava.chapter14.case08.counter.TypeCounter;
import mindview.thinkinginjava.chapter14.case08.factory.PetsFactory;
import mindview.thinkinginjava.chapter14.case08.pet.Pet;

public class PetCount4 {

    public static void main(String[] args){
        TypeCounter typeCounter = new TypeCounter(Pet.class);
        for(Pet pet : PetsFactory.createArray(20)){
            typeCounter.count(pet);
        }
        System.out.println(typeCounter);
    }
}
