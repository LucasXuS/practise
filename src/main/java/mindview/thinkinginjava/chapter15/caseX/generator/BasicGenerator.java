package mindview.thinkinginjava.chapter15.caseX.generator;


import mindview.thinkinginjava.chapter15.caseX.generator.interf.Generator;

public class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> BasicGenerator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }
}
