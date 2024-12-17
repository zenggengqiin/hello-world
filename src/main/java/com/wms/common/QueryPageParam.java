package com.wms.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认值
    private static int PAGE_SIZE=10;
    private static int PAGE_NUM=1;

    //接受值
    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;

    //查询条件
    private HashMap param = new HashMap();
}
