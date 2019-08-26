package com.robin.demo.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ForSelectOption<T> {

    /**
     * 默认选项key
     * @return
     */
    default String defKey() {
        return "";
    }

    /**
     * 默认选项展示文本
     * @return
     */
    default String defValue() {
        return "全部";
    }

    /**
     * 选项ID
     * @return
     */
    default String selectTagId() {
        return this.getClass().getSimpleName();
    }

    default boolean filter(ForSelectOption option) {
        return true;
    }

    /**
     * 获取Select选项
     * @return
     */
    default Map<String, List<SelectOption>> buildSelectOption() {
        ForSelectOption[] constants = this.getClass().getEnumConstants();

        List<SelectOption> options = new ArrayList<>(constants.length + 2);

        options.add(new SelectOption<>(defKey(), defValue()));

        for (ForSelectOption constant : constants) {

            {if (!filter(constant)) continue;}

            options.add(new SelectOption<>(constant.getKey(), constant.getTitle()));
        }

        Map<String, List<SelectOption>> map = new HashMap<>(1);

        map.put(selectTagId(), options);

        return map;

    }

    /**
     * 获取option key
     * @return
     */
    T getKey();

    /**
     * 获取option展示文本
     * @return
     */
    String getTitle();
}
