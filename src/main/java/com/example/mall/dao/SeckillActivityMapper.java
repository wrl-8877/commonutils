package com.example.mall.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.domain.SeckillActivity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    List<SeckillActivity> findUnstartSeckill();

    int update(SeckillActivity activity);

    SeckillActivity findById(int id);

    List<SeckillActivity> selectEndSeckill();
}
