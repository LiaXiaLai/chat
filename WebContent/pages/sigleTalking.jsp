<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${nickName}</title>

<style type="text/css">
	#receive{
		display:inline-block;
		width: 500px;
		height:300px;
		margin-left: auto;
		margin-right: auto;
		background-color: red;
	}
	#send{
		display:inline-block;
		width: 500px;
		height:100px;
		margin-left: auto;
		margin-right: auto;
		background-color: blue;
	}
	textarea {
		width: 400px;
		height: 93px;
	}
	button {
		width: 88px;
		text-align: 
	}
</style>
</head>
<body>
	<input type="hidden" value="${id }" id="id">
	<input type="hidden" value="${userId }" id="userId">
	<div id="receive">
		
	</div>
	<br/>
	<div id="send">
		<textarea rows="" cols="" id="sendM"></textarea>
		<button onclick="submit()">提交</button>
	</div>
</body>
<script type="text/javascript" src="/JTalking/js/jquery.min.js"></script>
<script type="text/javascript">
	var loadTime;
	$(function(){
		
		$.ajax({
			url:"/JTalking/talk/getSingleMessage",
			type:"post",
			data:{
				"id":$("#id").val(),
				"flg":true
			},
			dataType:"json",
			success:function(data){
				var userId = $("#userId").val();
				var list = data.data;
				for(var i =0;i<list.length;i++){
					if(list[i].fromUserId==userId){
						var html = "<div style='text-align:right;background-color:green'>"+list[i].content+"</div>"
					}else{
						var html = "<div style='text-align:left;background-color:white'>"+list[i].content+"</div>"
					}
					$("#receive").append(html);
				}
				loadTime = new Date().getTime();
				
				//第一次请求成功之后,每个一秒钟刷新一次聊天记录
				
				setInterval(function(){
					loadMessage();
				}, 1000)
			}
		})
	})
	
	//提交聊天内容
	function submit(){
		var sendM = $("#sendM").val();
		var html = "<div style='text-align:right;background-color:green'>"+sendM+"</div>"
		$("#receive").append(html);
		$("#sendM").val("");
		$.ajax({
			url:"/JTalking/talk/sendSingleMessage",
			type:"post",
			data:{
				"id":$("#id").val(),
				"content":sendM,
				"createTime":new Date().getTime()
			},
			dataType:"json",
			success:function(data){
				
				
			}
		})
	}

	//刷新对面的聊天信息
	function loadMessage(){
		
		$.ajax({
			url:"/JTalking/talk/getSingleMessage",
			type:"post",
			data:{
				"id":$("#id").val(),			
				"createTime":loadTime
			},
			dataType:"json",
			success:function(data){
				
				loadTime = new Date().getTime();
				
				var list = data.data;
				for(var i =0;i<list.length;i++){
					
					var html = "<div style='text-align:left;background-color:white'>"+list[i].content+"</div>"
					
					$("#receive").append(html);
				}
			}
		})
	}
	
	
</script>
</html>