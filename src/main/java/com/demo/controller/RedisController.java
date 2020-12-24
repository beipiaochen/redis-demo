package com.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Test
    public void opsForValue(){

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        Boolean aBoolean = opsForValue.setIfAbsent("username", "xiaochen");
        System.out.println("键是否存在："+!aBoolean);
        //添加/修改String
//        opsForValue.set("username","xiaochen");
        //获取String
//        String username = opsForValue.get("username");
//        logger.debug(username);
    }

    /**
     * List 底层就是个队列
     */
    @Test
    public void opsForList(){
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        //左添加数据
//        listOperations.leftPush("userList","xiaochen");
//        listOperations.leftPush("userList","xiaoli");
//        listOperations.leftPush("userList","chen");
        //查询数据
        List<String> userList = listOperations.range("userList", 0, listOperations.size("userList"));
        for (int i = 0; i < userList.size(); i++) {
            logger.debug(userList.get(i));
        }
    }
    @Test
    public void opsForHash(){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        //直接覆盖
//        opsForHash.put("opshash","username","xiaochen2");
        //如果存在，则不进行修改
//        opsForHash.putIfAbsent("opshash","username","xiaochen");
//        opsForHash.putIfAbsent("opshash","age","6");
        //获取数据
//        String username = (String) opsForHash.get("opshash", "username");
//        System.out.println(username);
        //获取特定hash值
//        Map<Object, Object> opshash = opsForHash.entries("opshash");
//        System.out.println(opshash);
        //获取key
//        Set<Object> keys = opsForHash.keys("opshash");
//        System.out.println(keys);
//        List<Object> opshash = opsForHash.values("opshash");
//        System.out.println(opshash);
//        Long increment = opsForHash.increment("opshash", "age", 1);
//        System.out.println(increment);
        //
//        Long delete = opsForHash.delete("opshash", "age");
//        System.out.println(delete);

    }

    /**
     * 不重复
     */
    @Test
    public void opsForSet(){
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        Long add = opsForSet.add("set:username", "xiaochen2");
        Set<String> members = opsForSet.members("set:username");
        System.out.println(members);
//        System.out.println(add);
        redisTemplate.opsForZSet();
    }

    @Test
    public void opsForZSet(){
        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
//        Boolean add = opsForZSet.add("zset:username", "xiaochen", 1);
//        System.out.println(add);

        //添加数据到zset中，如果此键存在，就将权重改为指定值，返回该value是否存在
//        Boolean add = opsForZSet.add("zset:username", "xiaochen", 2);
//        System.out.println(add);
        //查看权重 在 min 和 max 之间的数据有多少个。
//        Long count = opsForZSet.count("zset:username", 3, 4);
//        System.out.println(count);
        //增加指定值
//        Double score = opsForZSet.incrementScore("zset:username", "xiaochen", 4);
//        System.out.println(score);




    }


}
