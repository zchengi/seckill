package org.cheng.seckill.exception;

/**
 * 秒杀异常
 * Created by cheng
 * 2017/8/26 13:35
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
