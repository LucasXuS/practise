package mindview.thinkinginjava.chapter14.case09.part;


public class AirFilter extends Filter {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
