package cn.acorg.modules.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.acorg.coupon.entity.CouponUserRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户优惠券记录 Mapper
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 16:07
 */
@Mapper
public interface CouponUserRecordMapper extends BaseMapper<CouponUserRecordDO> {
}
