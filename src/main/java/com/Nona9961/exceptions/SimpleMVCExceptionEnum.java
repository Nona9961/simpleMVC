package com.Nona9961.exceptions;

/**
 * 自定义异常枚举类
 */
public enum SimpleMVCExceptionEnum {
    PROPERTIES_NOT_FOUND("该properties不存在"),
    PROPERTIES_PATH_IS_NULL("未设置properties路径"),
    PROPERTIES_ATTR_INVALID("properties未定义该属性"),
    CONTROLLER_WITHOUT_MAPPING("未设置mapping"),
    CONTROLLER_CREATED_FAILURE("Controller实例创建失败"),
    MAPPING_AMBIGUOUS("重复的mapping"),
    FILE_READ_ERROR("文件读取失败"),
    MAPPING_NOT_FOUND("未找到该mapping"),
    ;// 前面是实例

    private String message;

    SimpleMVCExceptionEnum(String message) {
        this.message = message;
    }
}
