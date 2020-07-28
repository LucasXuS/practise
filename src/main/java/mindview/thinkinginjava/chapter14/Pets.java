package net.mindview.chapter14;

import net.mindview.chapter14.Pet.Pet;

import java.util.ArrayList;

public class Pets {
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
