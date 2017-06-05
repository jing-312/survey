package com.atguigu.survey.component.handler.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.StatisticsService;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;
import com.atguigu.survey.utils.GlobaleNames;

@Controller
public class StatisticsHandler {

	@Autowired
	private StatisticsService statisticsService;
	@RequestMapping("/manager/statistics/exportExcel/{surveyId}")
	public void exportExcel(@PathVariable("surveyId") Integer surveyId,
			HttpServletResponse response) throws IOException{
		HSSFWorkbook workBook = statisticsService.getWorkBook(surveyId);
		
		response.setContentType("application/vnd.ms-excel");
		
		String fileName = System.nanoTime() + ".xls";
		
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		workBook.write(outputStream);
		
	}
	@RequestMapping("/manager/statistics/generateChartResult/{questionId}")
	public void generateChartResult(@PathVariable("questionId") Integer questionId,
			HttpServletResponse response ) throws IOException{
		JFreeChart chart = statisticsService.getChart(questionId);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		ChartUtilities.writeChartAsJPEG(outputStream, chart, 1200, 600);
	}
	/**
	 * 以列表形式显示答案的列表。
	 * @param questionId
	 * @param map
	 * @return
	 */
	@RequestMapping("/manager/statistics/showList/{questionId}")
	public String listResult(@PathVariable("questionId")Integer questionId,Map<String,Object> map){
		
		List<String> listResult= statisticsService.getListResult(questionId);
		
		map.put("listResult",listResult);
		
		return "manager/statistics_listResult";
		
	}
	@RequestMapping("/manager/statistics/showSummary/{surveyId}")
	public String showSummary(@PathVariable("surveyId")Integer surveyId,Map<String,Object> map){
		
		Survey survey = statisticsService.getSurveyDeeply(surveyId);
		
		map.put("survey", survey);
		
		return "manager/statistics_summary";
		
	}
	/**
	 * 用来展示所有的调查的结果。
	 * @return
	 */
	@RequestMapping("/manager/statitics/showAllAvailable")
	public String showAllAvailable(
				@RequestParam(value="pageNoStr",required=false) String pageNoStr,
				Map<String,Object> map){
		
		Page<Survey> page = statisticsService.getAllAvailablePage(pageNoStr);
		
		map.put(GlobaleNames.PAGE,page);
		
		return "manager/statistics_showAllAvailable";
		
	}
}
