package cn.acorg.framework.validator;

import cn.acorg.framework.exception.CommonResultEnum;
import cn.acorg.framework.exception.ServiceBaseException;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author architect
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws ServiceBaseException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws ServiceBaseException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator()
                    .next();
            throw new ServiceBaseException(constraint.getMessage(), CommonResultEnum.PARAMS_ERROR.getCode());
        }
    }

    /**
     * 功能描述 校验-手机号是否正确
     *
     * @param phone:手机号
     * @author Z-YJ
     * @date 2020/4/3
     */
    public static void validateMobile(String phone) throws ServiceBaseException {
        // 1 判断-手机号是否存在
        if (StrUtil.isBlank(phone)) {
            throw new ServiceBaseException(CommonResultEnum.PARAMS_ERROR, "手机号不能为空！");
        }

        // 2 校验-手机号（格式）
        boolean mobile = cn.hutool.core.lang.Validator.isMobile(phone);

        // 3 判断-是否正确
        if (!mobile) {
            throw new ServiceBaseException(CommonResultEnum.PARAMS_ERROR, "手机号不正确！");
        }
    }
}
