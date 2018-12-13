package util;
import java.text.MessageFormat;

/**
 * <br>类 名: TnsnamesoraUtil 
 * <br>描 述: 生成oracel数据源文件
 * <br>作 者: 王巍 
 * <br>创 建： 2015年3月10日 
 * <br>版 本：v1.2 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class TnsnamesoraUtil {
	public static void main(String[] args) {
		/*String[] strA = {"172.16.23.78&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote",
				"172.16.23.4&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote,uuzzcms/uuzzcms@uuzzcms",
				"172.16.23.68&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote",
				"10.0.3.195&sdcustomer/customer@sysman,sdtrade2/trade@tradenew,sdpayment/payment@paymentnew,promote/promote@promote",
				"172.16.23.3&sdpayment/payment@paymentnew,sdtrade/trade@tradenew",
				"172.16.23.235&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote",
				"10.0.1.55&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote",
				"172.16.23.145&sdpayment/payment@paymentnew,sdcustomer/customer@sysman,sdtrade2/trade@tradenew,promote/promote@promote"};
		*/
		// m6
		/*String[] strA = {"10.1.4.33&sdcustomer/customer@sysman,sdtrade/trade@tradenew",
				"10.1.4.44&sdpayment/payment@paymentnew"};*/
		String[] strA = {"172.16.15.97&sys/change_on_install@orcl,system/manager@orcl"};
		
		String serverTemplate = "{0}_{1}=(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST={2})(PORT=1521))(CONNECT_DATA=(SERVER=default)(SERVICE_NAME={3})))";
		MessageFormat serverFormat = new MessageFormat(serverTemplate); 
		
		String plsqlLinkTemplate = "{0}@{1}_{2}";
		MessageFormat plsqlLinkFormat = new MessageFormat(plsqlLinkTemplate); 
		
		for(String str: strA){
			 String[] dbInfos = str.split("&");
			 String ip = dbInfos[0];
			 String[] servers = dbInfos[1].split(",");
			 for(String servser : servers){
				 String[] servserInfos = servser.split("@");
				 String userPwd = servserInfos[0];
				 String sid = servserInfos[1];
				 
				// tnsnames.ora文件配置生成
				 String[] params1 = {sid,ip.split("\\.")[3],ip,sid};
				 String dbServiceConfig = serverFormat.format(params1);
				 System.out.println(dbServiceConfig);
				 
				 // plsql固定用户连接生成
				 String[] params2 = {userPwd,sid,ip.split("\\.")[3]};
				 String plsqlConfig = plsqlLinkFormat.format(params2);
				 System.out.println(plsqlConfig);
			 }
		 }
	}

}
