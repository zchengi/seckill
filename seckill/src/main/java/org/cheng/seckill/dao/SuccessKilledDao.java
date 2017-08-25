package org.cheng.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.cheng.seckill.entity.SuccessKilled;
import org.springframework.stereotype.Repository;

/**
 * Created by cheng
 * 2017/8/25 12:43
 */
@Repository
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     *
     * @param seckillId
     * @param userPhone
     * @return 插入行数>1：表示更新的记录行数;0：插入失败
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 查询SuccessKilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
