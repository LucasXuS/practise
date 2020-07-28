package net.mindview.chapter14.Part;

public class PowerSteeringBelt extends Belt {
    public static class Factory implements net.mindview.chapter14.factory.Factory<PowerSteeringBelt> {
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}
