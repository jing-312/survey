package com.atguigu.survey.component.handler.guest;


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
	 * 实现了对资源状态更改的具体操作
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
			
			statusResult = publicStatus ? "公共资源" : "受保护资源";
		}
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("invokeResult",invokeResult);
		jsonMap.put("statusResult",statusResult);
		
		return jsonMap;
		
	}
	/**
	 * 实现了具体的批量删除功能。
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
	 * 用来处理显示资源列表的方法。
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
