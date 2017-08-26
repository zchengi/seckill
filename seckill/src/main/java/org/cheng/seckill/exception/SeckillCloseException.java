package org.cheng.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by cheng
 * 2017/8/26 13:34
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
