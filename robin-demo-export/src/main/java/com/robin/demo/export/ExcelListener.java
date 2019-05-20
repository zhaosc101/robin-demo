package com.robin.demo.export;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelListener extends AnalysisEventListener<ReadModel> {

    private List<Object>  data = new ArrayList<>(101);

    @Override
    public void invoke(ReadModel object, AnalysisContext context) {
//        System.out.println(context.getCurrentSheet()+""+data.size());

//        System.out.println(context.getCurrentRowNum());
        System.out.println("log invoke~~~~~~");
        data.add(object);
        if(data.size()>=100){
            doSomething();
            data = new ArrayList<Object>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("log doAfterAllAnalysed~~~~~~");
        //        doSomething();
    }

    public void doSomething(){
        for (Object o:data) {
            System.out.println(o);
        }
    }
}
