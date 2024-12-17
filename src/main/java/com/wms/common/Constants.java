package com.wms.common;

/**
 * 状态码枚举类
 */
public enum Constants {

    CODE_200("200"),// 成功
    CODE_400("400"), //参数错误
    CODE_401("401"),// 权限不足
    CODE_500("500"),// 系统错误
    CODE_600("600");//其他业务异常

    private final String code;

    private Constants(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Constants{" +
                "code='" + code + '\'' +
                '}';
    }
}
