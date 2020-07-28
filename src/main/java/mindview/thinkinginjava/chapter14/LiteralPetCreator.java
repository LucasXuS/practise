package net.mindview.chapter14;

import net.mindview.chapter14.Pet.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {

    public static final List<Class<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(
                    Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
                    Cymric.class, Rat.class, Mouse.class, Hamster.class));

    @Override
    public List<Class<? extends Pet>> types() {
        return allTypes;
    }
}
