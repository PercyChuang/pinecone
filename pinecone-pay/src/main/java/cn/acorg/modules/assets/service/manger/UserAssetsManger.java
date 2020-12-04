package cn.acorg.modules.assets.service.manger;

import cn.acorg.assets.entity.UserAssetsDO;
import cn.acorg.modules.assets.dao.UserAssetsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资产获取
 * @author 松果
 * @version 1.0
 * @date 2020/11/10 14:27
 */
@Service
public class UserAssetsManger {

    @Resource
    private UserAssetsMapper userAssetsMapper;

    /**
     * 根据用户ID，查询用户资产
     * @param userId 用户ID
     * @author 松果
     * @date 2020/11/10 14:29
     * @return cn.acorg.modules.assets.entity.UserAssetsDO
     */
    public UserAssetsDO findByUserId(Long userId) {
        QueryWrapper<UserAssetsDO> wrapper = Wrappers.query();
        wrapper.lambda()
                .eq(UserAssetsDO::getUserId, userId);
        List<UserAssetsDO> list = userAssetsMapper.selectList(wrapper);
        return list.size() == 0 ? null : list.get(0);
    }
}
