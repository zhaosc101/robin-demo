package com.robin.demo.common;

import com.google.common.collect.Maps;
import com.suixingpay.antifraud.common.constants.*;
import com.suixingpay.antifraud.common.support.utils.JsonUtils;
import com.suixingpay.antifraud.comom.BlackItemTypeEnum;
import com.suixingpay.antifraud.comom.ChannelEnum;
import com.suixingpay.antifraud.comom.ProcessNodeEnum;
import com.suixingpay.antifraud.dao.beans.datagate.RcmDictDto;
import com.suixingpay.antifraud.dto.interact.BlackGrayListDto;
import com.suixingpay.antifraud.gateway.core.utils.OperationResult;
import com.suixingpay.antifraud.service.gateway.DataGateServiceProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

import static com.suixingpay.antifraud.dao.beans.datagate.DataGateConstPool.GW_CODE_LOAD_RCM_DICT;
import static com.suixingpay.antifraud.dao.beans.datagate.DataGateConstPool.GW_PARAM_DICT_CNT_REL_TYPE;
import static com.suixingpay.antifraud.gateway.core.utils.OperationResult.RESULT.*;
import static com.suixingpay.antifraud.gateway.core.utils.OperationResult.RESULT.R_SUCCESS;

@Api("通用部分API接口")
@RestController
@RequestMapping("common")
@Slf4j
public class CommonController {

    @Autowired
    private DataGateServiceProxy dataGateServiceProxy;
    
    @ApiOperation(value = "查询下拉框选项", notes = "返回结构: " +
            "{rule_status: {规则状态}, " +
            "ruleset_status: {规则集状态}, " +
            "rule_limit_type: {规则限制类型}, " +
            "product_status: {产品状态}, " +
            "rule_event_type: {规则事件类型}, " +
            "busin_type: {业务线类型}," +
            "case_source_enum:{工单类型}}," +
            "case_limit_type:{工单限制类型}, " +
            "cntRelTypes:{联系人关系字典}, " +
            "black_gray_type: {黑灰名单类型}, " +
            "black_gray_channel: {黑灰名单渠道}, " +
            "black_gray_status: {黑灰名单渠道}, " +
            "black_item_type: {黑灰名单属性类型}")
    @GetMapping("select/options")
    public OperationResult<Map<String, Object>> getCommonSelectOption() {

        Map<String, Object> options = new HashMap<>();

        ForSelectOption[] arr = {

                Rule.Status.ENABLE, Rule.LimitType.INTERCEPT, Rule.EventType.E_UNKNOWN,
                Ruleset.Status.DELETE, Product.Status.DELETE, BusiTypeEnum.UNKNOWN,
                Label.Type.BLACK_LIST, Label.Level.L_ONE, Label.Status.DISABLE, Case.CaseResourceEnum.EXCEL,
                Case.CaseLimitTypeEnum.UN_LIMIT,Rule.LimitTypeConfig.INTERCEPT
        };

        for (ForSelectOption item : arr) {
            options.putAll(item.buildSelectOption());
        }
    
        Map<String, Object> params = Maps.newHashMap();
        params.put("dictType", GW_PARAM_DICT_CNT_REL_TYPE);
        final List<Map> maps = dataGateServiceProxy.loadDataFromDataGate(GW_CODE_LOAD_RCM_DICT, Map.class, params, 0, 100);
        final List<RcmDictDto> rcmDictDtos = JsonUtils.readListValue(maps, RcmDictDto.class);
        options.put("cntRelTypes", rcmDictDtos);

        //黑灰名单
        options.putAll(buildBlackGrayOptions());

        return new OperationResult<>(R_SUCCESS, options);
    }

    private Map<String, List<SelectOption<String>>> buildBlackGrayOptions() {
        Map<String, List<SelectOption<String>>> options = new HashMap<>();

        ChannelEnum[] values = ChannelEnum.values();

        List<SelectOption<String>> channels = new ArrayList<>(values.length + 2);
        channels.add(new SelectOption<>("", "请选择"));

        for (ChannelEnum item : values) {
            channels.add(new SelectOption<>(item.key, item.title));
        }

        options.put("black_gray_channel", channels);

        List<SelectOption<String>> types = Arrays.asList(
                new SelectOption<>("", "请选择"),
                new SelectOption<>(BlackGrayListDto.bg_type_blk, "黑名单"),
                new SelectOption<>(BlackGrayListDto.bg_type_gry, "灰名单"),
                new SelectOption<>(BlackGrayListDto.bg_type_multipoint, "多头名单")
        );

        options.put("black_gray_type", types);

        List<SelectOption<String>> status = Arrays.asList(
                new SelectOption<>("", "请选择"),
                new SelectOption<>(BlackGrayListDto.blk_sts_valid, "有效"),
                new SelectOption<>(BlackGrayListDto.blk_sts_novalid, "无效")
        );

        options.put("black_gray_status", status);

        BlackItemTypeEnum[] itemTypeEnums = BlackItemTypeEnum.values();

        List<SelectOption<String>> itemTypeOptions = Arrays.stream(itemTypeEnums).map(item -> new SelectOption<>(item.blackType, item.msg)).collect(Collectors.toList());

        itemTypeOptions.add(0, new SelectOption<>("", "请选择"));

        options.put("black_item_type", itemTypeOptions);

        ProcessNodeEnum[] processNodeEnums = ProcessNodeEnum.values();

        List<SelectOption<String>> nodeOptions = Arrays.stream(processNodeEnums).map(item -> new SelectOption<>(item.key, item.title)).collect(Collectors.toList());

        nodeOptions.add(0, new SelectOption<>("", "请选择"));

        options.put("black_node_type", nodeOptions);

        return options;

    }

}
