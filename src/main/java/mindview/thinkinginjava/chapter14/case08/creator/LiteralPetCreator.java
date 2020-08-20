package mindview.thinkinginjava.chapter14.case08.creator;


import mindview.thinkinginjava.chapter14.case08.pet.*;

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
