package net.mindview.chapter14;

import net.mindview.chapter14.Pet.Pet;

import java.util.IllegalFormatException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeCounter extends LinkedHashMap<Class<?>, Integer> {
    private Class<?> baseType;

    public TypeCounter(Class<?> type) {
        baseType = type;
    }

    public void count(Object object) {
        Class<?> type = object.getClass();
        if (baseType == null || !baseType.isAssignableFrom(type)) {
            throw new RuntimeException(type.getSimpleName()
                    + " is not the subClass of " + baseType.getSimpleName());
        }
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer count = get(type);
        if (count == null || count == 0) {
            put(type, 1);
        } else {
            put(type, count + 1);
        }
        Class<?> superType = type.getSuperclass();
        if(superType != null && baseType.isAssignableFrom(superType)){
            countClass(superType);
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> entry : this.entrySet()) {
            stringBuilder.append(entry.getKey().getSimpleName())
                    .append(" = ").append(entry.getValue()).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
