<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/res_jsp/base.jsp" %>

<script type="text/javascript">
	$(function(){
		
		$("#optionDiv").hide();
		
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
			<h4>[新建问题页面]</h4>
			<div class="container">
				<form action="guest/question/saveQuestion" method="post" class="form-horizontal">
					<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
					<input type="hidden" name="bagId" value="${requestScope.bagId}"/>
					
					<div class="form-group">
						<label for="questionNameInputId" class="col-lg-2 control-label">问题名称</label>
						<div class="col-lg-10">
							<input id="questionNameInputId" 
								   class="form-control" 
								   type="text" 
								   name="questionName"
								   placeholder="请输入问题名称"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-2 control-label">选择题型</label>
						<div class="col-lg-10">
							<input type="radio" name="questionType" value="0" id="type01"/>
							<label for="type01">单选题</label>
							
							<input type="radio" name="questionType" value="1" id="type02"/>
							<label for="type02">多选题</label>
							
							<input type="radio" name="questionType" value="2" id="type03"/>
							<label for="type03">简答题</label>
						</div>
					</div>
					
					<div class="form-group" id="optionDiv">
						<label for="questionOptionInputId" class="col-lg-2 control-label">选项</label>
						<div class="col-lg-10">
							<textarea   rows="10"
										id="questionOptionInputId"
										name="questionOption"
										class="form-control"
										placeholder="请输入选项，各个选项之间用换行分隔"
										></textarea>
						</div>
					</div>
					
					<div style="text-align: center;">
						<button type="submit" class="btn btn-default" style="width: 30%">保存</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>