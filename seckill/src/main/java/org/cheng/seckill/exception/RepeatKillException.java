package org.cheng.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * Created by cheng
 * 2017/8/26 13:30
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
