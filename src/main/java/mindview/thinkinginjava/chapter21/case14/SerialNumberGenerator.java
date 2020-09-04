package mindview.thinkinginjava.chapter21.case14;

/**
 * @author <a href="mailto:xusong@gtmap.cn">xusong</a>
 * @version 1.0, ${date}
 * @description: ${todo}
 */
public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++;
    }
}
