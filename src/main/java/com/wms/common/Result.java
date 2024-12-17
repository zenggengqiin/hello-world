package com.wms.common;

import lombok.Data;

@Data
public class Result {

    private int code;//状态编码
    private String msg;//返回结果
    private Long total;//总记录数
    private Object data;//数据

    private static Result result(int code, String msg, Long total, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setTotal(total);
        r.setData(data);
        return r;
    }

    public static Result fail() {
        return result(400, "失败", 0L, null);
    }

    public static Result success(Object data){
        return result(200, "成功", 0L, data);
    }
    public static Result success(){
        return result(200, "成功", 0L, null);
    }

    public static Result success(Object data,Long total){
        return result(200, "成功", total, data);
    }
}
