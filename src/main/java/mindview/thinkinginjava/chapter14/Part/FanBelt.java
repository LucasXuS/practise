package net.mindview.chapter14.Part;

public class FanBelt extends Belt {
    public static class Factory implements net.mindview.chapter14.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }
}
