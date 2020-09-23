package mindview.thinkinginjava.chapter19.caseX.enu;

import mindview.thinkinginjava.chapter19.caseW.Enums;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);
    private Food[] values;

    private Course(Class<? extends Food> c) {
        values = c.getEnumConstants();
    }

    public Food randomSelection(){
        return Enums.random(values);
    }
}
