<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友列表</title>
<style type="text/css">
	#friend_list{
		width: 300px;
		height: 500px;
		background-color: #f7f6f6;
		overflow-y: scroll;
		display: inline-block;
	}
	#search_friends_list{
		width: 500px;
		height:500px;
		background-color: red;
	}
	td{
		text-align:right;
	}
	.search{
		display: none;
	}
	li{
		list-style-type: none;
		font-size: 18px;
		margin-right: 20px;
		margin-top: 5px;	
		cursor: pointer;
	}
</style>
</head>
<body>

	<div id="message"></div>
	<table>
		<tr>
			<td>
				<button id="toSearch" onclick="toSearch()">搜索好友</button> 
			</td>
			<td class="search">
				<input id="nickName"> <button onclick="searcFriendsByNickName()">搜索</button>
			</td>
		</tr>
		<tr>
			<td>
				<div id="friend_list">
				</div>
			</td>
			<td class="search">
				<div id="search_friends_list"></div>
			</td>
		
		</tr>
	</table>
</body>
<script type="text/javascript" src="/JTalking/js/jquery.min.js"></script>
<script type="text/javascript">
	var flg = true;
	function toSearch(){
		if(flg){
			$(".search").show();
			$("#toSearch").text("收起");
			flg = false;
		}else{
			$(".search").hide();
			$("#toSearch").text("搜索好友");
			flg = true;
		}
	}
	//页面刷新完成执行
	$(function(){
		loadFriends();
	})
	
	function loadFriends(){
		$.ajax({
			url:"/JTalking/user/getFriends",
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					$("#message").text(data.message);
				}else{
					var list = data.data;
					
					for(var i = 0;i<list.length;i++){
						var str = "<li ><a href='/JTalking/talk/toSigleTalkingPage?id="+list[i].id+"&nickName="+list[i].nickName+"' target='_blank'>"+list[i].nickName+"</a></li>";
						$("#friend_list").append(str);
					}
				}
			}
		});
	}
	
	function searcFriendsByNickName(){
		var nickName = $("#nickName").val();
		
		if(nickName==""||nickName==undefined){
			$("#message").text("昵称不能为空");
			return;
		}
		$.ajax({
			url:"/JTalking/user/searchFriendsByNickName",
			type:"post",
			data:{"nickName":nickName},
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					
				}else{
					var list = data.data;
					var lis = "";
					
					for(var i = 0;i<list.length;i++){
						lis+= "<div>"+list[i].nickName+"<button onclick='addFriend("+list[i].id+")'>添加</button></div>";
					}
					$("#search_friends_list").html(lis);
				}
			}
		});
		
		
	}
	function addFriend(id){
		$.ajax({
			url:"/JTalking/user/addFriend",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data.code==-1){
					$("#message").text(data.message);
				}else{
					$("#friend_list").html("");
					$("#message").text("");
					loadFriends();
				}
			}
		});
		
		
		/* setInterval(function(){
			
		}, 1000); */
	}
</script>

</html>