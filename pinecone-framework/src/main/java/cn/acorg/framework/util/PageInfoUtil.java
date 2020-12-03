package cn.acorg.framework.util;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 分页返回值工具类
 * @author Z-BL
 * @date 2020年03月04日 16:06:13
 */

public class PageInfoUtil {

    /**
     * description: 构建分页返回值
     * @param sourceList
     * @param target
     * @return: com.github.pagehelper.PageInfo<T>
     * @Author: Z-BL
     * @Date: 2020/3/4 16:07
     */
    public static <T,E> PageInfo<T> buildPage(List<E> sourceList, Class<T> target){
        if(sourceList == null){
            return new PageInfo<>();
        }
        // 获取page信息
        PageInfo<E> orgPage = PageInfo.of(sourceList);
        PageInfo<T> page = new PageInfo<>();
        BeanUtils.copyProperties(orgPage, page);
        // 转换实体
        List<T> list = ConvertUtils.sourceToTarget(sourceList, target);
        page.setList(list);
        return page;
    }

    /**
     *  构建分页返回值,未设置具体值
     * @param sourceList
     * @param target
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T,E> PageInfo<T> buildPageNotData(List<E> sourceList, Class<T> target){
        if(sourceList == null){
            return new PageInfo<>();
        }
        // 获取page信息
        PageInfo orgPage = PageInfo.of(sourceList);
        PageInfo<T> page = new PageInfo<>();
        BeanUtils.copyProperties(orgPage, page);
        return page;
    }

}
