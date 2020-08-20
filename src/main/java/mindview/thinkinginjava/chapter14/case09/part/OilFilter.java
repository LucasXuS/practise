package mindview.thinkinginjava.chapter14.case09.part;

public class OilFilter extends Filter {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<OilFilter> {
        @Override
        public OilFilter create() {
            return new OilFilter();
        }
    }
}
