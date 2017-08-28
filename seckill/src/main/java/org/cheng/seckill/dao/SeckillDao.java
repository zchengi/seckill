package org.cheng.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.cheng.seckill.entity.Seckill;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng
 * 2017/8/25 12:40
 */
@Repository
public interface SeckillDao {

    /**
     * 减少库存
     *
     * @param seckillId
     * @param killTime
     * @return 减少行数>1：表示更新的记录行数;0：减少失败
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀长跑列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
