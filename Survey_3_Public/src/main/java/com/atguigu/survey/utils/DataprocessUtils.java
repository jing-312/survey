package com.atguigu.survey.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.LoopTagStatus;

import org.springframework.context.support.StaticApplicationContext;

import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.entities.manager.Role;
import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DataprocessUtils {
	
	/**
	 * MD5加密
	 * @param source 明文
	 * @return 密文
	 */
	public static String md5(String source) {
		
		if(source == null || source.length() == 0) {
			return source;
		}
		
		//1.准备工作
		char [] c = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuilder builder = new StringBuilder();
		
		//2.获取MessageDigest对象
		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		//3.获取源字符串的字节数组
		byte[] bytes = source.getBytes();
		
		//4.执行加密操作
		byte[] target = digest.digest(bytes);
		
		//5.遍历字节数组
		for (int i = 0; i < target.length; i++) {
			
			byte b = target[i];
			
			//6.取高四位和低四位值
			int high = (b >> 4) & 15;
			int low = b & 15;
			
			//7.获取对应字符
			char highChar = c[high];
			char lowChar = c[low];
			
			//8.拼装字符串
			builder.append(highChar).append(lowChar);
		}
		
		return builder.toString();
	}
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * @param inputStream
	 * @param realPath /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(InputStream inputStream, String realPath) {
		
		OutputStream out = null;
		
		try {
			//1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(inputStream);

			//2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			
			//3.计算目标图片的宽高值
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;
			
			if(sourceWidth > 100) {
				//按比例压缩目标图片的尺寸
				targetWidth = 100;
				targetHeight = sourceHeight/(sourceWidth/100);
				
			}
			
			//4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			
			//5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth, targetHeight, null);
			
			//6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			
			//7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath+"/"+targetFileName);
			
			//8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			
			//9.JPEG编码
			encoder.encode(targetImage);
			
			//10.返回文件名
			return "surveyLogos/"+targetFileName;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		} finally {
			//10.关闭流
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * 将选项数据转换为JSON字符串
	 * @param questionOption
	 * @return
	 */
	public static String optionsToJson(String questionOption) {
		
		if(questionOption == null) {
			return questionOption;
		}
		
		//根据换行进行拆分为数组
		String[] split = questionOption.split("\r\n");
		
		//转换为JSON字符串
		return new Gson().toJson(split);
	}
	
	/**
	 * 将JSON字符串还原为List
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> jsonToList(String json) {
		return new Gson().fromJson(json, List.class);
	}
	
	/**
	 * 将JSON数据恢复为带换行的字符串
	 * @param json
	 * @return
	 */
	public static String jsonToOptions(String json) {
		
		List<String> list = jsonToList(json);
		
		StringBuilder builder = new StringBuilder();
		
		for (String option : list) {
			builder.append(option).append("\r\n");
		}
		
		return builder.toString().trim();
	}
	
	/**
	 * 深度复制
	 * @param source 源对象
	 * @return 目标对象
	 */
	public static Serializable deeplyCopy(Serializable source) {
		
		if(source == null) {
			return null;
		}
		
		Serializable target = null;
		
		//1.声明四个流对象的变量
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		
		try{
			
			//2.创建字节数组输出流
			baos = new ByteArrayOutputStream();
			
			//3.根据字节数组输出流创建对象输出流
			oos = new ObjectOutputStream(baos);
			
			//4.执行序列化操作：将对象的序列化数据通过baos写入字节数组
			oos.writeObject(source);
			
			//5.通过baos获取字节数组
			byte[] byteArray = baos.toByteArray();
			
			//6.根据字节数组创建bais
			bais = new ByteArrayInputStream(byteArray);
			
			//7.根据字节数组输入流创建对象输入流
			ois = new ObjectInputStream(bais);
			
			//8.执行反序列化操作
			target = (Serializable) ois.readObject();
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return target;
	}
	
	/**
	 * 作用：决定参与调查页面上的input标签是否回显
	 * @param pageContext
	 * @return
	 * 	选择题：checked="checked"
	 * 	简答题：value="..."
	 */
	@SuppressWarnings("unchecked")
	public static String determineRedisplay(PageContext pageContext) {
		
		//1.获取HttpSession对象
		HttpSession session = pageContext.getSession();
		
		//2.从Session域中获取allBagMap
		Map<Integer,Map<String,String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) session.getAttribute(GlobaleNames.ALL_BAG_MAP);
		
		//3.根据bagId从allBagMap中获取parameterMap
		//①获取request对象
		ServletRequest request = pageContext.getRequest();
		
		//②从request对象中获取当前包裹
		Bag bag = (Bag) request.getAttribute(GlobaleNames.CURRENT_BAG);
		
		//③从bag对象中获取bagId
		Integer bagId = bag.getBagId();
		
		//④在通过bagId获取parameterMap
		Map<String, String[]> parameterMap = allBagMap.get(bagId);
		
		if(parameterMap == null) {
			return "";
		}
		
		//4.通过pageContext对象在四个属性域中以由小至大的顺序搜索当前Question对象
		Question question = (Question) pageContext.findAttribute("question");
		
		//5.通过Question对象或questionId
		Integer questionId = question.getQuestionId();
		
		//6.拼装当前标签的name属性值
		String inputName = "q" + questionId;
		
		//7.根据inputName从parameterMap中获取曾经提交过的请求参数数组
		String[] everValueArr = parameterMap.get(inputName);
		
		if(everValueArr == null) {
			return "";
		}
		
		//8.检测当前问题的题型
		Integer questionType = question.getQuestionType();
		
		if(questionType == 0 || questionType == 1) {
			
			//9.如果是选择题，检查当前标签的value值是否在everValueArr中
			//①获取optionStatus
			LoopTagStatus optionStatus = (LoopTagStatus) pageContext.findAttribute("optionStatus");
			
			//②获取当前标签的value值
			String currentValue = optionStatus.getIndex() + "";
			
			//③将everValueArr转换为List集合
			List<String> everValueList = Arrays.asList(everValueArr);
			
			//④判断everValueList集合中是否包含currentValue
			if(everValueList.contains(currentValue)) {
				
				//⑤如果包含，则说明可以回显
				return "checked='checked'";
				
			}
			
		}
		
		if(questionType == 2) {
			
			//10.如果是简答题，则获取曾经填写过的值直接使用
			//①获取everValueArr的第一个元素
			String everValue = everValueArr[0];
			
			//②拼装字符串返回
			return "value='"+everValue+"'";
			
		}
		
		//11.在不回显的情况下返回空字符串
		return "";
	}

	/**
	 * 根据字符串数组生成答案字符串
	 * @param value
	 * @return
	 */
	public static String generateAnswerContent(String[] value) {
		
		if(value == null) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < value.length; i++) {
			String v = value[i];
			builder.append(",").append(v);
		}
		
		return builder.substring(1);
	}

	/**
	 * 将问题id从'q23'这样的字符串中解析出来
	 * @param questionId
	 * @return
	 */
	public static Integer parseQuestionId(String key) {
		
		String questionIdStr = key.substring(1);
		
		return Integer.parseInt(questionIdStr);
	}
	
	/**
	 * 处理servletPath，去除后面附加的数据部分
	 * 当前项目的规范：/名称空间/模块名/操作名/参数/...
	 * @param servletPath
	 * @return
	 */
	public static String servletPathCut(String servletPath) {
		
		String[] split = servletPath.split("/");
		
		return "/"+split[1]+"/"+split[2]+"/"+split[3];
	}
	
	/**
	 * 根据深度加载的角色集合计算用户权限码数组
	 * @param roleSet
	 * @param maxPos 系统中最大的权限位的值，加1后就是权限码数组的长度
	 * @return
	 */
	public static String calculateCodeArr(Set<Role> roleSet, Integer maxPos) {
		
		//1.计算权限码数组的长度
		int length = maxPos + 1;
		
		//2.声明一个数组，用来保存计算得到的权限码数值
		int [] codeArr = new int[length];
		
		//3.逐层遍历
		for (Role role : roleSet) {
			
			Set<Auth> authSet = role.getAuthSet();
			
			for (Auth auth : authSet) {
				
				Set<Res> resSet = auth.getResSet();
				
				for (Res res : resSet) {
					
					//4.获取res对象的权限码
					Integer resCode = res.getResCode();
					
					//5.获取res对象的权限位
					Integer resPos = res.getResPos();
					
					//6.以权限位为下标从codeArr取出旧值
					int oldValue = codeArr[resPos];
					
					//7.将旧值和resCode做或运算，得到新值
					int newValue = oldValue | resCode;
					
					//8.将新值放回codeArr中原来的位置
					codeArr[resPos] = newValue;
					
				}
				
			}
			
		}
		
		//9.将codeArr转换为字符串
		return convertCodeArr2Str(codeArr);
	}
	
	/**
	 * 将权限码数组转换为逗号分隔的字符串
	 * @param codeArr
	 * @return
	 */
	public static String convertCodeArr2Str(int [] codeArr) {
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < codeArr.length; i++) {
			builder.append(",").append(codeArr[i]);
		}
		
		return builder.substring(1);
	}
	
	/**
	 * 权限验证工具方法
	 * @param codeArrStr
	 * @param res
	 * @return
	 */
	public static boolean checkAuthority(String codeArrStr, Res res) {
		
		//1.从res对象中获取权限码和权限位的值
		Integer resCode = res.getResCode();
		Integer resPos = res.getResPos();
		
		//2.将codeArrStr转换为数组
		String[] split = codeArrStr.split(",");
		
		//3.以resPos为下标从数组中取出元素
		String codeStr = split[resPos];
		
		//4.将codeStr转换为整型
		Integer code = Integer.parseInt(codeStr);
		
		//5.进行与运算
		int result = resCode & code;
		
		//6.结果非零时表示其有权限
		return result != 0;
	}
	/**
	 * 用来创建数据库的表的表名
	 * @param offset 偏移量
	 * -1：上一个月
	 * 0：本月
	 * 1：下一个月
	 * @return
	 */
	public static String generateTableName(int offset){
		
		String tableName = "AUTO_LOG_";
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.MONTH, offset);
		
		Date time = calendar.getTime();
		
		tableName = tableName + new SimpleDateFormat("yyyy_MM").format(time);
		
		return tableName;
	
		
	}
}
