package com.atguigu.survey.component.handler.guest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.e.DeleteSurveyFailedException;
import com.atguigu.survey.e.FileTooLargeSaveException;
import com.atguigu.survey.e.FileTypeInvalidSaveException;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.DataprocessUtils;
import com.atguigu.survey.utils.GlobaleMessage;
import com.atguigu.survey.utils.GlobaleNames;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Controller
public class SurveyHandler {
	
	@Autowired
	private SurveyService surveyService;
	
	
	@RequestMapping("/guset/survey/deleteDpeelySurvey/{surveyId}")
	public String deleteDeeplySurvey(@PathVariable("surveyId") Integer surveyId){
		
		surveyService.deleteDeeplySurvey(surveyId);
		
		return "redirect:/guest/survey/showMyUncompleted";
		
	}
	
	
	/**
	 * 完成调查的具体实现过程。
	 * @param surveyId
	 * @return
	 */
	@RequestMapping("/guest/survey/complete/{surveyId}")
	public String complete(@PathVariable("surveyId") Integer surveyId){
		
		surveyService.updateCompleteStatus(surveyId);
		
		return "redirect:/index.jsp";
	}
	/**
	 * 携带数据跳转到设计页面。
	 * @param surveyId
	 * @param map
	 * @return
	 */
	@RequestMapping("/guest/survey/toDesignUI/{surveyId}")
	public String toDesignUI(
					@PathVariable("surveyId") Integer surveyId,
					Map<String ,Object>map){
		Survey survey = surveyService.getSurveyDeeply(surveyId);
		
		map.put("survey", survey);
		
		return "guest/survey_design";
		
	}
	/**
	 * 实现了具体的更新调查的具体方法。
	 * @param survey
	 * @param logoFile
	 * @param pageNo
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/guest/survey/updateSurvey")
	public String updateSurvey(Survey survey,
							   @RequestParam("logoFile") MultipartFile logoFile,
							   @RequestParam("pageNo") Integer pageNo,
							   HttpSession session,
							   HttpServletRequest request
							   ) throws IOException{
		boolean empty = logoFile.isEmpty();
		
		if(!empty){
			
			if(logoFile.getSize() > 100*1024){
				request.setAttribute("survey",survey);
				request.setAttribute("pageNo",pageNo);
				throw new FileTooLargeSaveException(GlobaleMessage.FILE_TOO_LARGE);
			}
			
			if(!logoFile.getContentType().startsWith("image/")){
				request.setAttribute("survey",survey);
				request.setAttribute("pageNo",pageNo);
				throw new FileTypeInvalidSaveException(GlobaleMessage.FILE_TYPE_INVALID);
			}
			
			String virtualPath = "/surveyLogos";
			
			ServletContext servletContext = session.getServletContext();
			
			String realPath = servletContext.getRealPath(virtualPath);
			
			InputStream inputStream = logoFile.getInputStream();
			
			String logoPath = DataprocessUtils.resizeImages(inputStream, realPath);
			
			survey.setLogoPath(logoPath);
		}
		
		surveyService.updateSurvey(survey);
		
		return "redirect:/guest/survey/showMyUncompleted?pageNoStr="+pageNo;
	}
	/**
	 * 跳转到编辑页面，并能够回显想要修改的调查信息。
	 * @param surveyId
	 * @param map
	 * @return
	 */
	@RequestMapping("/guest/survey/toEditUI/{surveyId}/{pageNo}")
	public String editSurvey(@PathVariable("surveyId") Integer surveyId,
			@PathVariable("pageNo") Integer pageNo,
			Map<String,Object> map){
		
		Survey survey = surveyService.selectSurvey(surveyId);
		
		map.put("survey", survey);
		
		return "guest/survey_editUI";
	}
	/**
	 * 删除survey的具体实现方法。
	 * @param surveyId
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/guest/survey/deleteSurvey/{surveyId}/{pageNo}")
	public String deleteSurvey(@PathVariable("surveyId") Integer surveyId,
							   @PathVariable("pageNo") Integer pageNo ){
		
		try {
			surveyService.deleteSurvey(surveyId);
		} catch (Exception e) {
			e.printStackTrace();
			//获取异造成异常的原因。
			Throwable cause = e.getCause();
			//检查原因是否存在。
			if (cause != null){
				if(cause instanceof MySQLIntegrityConstraintViolationException){
					
					throw new DeleteSurveyFailedException(GlobaleMessage.DELETE_SURVEY_FAILED);
					
				}
			}
		}

		
		return "redirect:/guest/survey/showMyUncompleted?pageNoStr="+pageNo;
	}
	
	/**
	 * 用来处理分页显示的功能的具体Handler方法
	 * @param session
	 * @param map
	 * @param pageNoStr
	 * @return
	 */
	@RequestMapping("/guest/survey/showMyUncompleted")
	public String showMyUncompleted(
			HttpSession session,
			Map<String,Object> map,
			@RequestParam(value="pageNoStr", required=false) String pageNoStr){
		
		User user = (User)session.getAttribute(GlobaleNames.LOGIN_USER);
		Integer userId = user.getUserId();
		
		
		Page<Survey> page = surveyService.getMyUncompletedPage(userId,pageNoStr); 
		
		map.put(GlobaleNames.PAGE, page);
		
		return "guest/showMyUncompleted";
	}
	/**
	 * 用来完成调查创建的方法，
	 * @param logoFile
	 * @param survey
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/guest/survey/saveSurvey")
	public String saveSurvey(
			@RequestParam("logoFile") MultipartFile logoFile,
			Survey survey,
			HttpSession session) throws IOException{
		//判断用户是否上传文件。
		boolean empty = logoFile.isEmpty();
		//如果用户上传文件不为空的 情况下进行下列操作。
		if(!empty){
			
			if(logoFile.getSize()>100*1024){
				throw new FileTooLargeSaveException(GlobaleMessage.FILE_TOO_LARGE);
			}
			
			if(! logoFile.getContentType().startsWith("image/")){
				throw new FileTypeInvalidSaveException(GlobaleMessage.FILE_TYPE_INVALID);
			}
			
			InputStream inputStream = logoFile.getInputStream();
			
			String virtualPath = "/surveyLogos";
			
			String realPath = session.getServletContext().getRealPath(virtualPath);
			
			String logoPath = DataprocessUtils.resizeImages(inputStream, realPath);
			
			survey.setLogoPath(logoPath);
		}
		
		User user = (User)session.getAttribute(GlobaleNames.LOGIN_USER);
		
		survey.setUserId(user.getUserId());
		
		surveyService.saveSurvey(survey);
		
		return "redirect:/guest/survey/showMyUncompleted";
				/*"redirect:guest/showMyUncompleted";*/
	}
	
}
