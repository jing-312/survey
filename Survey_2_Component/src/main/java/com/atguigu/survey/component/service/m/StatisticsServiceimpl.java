package com.atguigu.survey.component.service.m;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mappers.AnswerMapper;
import com.atguigu.survey.component.mappers.QuestionMapper;
import com.atguigu.survey.component.mappers.SurveyMapper;
import com.atguigu.survey.component.service.i.StatisticsService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.page.Page;

@Service
public class StatisticsServiceimpl implements StatisticsService {

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private QuestionMapper questionMapper;

	public Page<Survey> getAllAvailablePage(String pageNoStr) {
		// 1,先查询出来总记录数。
		int totalRecordNo = surveyMapper.getAllAvailableCount();
		// 2，新建分页对象。
		Page<Survey> page = new Page<Survey>(pageNoStr, totalRecordNo);
		// 3,获取分页的Sql的两个参数
		int index = page.getIndex();
		int pageSize = page.getPageSize();
		// 4,组装每次分页所要的list集合
		List<Survey> list = surveyMapper.getAllAvailableList(index, pageSize);

		page.setList(list);

		return page;
	}

	public Survey getSurveyDeeply(Integer surveyId) {

		return surveyMapper.getSurveyDeeply(surveyId);
	}

	public List<String> getListResult(Integer questionId) {

		return answerMapper.getListResult(questionId);
	}

	public JFreeChart getChart(Integer questionId) {

		// 1.准备数据
		// ①查询Question对象
		Question question = questionMapper.selectByPrimaryKey(questionId);
		String questionName = question.getQuestionName();
		List<String> optionList = question.getOptionList();

		// ②查询questionEngagedCount
		int questionEngagedCount = answerMapper.getQuestionEngagedCount(questionId);

		// ③创建dataset对象
		DefaultPieDataset dataset = new DefaultPieDataset();

		// ④遍历optionList
		for (int i = 0; i < optionList.size(); i++) {
			// [1]获取每一个option
			String option = optionList.get(i);
			// [2]拼装选项索引对应的参数值
			String indexParam = "%," + i + ",%";
			// [3]查询选项被选中的次数
			int optionEngagedCount = answerMapper.getOptionEngagedCount(questionId, indexParam);
			// [4]将数据设置到dataset对象中
			dataset.setValue(option, optionEngagedCount);
		}

		// ⑤组装图表title
		String title = questionName + "(" + questionEngagedCount + "次参与)";
		// 2.创建并修饰JFreeChart对象
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset);

		String fontName = "宋体";
		int style = Font.PLAIN;

		chart.getTitle().setFont(new Font(fontName, style, 50));
		chart.getLegend().setItemFont(new Font(fontName, style, 30));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font(fontName, style, 20));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0},{1}/{3},{2}"));

		plot.setForegroundAlpha(0.6f);

		return chart;
	}

	public HSSFWorkbook getWorkBook(Integer surveyId) {
		
		Survey survey = surveyMapper.getSurveyDeeply(surveyId);
		
		String surveyName = survey.getSurveyName();
		
		LinkedHashSet<Bag> bagSet = survey.getBagSet();
		
		List<Question> questionList = new ArrayList<Question>();
		
		for (Bag bag : bagSet) {
			
			LinkedHashSet<Question> questionSet = bag.getQuestionSet();
			
			questionList.addAll(questionSet);
		}
		
		int surveyEngagedCount = answerMapper.getSurveyEngagedCount(surveyId);
		
		List<Answer> answerList = answerMapper.getAnswerListBySurveyId(surveyId);
		
		Map<String, Map<Integer,String>> bigMap = generateBigMap(answerList);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		String sheetName = surveyName+"("+surveyEngagedCount +"次参与)";
		
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		HSSFRow row = sheet.createRow(0);
		
		for(int i=0; i<questionList.size();i++){
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(questionName);
		}
		
		if(surveyEngagedCount == 0){
			return workbook;
		}
		
		Collection<Map<Integer,String>> values = bigMap.values();
		List<Map<Integer,String>> smallMapList = new ArrayList<Map<Integer, String>>(values);
		for(int i = 0; i < smallMapList.size(); i++) {
			
			Map<Integer, String> smallMap = smallMapList.get(i);
			
			HSSFRow answerRow = sheet.createRow(i+1);
			
			for(int j = 0; j < questionList.size(); j++) {
				
				HSSFCell answerCell = answerRow.createCell(j);
				
				Question question = questionList.get(j);
				
				Integer questionId = question.getQuestionId();
				
				String content = smallMap.get(questionId);
				
				answerCell.setCellValue(content);
				
			}
			
		}
		return workbook;
	}

public Map<String, Map<Integer, String>> generateBigMap(List<Answer> answerList) {
		
		Map<String, Map<Integer,String>> bigMap = new HashMap<String, Map<Integer, String>>();
		
		for (Answer answer : answerList) {
			
			String answerUuid = answer.getAnswerUuid();
			Integer questionId = answer.getQuestionId();
			String answerContent = answer.getAnswerContent();
			
			//尝试从bigMap中获取smallMap
			Map<Integer, String> smallMap = bigMap.get(answerUuid);
			
			//判断smallMap是否为空
			if(smallMap == null) {
				
				//创建新的smallMap对象
				smallMap = new HashMap<Integer, String>();
				
				//存入bigMap
				bigMap.put(answerUuid, smallMap);
				
			}
			
			//将需要的数据保存到smallMap中
			smallMap.put(questionId, answerContent);
			
		}
		
		return bigMap;
	}
	
}
