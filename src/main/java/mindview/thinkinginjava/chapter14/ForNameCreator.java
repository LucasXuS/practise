package net.mindview.chapter14;

import net.mindview.chapter14.Pet.Pet;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {

    // 静态变量和静态代码块的运行根据顺序运行

    private static String[] typeNames = new String[]{
            "net.mindview.chapter14.Pet.Cymric"
            , "net.mindview.chapter14.Pet.EgyptianMau"
            , "net.mindview.chapter14.Pet.Hamster"
            , "net.mindview.chapter14.Pet.Manx"
            , "net.mindview.chapter14.Pet.Mouse"
            , "net.mindview.chapter14.Pet.Mutt"
            , "net.mindview.chapter14.Pet.Pug"
            , "net.mindview.chapter14.Pet.Rat"

    };
    private static ArrayList<Class<? extends Pet>> types = new ArrayList<>();

    static {
        loader();
    }

    @SuppressWarnings("unchecked")
    private static void loader() {
        for (String typeName : typeNames) {
            try {
                types.add((Class<? extends Pet>) Class.forName(typeName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
