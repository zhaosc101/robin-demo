package com.robin.demo.common;

public interface Rule {

    //validate group
    interface Query {}
    interface New {}


    enum Status implements ForSelectOption<Integer>{
        DELETE(-1, "删除"),
        DISABLE(0, "禁用"),
        ENABLE(1, "启用");

        public final Integer key;
        public final String value;

        Status(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String selectTagId() {
            return "rule_status";
        }

        public Integer getKey() {
            return key;
        }

        public String getTitle() {
            return value;
        }
    }

    enum OperatorType {
        OP_ADD_NEW(1, "添加"),
        OP_UPDATE(2, "修改"),
        OP_DELETE(3, "删除"),
        OP_UNKNOWN(-9, "未知"),
        ;

        public final Integer key;
        public final String value;

        OperatorType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public static OperatorType getOpTypeByKey(Integer key) {
            OperatorType[] values = OperatorType.values();
            for (OperatorType opType : values) {
                if (opType.key.equals(key)) {
                    return opType;
                }
            }

            return OP_UNKNOWN;
        }
    }

    enum EventType implements ForSelectOption<String> {
        E_RECORD("0", "记录"),
        E_WARNING("1", "预警"),
        E_CREATE_ORDER("2", "生成工单"),
        E_UNKNOWN("-9", "未知"),
        ;

        public final String key;
        public final String value;

        EventType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public static EventType getEventTypeByKey(String key) {
            EventType[] values = EventType.values();
            for (EventType eventType : values) {
                if (eventType.key.equals(key)) {
                    return eventType;
                }
            }

            return E_UNKNOWN;
        }

        @Override
        public String selectTagId() {
            return "rule_event_type";
        }

        @Override
        public boolean filter(ForSelectOption option) {
            return option != E_UNKNOWN;
        }

        public String getKey() {
            return key;
        }

        public String getTitle() {
            return value;
        }
    }

    enum LimitType implements ForSelectOption<Integer> {

        NOT_INTERCEPT("Y",0, "未限制",99),
//        BLACK_LIST("N",1, "黑名单",1),
        DISABLE("N",2, "禁用",2),
        FREEZE("N",3, "冻结",3),

        INTERCEPT("I",4, "拦截中",98),
//        GREY_LIST("N",5, "灰名单",4),

        ;

        public final String status;
        public final int code;
        public final String message;
        public final int securityLevel;

        LimitType(String status, int code, String message, int securityLevel) {
            this.status = status;
            this.code = code;
            this.message = message;
            this.securityLevel = securityLevel;
        }

        public static boolean validateCode(Integer code) {

            if (code == null) return false;

            LimitType[] values = LimitType.values();
            for (LimitType type : values) {
                if (type.code == code) return true;
            }

            return false;

        }


        @Override
        public String selectTagId() {
            return "rule_limit_type";
        }

        @Override
        public Integer getKey() {
            return code;
        }

        @Override
        public String getTitle() {
            return message;
        }
    }

    //限制类型配置项和LimitType 规则配置区分开
    enum LimitTypeConfig implements ForSelectOption<Integer> {

        NOT_INTERCEPT("Y",0, "未限制",99),
                BLACK_LIST("N",1, "黑名单",1),
        DISABLE("N",2, "禁用",2),
        FREEZE("N",3, "冻结",3),

        INTERCEPT("I",4, "拦截中",98),
                GREY_LIST("N",5, "灰名单",4),

        ;

        public final String status;
        public final int code;
        public final String message;
        public final int securityLevel;

        LimitTypeConfig(String status, int code, String message, int securityLevel) {
            this.status = status;
            this.code = code;
            this.message = message;
            this.securityLevel = securityLevel;
        }

        public static boolean validateCode(Integer code) {

            if (code == null) return false;

            LimitType[] values = LimitType.values();
            for (LimitType type : values) {
                if (type.code == code) return true;
            }

            return false;

        }


        @Override
        public String selectTagId() {
            return "rule_limit_type_config";
        }

        @Override
        public Integer getKey() {
            return code;
        }

        @Override
        public String getTitle() {
            return message;
        }
    }

    enum InterceptorSwitchEnum {
        NOT_AUTO("NOT_AUTO","手动"),
        AUTO("AUTO","自动"),
        QUERY("QUERY","查询开关状态");

        public final String code;
        public final String message;

        InterceptorSwitchEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public static InterceptorSwitchEnum getByCode(String code) {
            InterceptorSwitchEnum[] values = InterceptorSwitchEnum.values();

            for (InterceptorSwitchEnum item : values) {
                if (item.code.equalsIgnoreCase(code)) {
                    return item;
                }
            }

            return null;
        }
    }

}
