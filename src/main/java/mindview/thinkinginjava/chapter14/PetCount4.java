package net.mindview.chapter14;

import net.mindview.chapter14.Pet.Pet;

public class PetCount4 {

    public static void main(String[] args){
        TypeCounter typeCounter = new TypeCounter(Pet.class);
        for(Pet pet : Pets.createArray(20)){
            typeCounter.count(pet);
        }
        System.out.println(typeCounter);
    }
}
