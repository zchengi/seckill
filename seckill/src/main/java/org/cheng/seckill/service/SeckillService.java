package org.cheng.seckill.service;

import org.cheng.seckill.dto.Exposer;
import org.cheng.seckill.dto.SeckillExecution;
import org.cheng.seckill.entity.Seckill;
import org.cheng.seckill.exception.RepeatKillException;
import org.cheng.seckill.exception.SeckillCloseException;
import org.cheng.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by cheng
 * 2017/8/26 13:10
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时，输出秒杀接口地址，否则输入系统时间和秒杀时间
     *
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;
}
