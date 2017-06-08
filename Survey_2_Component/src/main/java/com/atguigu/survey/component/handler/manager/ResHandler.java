package com.atguigu.survey.component.handler.manager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.e.RemoveResFailedException;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.GlobaleMessage;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Controller
public class ResHandler {

	@Autowired
	private ResService resService;
	
	/**
	 * 瀹炵幇浜嗗璧勬簮鐘舵�佹洿鏀圭殑鍏蜂綋鎿嶄綔
	 * @param resId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manager/res/toggleResStatus")
	public Map<String, Object> toggleResStatus(@RequestParam("resId") Integer resId){
		
		String invokeResult = null;
		try {
			
			resService.updateStatus(resId);
			
			invokeResult ="success"; 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			invokeResult ="failed"; 
		}
		
		String statusResult = null;
		
		if("success".equals(invokeResult)){
			
			boolean publicStatus = resService.getResStatus(resId);
			
			statusResult = publicStatus ? "鍏叡璧勬簮" : "鍙椾繚鎶よ祫婧�";
		}
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("invokeResult",invokeResult);
		jsonMap.put("statusResult",statusResult);
		
		return jsonMap;
		
	}
	/**
	 * 瀹炵幇浜嗗叿浣撶殑鎵归噺鍒犻櫎鍔熻兘銆�
	 * @param resIdList
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/manager/res/batchDelete")
	public String batchDelete(@RequestParam(value="resIdList", required=false) List<Integer> resIdList) throws Exception {
		
		if(resIdList != null && resIdList.size() > 0) {
			try {
				resService.batchDelete(resIdList);
			} catch (Exception e) {
				Throwable cause = e.getCause();
				if(cause != null && cause instanceof MySQLIntegrityConstraintViolationException) {
					throw new RemoveResFailedException(GlobaleMessage.REMOVE_RES_FAILED);
				}
				throw e;
			}
		}
		return "redirect:/manager/res/showList";
	}
	/**
	 * 鐢ㄦ潵澶勭悊鏄剧ず璧勬簮鍒楄〃鐨勬柟娉曘��
	 * @param map
	 * @return
	 */
	@RequestMapping("/manager/res/showList")
	public String showList(Map<String, Object> map){
		
		List<Res> resList = resService.getResAll();
		
		map.put("resList", resList);
		
		return "manager/res_showList";
		
	}
}
