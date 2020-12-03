package cn.acorg.modules.order.dao;

import cn.acorg.modules.order.entity.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单 Mapper
 * @author 松果
 * @version 1.0
 * @date 2020/11/9 15:56
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

}
