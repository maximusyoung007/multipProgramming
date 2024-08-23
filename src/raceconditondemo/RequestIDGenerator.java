package raceconditondemo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/23      create
 */
public class RequestIDGenerator {
    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();
    private final static short SQE_UPPER_LIMIT = 999;
    private short sequence = -1;

    private RequestIDGenerator() {

    }

    public short nextSequence() {
        //多线程场景下，有可能第一个线程拿到了sequence=1，第二个线程进来了，但是第一个线程还没来得及更新sequence，所以第二个线程拿到的也是1，造成重复
        if (sequence > SQE_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    public String nextID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String timeStamp = sdf.format(new Date());
        DecimalFormat df = new DecimalFormat("000");

        short sequence = nextSequence();

        return "0049" + timeStamp + df.format(sequence);
    }

    //单例模式， 通过此方法可以保证每次获取到的都是同一个实例,才可以保证每次获得的序列号都是递增的
    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }
}
