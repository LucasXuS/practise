package chapter07.case01;

public class SpaceShipDelegation {

    private String name;

    public SpaceShipDelegation(String name){
        this.name = name;
    }

    public void forward(int velocity) {
        spaceShipControls.forward(velocity);
    }

    public void backward(int velocity) {
        spaceShipControls.backward(velocity);
    }

    public void up(int velocity) {
        spaceShipControls.up(velocity);
    }

    public void down(int velocity) {
        spaceShipControls.down(velocity);
    }

    public void left(int velocity) {
        spaceShipControls.left(velocity);
    }

    public void right(int velocity) {
        spaceShipControls.right(velocity);
    }

    private SpaceShipControls spaceShipControls = new SpaceShipControls();

    @Override
    public String toString(){
        return name;
    }
}
