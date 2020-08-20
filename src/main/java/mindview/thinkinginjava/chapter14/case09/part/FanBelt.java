package mindview.thinkinginjava.chapter14.case09.part;

public class FanBelt extends Belt {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
