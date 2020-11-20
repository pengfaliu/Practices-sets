package cn.paul.RedisClusterTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

/**
 * Created by lfp on 2020/10/31.
 */
public class CollectionsDemo {
    public static void main(String[] args) throws IOException {
        Map<String,String> m1 = new HashMap<>();
        m1.put("a","1");
        m1.put("b","2");
        m1.put("c","3");
        m1.put("d", "4");
        System.out.println(m1);
        System.out.println(m1.get("a"));

        Map<String,Date> m2 = new HashMap<>();
        //m2.put("a",Date.from());

        Logger LOGGER = Logger.getLogger("RedisClusterTest.log");
        LOGGER.info("this is a logger test");

        ConsoleHandler consolehandler = new ConsoleHandler();
        consolehandler.setLevel(Level.INFO);
        LOGGER.addHandler(consolehandler);
        LOGGER.info("this is a test consolehandler");
        FileHandler fileHandler = new FileHandler("./CollectionDemo.log");
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new MyLogHander());
        LOGGER.addHandler(fileHandler);
        LOGGER.info("file test log ");
        fileHandler.close();
    }
}

class MyLogHander extends Formatter {
    @Override
    public String format(LogRecord record) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat();

        return ft.format(date) + ":" + record.getLevel() + ":" + record.getMessage()+ "\n";
    }
}

class  LogBackTest {
    //LogbackException
}