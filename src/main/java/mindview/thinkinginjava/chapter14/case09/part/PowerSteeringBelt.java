package mindview.thinkinginjava.chapter14.case09.part;

public class PowerSteeringBelt extends Belt {
    public static class Factory implements mindview.thinkinginjava.chapter14.case09.factory.Factory<PowerSteeringBelt> {
        @Override
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}
