package mindview.thinkinginjava.chapter21.case32.model;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class Fat {

    private volatile double d = 0.0d;
    private static int counter = 0;
    private final int id = counter++;


    public Fat(){
        for(int i = 1; i < 10000; i++){
            d += (Math.PI + Math.E)/(double)i;
        }
    }

    public void operation(){
        System.out.println(this);
    }


    @Override
    public String toString(){
        return "Fat id: " + id + " ";
    }
}

