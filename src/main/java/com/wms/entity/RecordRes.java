package com.wms.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordRes extends Record{
    private String username;
    private String goodsname;
    private String storagename;
    private String goodstypename;
}
