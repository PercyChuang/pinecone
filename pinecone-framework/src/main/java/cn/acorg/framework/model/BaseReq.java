package cn.acorg.framework.model;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.hutool.db.sql.Order;
import cn.acorg.framework.cache.token.TokenInfo;
import cn.acorg.framework.filter.SQLFilter;
import com.github.pagehelper.PageHelper;
import lombok.Data;

import java.io.Serializable;

/**
 * 入参父类
 * @author 松果
 * @date 2020/10/29 16:48
 */
@Data
public abstract class BaseReq implements Serializable {

    private static final long serialVersionUID = -4841139077571170359L;
    
    /**
     * 当前实体分页对象
     */
    protected Page page;

    /**
     * 当前用户
     */
    protected TokenInfo currentUser;

    /**
     * 初始化分页信息
     *
     * @return void
     * @author 松果
     * @date 2020/10/29 16:58
     */
    public void initPageInfo() {
        Page page = this.page;
        if (page == null) {
            // 默认查询10条
            page = new Page(1, 10);
        }
        // 开始分页
        if (page.getPageSize() > 0) {
            if (page.getPageNumber() == 0) {
                page.setPageNumber(1);
            }
            PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        }

        // 设置排序
        Order[] orders = page.getOrders();
        if (orders != null) {
            StringBuffer orderBy = new StringBuffer();
            for (Order order : orders) {
                orderBy.append(order.getField())
                        .append(" ")
                        .append(order.getDirection())
                        .append(",");
            }
            // 移除后缀,
            String order = StrUtil.removeSuffix(orderBy.toString(), ",");
            // 防止SQL注入
            PageHelper.orderBy(SQLFilter.sqlInject(order));
        }
    }
}
