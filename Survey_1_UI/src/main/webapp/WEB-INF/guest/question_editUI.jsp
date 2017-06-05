<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
	$(function(){
		var initType = $(":radio:checked").val();
		
		if(initType == 2){
			
		$("#optionDiv").hide();
			
		}
		
		$(":radio").click(function(){
			
		var type = this.value;
			
		if(type == 2){
			$("#optionDiv").hide();
		}
		
		if(type == 0 || type ==1){
			$("#optionDiv").show();
		}
		});
		
		
		$("textarea").change(function(){
			var option = $("textarea").val();
			option =$.trim(option);
			var rge = /\s*\n\s*/g;
			option = option.replace(rge, "\n");
			this.value = option;
		});
	});
</script>

<body>

	<div class="container">
		<%@include file="/res_jsp/guest_top.jsp" %>
		
		<!-- 巨幕 -->
		<div class="jumbotron">
			<h4>[更新问题页面]</h4>
			<div class="container">
				<form:form action="guest/question/updateQuestion" method="post" cssClass="form-horizontal" modelAttribute="question">>
					<input  type="hidden" name="surveyId" value="${requestScope.surveyId}"/>
					<form:hidden path="questionId"/>
					<div class="form-group">
						<label for="questionNameInputId" class="col-lg-2 control-label" >问题名称</label>
						<div class="col-lg-10">
							<form:input id="questionNameInputId" 
										path="questionName" 
										cssClass="form-control"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">选择题型</label>
						<div class="form-group">
							<form:radiobuttons path="questionType" items="${requestScope.radioMap }"/>
						</div>
					</div>
					
					 <div id="optionDiv" class="form-group">
			        
			        	<label for="questionOptionInputId" class="col-lg-2 control-label">选项</label>
			        	<div class="col-lg-10">
			        		<form:textarea  id="questionOptionInputId" 
			        						path="questionOption" 
			        						cssClass="form-control" 
			        						rows="10"/>
			        	</div>
			        </div>
			         <div style="text-align: center;">
			            <button type="submit" class="btn btn-default" style="width: 30%">更新</button>
			        </div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>