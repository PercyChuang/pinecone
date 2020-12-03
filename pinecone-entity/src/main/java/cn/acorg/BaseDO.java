package cn.acorg;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实体基类
 * @author 松果
 * @version 1.0
 * @date 2020/10/30 13:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseDO<T extends Model<T>> extends Model<T> {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;

}
