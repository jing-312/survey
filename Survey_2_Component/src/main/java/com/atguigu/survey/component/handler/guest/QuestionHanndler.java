package com.atguigu.survey.component.handler.guest;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.QuestionService;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.utils.DataprocessUtils;

@Controller
public class QuestionHanndler {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/guest/question/updateQuestion")
	public String updateuestion(@RequestParam("surveyId") Integer surveyId,Question question){
		
		String questionOption = question.getQuestionOption();
		String optionsToJson = DataprocessUtils.optionsToJson(questionOption);
		question.setQuestionOption(optionsToJson);
		
		questionService.updateQuestion(question);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	/**
	 * 此方法实现了跳转页面和查询数据回显功能。
	 * @param surveyId
	 * @param questionId
	 * @param map
	 * @return
	 */
	@RequestMapping("/guest/question/toEditUI/{surveyId}/{questionId}")
	public String toEditUI(@PathVariable ("surveyId") Integer surveyId,
				@PathVariable("questionId") Integer questionId,
				Map<String,Object> map){
		
		Question question = questionService.seleteQuestion(questionId);
		
		String questionOption = question.getQuestionOption();
		String optionsToJson = DataprocessUtils.optionsToJson(questionOption);
		question.setQuestionOption(optionsToJson);
		
		map.put("question",question);
		
		//次map用于生成选择框按钮。
		Map<String,String> radioMap = new HashMap<String,String>();
		radioMap.put("0","单选题");
		radioMap.put("1","多选题");
		radioMap.put("2","简答题");
		map.put("radioMap", radioMap);
		
		return "guest/question_editUI";
	}
	
	/**
	 * 用来实现删除问题的具体的方法。
	 * @param surveyId
	 * @param questionId
	 * @return
	 */
	@RequestMapping("/guest/question/deleteQuestion/{surveyId}/{questionId}")
	public String deleteQuestion(@PathVariable("surveyId") Integer surveyId,
			@PathVariable("questionId") Integer questionId ){
		
		questionService.deleteQuestion(questionId);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	/**
	 * 保存问题的具体实现方法。
	 * @param question
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/question/saveQuestion")
	public String savaQuestion(Question question,@RequestParam("surveyId") Integer surveyId){
		
		String questionOption = question.getQuestionOption();
		
		String Json = DataprocessUtils.optionsToJson(questionOption);
		
		question.setQuestionOption(Json);
		
		questionService.saveQuestion(question);
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	
	@RequestMapping("/guest/question/toAddUI/{surveyId}/{bagId}")
	public String toQuestionUI(@PathVariable("surveyId") Integer surveyId,
				@PathVariable("bagId") Integer bagId){
		
		
		return "guest/question_addUI";
	}
}
