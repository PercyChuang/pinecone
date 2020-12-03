package cn.acorg.modules.assets.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.acorg.assets.entity.UserAssetsDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户资产 Mapper
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 9:28
 */
@Mapper
public interface UserAssetsMapper extends BaseMapper<UserAssetsDO> {
}
