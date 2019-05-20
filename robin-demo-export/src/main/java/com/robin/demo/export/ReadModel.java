package com.robin.demo.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ReadModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String userId;

    @ExcelProperty(index = 6)
    private Date time;

    @ExcelProperty(index = 2)
    private String name;

    @ExcelProperty(index = 3)
    private String type;

}
