package mindview.thinkinginjava.chapter14.case09.part;

public class GeneratorBelt extends Belt {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<GeneratorBelt> {
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}
