package com.atguigu.survey.component.handler.guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.EngageService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class EngageHandler {

	@Autowired
	private EngageService  engageService;
	
	/**
	 * 参与调查的包裹导航的实现。
	 * @param currentIndex
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/guest/engage/engage")
	public String engage(
			@RequestParam("currentIndex") Integer currentIndex,
			HttpServletRequest request,
			HttpSession session){
		
		Map<String,String[]> parameterMap = request.getParameterMap();
		
		List<Bag> bagList = (List<Bag>) session.getAttribute(GlobaleNames.BAG_LIST);
		
		Integer bagId = bagList.get(currentIndex).getBagId();
		
		Map<Integer,Map<String,String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) session.getAttribute(GlobaleNames.ALL_BAG_MAP);
		
		Map<String,String[]> newMap= new HashMap<String,String[]>(parameterMap);
		
		allBagMap.put(bagId, newMap);
		
		Integer newIndex = null;
		
		if(parameterMap.containsKey("submitPrev")) {
			newIndex = currentIndex - 1;
		}

		if(parameterMap.containsKey("submitNext")) {
			newIndex = currentIndex + 1;
		}
		
		if(parameterMap.containsKey("submitPrev") || parameterMap.containsKey("submitNext")) {
			//如果把这个放到if判断外面，会导致空指针异常。
			Bag bag = bagList.get(newIndex);
			
			request.setAttribute(GlobaleNames.CURRENT_BAG, bag);
			
			request.setAttribute(GlobaleNames.CURRENT_INDEX, newIndex);
			
			return "guest/engage_engage";
		}
		if(parameterMap.containsKey("submitDone")) {
			//解析并保存答案...
			Survey survey = (Survey) session.getAttribute(GlobaleNames.CURRENT_SURVEY);
			
			Integer surveyId = survey.getSurveyId();
			
			engageService.saveAfterParseAnswers(allBagMap,surveyId);
			
		}

		session.removeAttribute(GlobaleNames.ALL_BAG_MAP);
		session.removeAttribute(GlobaleNames.BAG_LIST);
		session.removeAttribute(GlobaleNames.CURRENT_SURVEY);
		session.removeAttribute(GlobaleNames.LAST_INDEX);
		
		return "redirect:/index.jsp";
	}

	/**
	 * 实现跳转到参与调查页面，并准备好数据显示。
	 * @param surveyId
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/guest/engage/entry/{surveyId}")
	public String entry(
			@PathVariable("surveyId") Integer surveyId,
			HttpSession session,
			HttpServletRequest request){
		
		Survey sruvey = engageService.getSurveyById(surveyId);
		
		LinkedHashSet<Bag> bagSet = sruvey.getBagSet();
		
		List<Bag> bagList = new ArrayList<Bag>(bagSet);
		
		Map<Integer,Map<String,String[]>> allBagMap = new HashMap<Integer, Map<String,String[]>>();
		
		int lastIndex = bagList.size()-1;
		
		session.setAttribute(GlobaleNames.CURRENT_SURVEY,sruvey);
		
		session.setAttribute(GlobaleNames.BAG_LIST,bagList);
		
		session.setAttribute(GlobaleNames.ALL_BAG_MAP,allBagMap);
		
		session.setAttribute(GlobaleNames.LAST_INDEX,lastIndex);
		
		int currentIndex = 0;
		
		Bag bag = bagList.get(currentIndex);
		
		request.setAttribute(GlobaleNames.CURRENT_BAG,bag);
		
		request.setAttribute(GlobaleNames.CURRENT_INDEX,currentIndex);
		
		return "guest/engage_engage";
	}
	
	
	/**
	 * 显示已经完成的调查。
	 * @param map
	 * @param pageNoStr
	 * @return
	 */
	@RequestMapping("/guest/engage/showAllAvailable")
	public String showAllAvailable(Map<String,Object> map,
			@RequestParam(value="pageNoStr", required=false) String pageNoStr){
		
		Page<Survey> page = engageService.getAllAvailable(pageNoStr);
		
		map.put(GlobaleNames.PAGE, page);
		
		return "guest/engage_allAvailable";
	}
}
