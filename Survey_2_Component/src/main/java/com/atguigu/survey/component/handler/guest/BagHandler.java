package com.atguigu.survey.component.handler.guest;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.BagService;
import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.e.BagOrderDuplicateException;
import com.atguigu.survey.e.DeleteBagFailedException;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.GlobaleMessage;
import com.atguigu.survey.utils.GlobaleNames;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Controller
public class BagHandler {
	@Autowired
	private BagService bagService;
	
	@Autowired
	private SurveyService surveyService;
	
	@RequestMapping("/guest/bag/copyHere/{bagId}/{surveyId}")
	public String copyHere(@PathVariable("bagId") Integer bagId,
			@PathVariable("surveyId") Integer surveyId){
		
		bagService.updateRelationshipByCopy(bagId,surveyId);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	/**
	 * 完成包裹的移动的具体方法。
	 * @param bagId
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/bag/moveHere/{bagId}/{surveyId}")
	public String moveHere(@PathVariable("bagId") Integer bagId,
			@PathVariable("surveyId") Integer surveyId){
		
		bagService.updateRelationshipByMove(bagId,surveyId);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
		
	}
	/**
	 * 跳转到分页显示移动和复制的页面。
	 * @param bagId
	 * @param surveyId
	 * @param map
	 * @param session
	 * @param pageNoStr
	 * @return
	 */
	@RequestMapping("/guest/bag/toMoveOrCopePage/{bagId}/{surveyId}")
	public String toMoveOrCopePage(@PathVariable("bagId") Integer bagId,
					@PathVariable("surveyId") Integer surveyId,
					Map<String,Object> map,
					HttpSession session,
					@RequestParam(value="pageNostr", required=false) String pageNoStr){
		
		User user = (User)session.getAttribute(GlobaleNames.LOGIN_USER);
		
		Integer userId = user.getUserId();
		
		Page<Survey> page = surveyService.getMyUncompletedPage(userId, pageNoStr);
		
		map.put(GlobaleNames.PAGE,page);
		
		return "guest/bag_moveOrCopyPage";
	}
	/**
	 * 实现包裹的顺序调整的具体的方法；
	 * @param bagIdList
	 * @param bagOrderList
	 * @param surveyId
	 * @param request
	 * @return
	 */
	@RequestMapping("/guest/bag/doAdjust")
	public String doAdjust(
			@RequestParam("bagIdList") List<Integer> bagIdList,
			@RequestParam("bagOrderList") List<Integer> bagOrderList,
			@RequestParam("surveyId") Integer surveyId,
			HttpServletRequest request) {
		
		for(int i = 0; i < bagIdList.size(); i++) {
			Integer bagId = bagIdList.get(i);
			Integer bagOrder = bagOrderList.get(i);
			System.out.println(bagId+":"+bagOrder);
		}
		
		//检查包裹序号中是否存在重复的数据
		Set<Integer> bagOrderSet = new HashSet<Integer>(bagOrderList);
		if(bagOrderList.size() != bagOrderSet.size()) {
			List<Bag> bagList = bagService.getBagListBySurveyId(surveyId);
			request.setAttribute("bagList", bagList);
			request.setAttribute("surveyId", surveyId);
			throw new BagOrderDuplicateException(GlobaleMessage.BAG_ORDER_DUPLICATE);
		}
		
		bagService.batchUpdateBagOrder(bagIdList, bagOrderList);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	/**
	 * 前往调整包裹顺序方法
	 * @param surveyId
	 * @param map
	 * @return
	 */
	@RequestMapping("/guest/bag/toAdjustUI/{surveyId}")
	public String toAdjustUI(@PathVariable("surveyId") Integer surveyId,
				Map<String,Object> map){
		
		List<Bag> bagList = bagService.getBagListBySurveyId(surveyId);
		
		map.put("bagList", bagList);
		
		return "guest/bag_adjust";
	}
	/**
	 * 实现深度删除包裹的具体方法，也会安全删除的方法。
	 * @param bagId
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/bag/deeplyDelete/{bagId}/{surveyId}")
	public String deleteDeeplyBag(@PathVariable("bagId") Integer bagId,
				@PathVariable("surveyId") Integer surveyId){
		
		bagService.deleteDeeplyBag(bagId);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	/**
	 * 更新包裹具体的实现。
	 * @param bag
	 * @return
	 */
	@RequestMapping("/guest/bag/updateBag")
	public String updatebag(Bag bag){
		
		bagService.updateBagById(bag);
		
		return "redirect:/guest/survey/toDesignUI/"+bag.getSurveyId();
	}
	
	/**
	 * 跳转到更新包裹的页面，
	 * @param bagId
	 * @param surveyId
	 * @param map
	 * @return
	 */
	@RequestMapping("/guest/bag/deditBagUI/{bagId}/{surveyId}")
	public String toUpdateUI(@PathVariable("bagId") Integer bagId,
			@PathVariable("surveyId") Integer surveyId,Map<String,Object> map){
		
		Bag bag = bagService.selectBagById(bagId);
		
		map.put("bag",bag);
		
		return "guest/bag_editUI";
		
	}
	/**
	 * 完成删除包裹的具体方法。
	 * @param bagId
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/bag/deleteBag/{bagId}/{surveyId}")
	public String toDelateUI(@PathVariable("bagId") Integer bagId,
			@PathVariable("surveyId") Integer surveyId){
		
		try {
			bagService.deleteBag(bagId);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if(cause != null){
				
				if(cause instanceof MySQLIntegrityConstraintViolationException ){
					
					throw new DeleteBagFailedException(GlobaleMessage.DELETE_BAG_FAILED);
				}
			}
		}
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	/**
	 * 实现具体保存bag的具体方法。
	 * @param bag
	 * @return
	 */
	@RequestMapping("/guest/bag/saveBag")
	public String saveBag(Bag bag) {
		
		bagService.saveBag(bag);
		
		return "redirect:/guest/survey/toDesignUI/"+bag.getSurveyId();
	}
	
	/**
	 * 跳转到保存包裹页面。
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/bag/toAddUI/{surveyId}")
	public String toAddUI(@PathVariable("surveyId") Integer surveyId) {
		
		return "guest/bag_addUI";
	}
}
