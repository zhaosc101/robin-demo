//import com.alibaba.fastjson.JSONObject;
//import com.tcredit.trics.platform.rest.util.sign.AESUtil;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//
//
///**
// * 还到--行为评分
//
// *
// */
//public class CashLoanFilterUtils_hdpf {
//
//	static Logger logger = LoggerFactory.getLogger(CashLoanFilterUtils_hdpf.class);
//	private static String blackNameList = "";
//	private static String gryNameList = "";
//	static{
////	String defaultBlack = "d7DevRemoteLogon_Result,d7DevConMbl_Result,m3DevConMbl_Result,m3MblConDev_Result,isVM_Result,isEmulator_Result,"
//	//				+	//"d1VPNConDev_Result,d1ProxyConDev_Result,h1DevApplyNumAndGap_Result,h1MblORIdApplyNum_Result,d1AndM1_MblORIdApplyNum_Result,h1DevConMblORId_Result,"
//	//				+	"d1AndH1_DevConMblORId_Result,h1MblORIdConDev_Result,d1AndH1_MblORIdConDev_Result,devOnceVPN_Result,smLoanOverdueNumAndLevel_Result,"
//	//				+	//"d30SmLoanOverdueNumAndLevel_Result,hitTfdBlk_Result,hitBrSpecialList_Result,hitBrExecution_Result,brApplyLoanTimes1_Result,brApplyLoanTimes3_Result,"
//	//				+	//"brApplyLoanTimes4_Result,isOverdue_Result,applyNum1Year_Result,applySuccRate1Year_Result,hit91BlackRrc_Result,hit91HRisk_Result,smLoanLogonNum_Result,"
//	//				+	//"smLoanApplyNum_Result,smLoanPassNum_Result,d7SmLoanLogonNum_Result,d7SmLoanPassNum_Result,d30SmLoanLogonNum_Result,d30SmLoanRejectRate_Result";
//		String defaultBlack ="hitBrSpecialList_Result,mblState_Result,isOverdue_Result,hitBrExecution_Result,brApplyLoanTimes4_Result,"
//				+ "brApplyLoanTimes3_Result,hitTfdBlk_Result,d7DevRemoteLogon_Result,d7DevConMbl_Result,m3DevConMbl_Result,h1DevConMblORId_Result,"
//				+ "hit91BlackRrc_Result,d1VPNConDev_Result,d1ProxyConDev_Result,m3MblConDev_Result,isVM_Result,isEmulator_Result,"
//				+ "d1IPLostConDevAndConMbl_Result,d1VPNConIP_Result,d1ProxyConIP_Result,h1DevApplyNumAndGap_Result,h1IpApplyNumAndGap_Result,"
//				+ "d1IpApplyNumAndproporDevInIp_Result,h1MblORIdApplyNum_Result,d1AndM1_MblORIdApplyNum_Result,h1IpConMbl_Result,"
//				+ "d1AndM1IPConMbl_Result,d1AndH1_DevConMblORId_Result,h1MblORIdConDev_Result,d1AndH1_MblORIdConDev_Result,devOnceVPN_Result,"
//				+ "top3In30HitSelfBlk_Result,contactInfHitSelfBlkNum_Result,brSpecialList1_Result";
//		String defaultGry="applySuccRate1Year_Result,brApplyLoanTimes1_Result,applyNum1Year_Result,hit91HRisk_Result,smLoanApplyNum_Result,"
//				+ "smLoanOverdueNumAndLevel_Result,smLoanLogonNum_Result,d7SmLoanLogonNum_Result,txtyRiskScore_Result,d30SmLoanOverdueNumAndLevel_Result,"
//				+ "smLoanPassNum_Result,d7SmLoanPassNum_Result,d30SmLoanLogonNum_Result,d30SmLoanRejectRate_Result,rhMultiScore_Result";
//		//Properties settings = ConfigUtils.loadProperties("settings.properties");
//		//if(null != settings){
//		//	blackNameList = settings.getProperty("blackName", defaultBlack);
//		//}else{
//			blackNameList = defaultBlack;
//			gryNameList = defaultGry;
//		//}
//		String[] blackArray = blackNameList.split(",");
//		String[] gryArray = gryNameList.split(",");
//		logger.info("------拉黑名单数量:{},------{}",blackArray.length, blackNameList);
//		logger.info("------拉灰名单数量:{},------{}",gryArray.length, gryNameList);
//	}
//
//
//	public  JSONObject getDetailResult(JSONObject instructionObject, boolean isAes,
//			String tokenId, Map<String, Object>  paramMap){
//		logger.info("过滤传入参数:"+JSONObject.toJSONString(paramMap));
//		// 需要返回到上一层的 Object
//		JSONObject resultObject = new JSONObject();
//		String status  = instructionObject.getString("status");
//		//如果授信失败
//        if(!status.equals("0")){
//			resultObject.put("credSts", "F");
//			resultObject.put("behaviorScore", 0L);
//			resultObject.put("behaviorLvl", "");
//			return setDefautKey(resultObject);
//		}
//
//		String realResult;
//		if (isAes) {
//			realResult = AESUtil.decode(tokenId, instructionObject.getString("data"));
//		} else {
//			realResult = instructionObject.getString("data");
//		}
//		// 原始的方案输出结果中的 data 部分
//		JSONObject dataObject = JSONObject.parseObject(realResult);
//
//		resultObject.put("credSts", "S");
//		//行为评分
//		String behaviorScore = getNodeValue(dataObject,"behaviorScore");
//		resultObject.put("behaviorScore",behaviorScore == null ? new Double(0):new Double(behaviorScore) );
//		//行为评级
//		String behaviorLvl = getNodeValue(dataObject,"behaviorLvl");
//		resultObject.put("behaviorLvl",behaviorLvl );
//
//		return this.setDefautKey(resultObject);
//	}
//
//	public  String getNodeValue(JSONObject dataObject,String nodeName){
//		JSONObject nodeObject ;
//		if(dataObject.containsKey(nodeName)){
//			nodeObject = dataObject.getJSONObject(nodeName);
//			if(nodeObject != null){
//				if(nodeObject.containsKey("value")){
//					String nodeValue = nodeObject.getString("value");
//					if(StringUtils.isNotBlank(nodeValue)){
//						return nodeValue;
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	//如果输出没有该字段，就补null
//	public  JSONObject setDefautKey(JSONObject dataObject){
//		return dataObject;
//	}
//
//	public static void main(String[] args) {
//
//		String[] blackArray = CashLoanFilterUtils_hdpf.blackNameList.split(",");
//		System.out.println(blackArray.length);
//		for(String b : blackArray){
//			System.out.println(b);
//		}
//	}
//}
