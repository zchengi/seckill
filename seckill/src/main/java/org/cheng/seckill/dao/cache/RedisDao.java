package org.cheng.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.cheng.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by cheng
 * 2017/8/28 14:39
 */
public class RedisDao {

    private JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //并没有实现内部序列号操作
                //get->byte[]->反序列化->Object(Seckill)
                //采用自定义序列化
                byte[] bytes = jedis.get(key.getBytes());
                //缓存获取到
                if (bytes != null) {
                    //空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //seckill 被反序列化
                    return seckill;
                }
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set Object(Seckill)->序列化->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSeckillId();
                //缓存器
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60;//1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
