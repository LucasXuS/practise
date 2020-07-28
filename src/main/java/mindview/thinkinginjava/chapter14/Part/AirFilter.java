package net.mindview.chapter14.Part;

import net.mindview.chapter14.factory.Factory;

public class AirFilter extends Filter {
    public static class Factory implements net.mindview.chapter14.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }
}
