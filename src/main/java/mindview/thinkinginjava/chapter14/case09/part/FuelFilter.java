package mindview.thinkinginjava.chapter14.case09.part;

public class FuelFilter extends Filter {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
