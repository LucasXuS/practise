package mindview.thinkinginjava.chapter14.case09.part;

public class CabinAirFilter extends Filter {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<CabinAirFilter> {
        @Override
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}
