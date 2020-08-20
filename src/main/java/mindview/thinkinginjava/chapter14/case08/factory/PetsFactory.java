package mindview.thinkinginjava.chapter14.case08.factory;


import mindview.thinkinginjava.chapter14.case08.creator.LiteralPetCreator;
import mindview.thinkinginjava.chapter14.case08.creator.PetCreator;
import mindview.thinkinginjava.chapter14.case08.pet.Pet;

import java.util.ArrayList;

public class PetsFactory {
    public static final PetCreator creator =
            new LiteralPetCreator();

    // 封装，隐藏creator类型
    public static Pet randomPet() {
        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }
}
