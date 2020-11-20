
package cn.paul.RedisClusterTest;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;

/**
 * Created by lfp on 2020/10/29.
 */


public class RedisClusterTest {

    public static void main(String[] args){
        try {
            //Connecting to Redis server on localhost
            Jedis jedis = new Jedis("10.2.224.50",6379);
            jedis.auth("123456");
            System.out.println("redis was connected!");

            //check whether server is running or not
            System.out.println("Server is running: " + jedis.ping());
            jedis.close();


            RedisStringOps();
            RedisListOps();
        }
        catch (JedisConnectionException e) {
            System.out.println("cannot connect redis");
            System.out.println(e);
        }
    }

    static int  RedisStringOps() {
        int Success=0;
        Jedis jedis = new Jedis("10.2.224.50",6379);
        jedis.auth("123456");
        jedis.set("a", "test");
        System.out.println("the key a's value is: " + jedis.get("a"));
        jedis.close();
        return Success;
    }

    static void RedisListOps() {
        Jedis jedis = new Jedis("10.2.224.50",6379);
        jedis.auth("123456");
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list","myqsql");
        jedis.lpush("tutorial-list","mongodb");
        jedis.lpush("tutorial-list","fork");
        jedis.lpush("tutorial-list","test");
        jedis.lpush("tutorial-list","you");
        jedis.lpush("tutorial-list", "china");

        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,6);

        for (int i=0; i< list.size();i++) {
            System.out.println("the tutorial-list store values "+ i + "rd is: " +list.get(i));
        }
        jedis.close();

    }
    static void RedisKeysOps(){
        //todo
        Jedis jedis = new Jedis("10.2.224.50",6379);
        /*List<String> list = jedis.keys; //there is no method;

        for (int i=0; i< list.size();i++) {
            System.out.println("list of stored keys: " +list.get(i));
        }
        jedis.close();
        */
    }

}

