<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
<style type="text/css">
	#register{
		display: none;
	}
	
	div{
		width: 300px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 60px;
	}
	input{
		margin-top: 10px;
		margin-bottom: 10px;
	}
	button {
		width: 180px;
	}
	a{
		margin-left: 20px;
	}
</style>
</head>
<body>	<div id="message"></div>
		<div id="login">
			用&ensp;户&ensp;名:<input id="l_userName">	<br/>
			密&ensp;&ensp;&ensp;&ensp;码 <input id="l_password" type="password"> <br/>
			<button onclick="login()">登录</button> <a href="javascript:void(0)" onclick="toRegister()">注册</a>
		</div>
		
		<div id="register">
			用&ensp;户&ensp;名:<input id="r_userName" onblur="checkUserName()">	<br/>
			密&ensp;&ensp;&ensp;&ensp;码 <input id="r_password" type="password"> <br/>
			重复密码:<input id="r_rePassword" type="password"> <br/>
			昵&ensp;&ensp;&ensp;&ensp;称:<input id="nickName"><br/>
			<button onclick="register()" id="r_register">注册</button> <a href="javascript:void(0)" onclick="backLogin()">返回</a>
		</div>
		
</body>
<script type="text/javascript" src="/JTalking/js/jquery.min.js"></script>
<script type="text/javascript">
	//切换到注册页
	function toRegister(){
		$("#login").hide(200);
		$("#register").show(200);
		$("#message").text("");
	}
	//切换到登录页
	function backLogin(){
		$("#register").hide(200);
		$("#login").show(200);
		$("#message").text("");
	}
	//检测用户名
	function checkUserName(){
		var userName = $("#r_userName").val();
		if(userName=="" || userName==undefined){
			$("#message").text("用户名不能为空");
			return;
		}
		$.ajax({
			url:"/JTalking/user/checkUserName",
			type:"post",
			data:{"userName":userName},
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					$("#r_register").attr("disabled",true);
				}else{
					$("#r_register").attr("disabled",false);
				}
				$("#message").text(data.message);
			}
		});
	}
	
	function register(){
		var userName = $("#r_userName").val();
		
		var password = $("#r_password").val();
		var rePassword = $("#r_rePassword").val();
		
		if(password!=rePassword){
			$("#message").text("两次输入的密码不一致,请重新输入");
			return ;
		}
		
		var nickName = $("#nickName").val();
		
		if(nickName==""||nickName==undefined){
			$("#message").text("昵称不能为空");
			return;
		}
		
		$.ajax({
			url:"/JTalking/user/register",
			type:"post",
			data:{
					"userName":userName,
					"password":password,
					"nickName":nickName
				},
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					$("#message").text(data.message);
				}else{
					backLogin();
				}
			}
		});
	}
	
	function login(){
		var userName = $("#l_userName").val();
		
		if(userName==""||userName==undefined){
			$("#message").text("用户名都不写,登录啥!");
			return;
		}
		
		var password = $("#l_password").val();
		if(password==""||password==undefined){
			$("#message").text("您的密码没写");
			return;
		}
		
		$.ajax({
			url:"/JTalking/user/login",
			type:"post",
			data:{
					"userName":userName,
					"password":password
				},
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					$("#message").text(data.message);
				}else{
					location.href="/JTalking/pages/friends.jsp";
				}
			}
		});
		
	}
</script>
</html>