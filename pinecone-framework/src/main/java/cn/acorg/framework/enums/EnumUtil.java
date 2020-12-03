package cn.acorg.framework.enums;

/**
 * 枚举工具类
 * @author 松果
 * @version 1.0
 * @date 2020/11/23 11:11
 */
public class EnumUtil {

    /**
     * 根据枚举值及枚举类获取 值对应的 枚举对象
     * @param value 枚举值
     * @param t 枚举类
     * @author 松果
     * @date 2020/11/23 11:22
     * @return T
     */
    public static <T extends BaseEnum> T getByValue(int value, Class<T> t){
        for(T item: t.getEnumConstants()){
            if(item.getValue() == value){
                return item;
            }
        }
        return null;
    }
}
