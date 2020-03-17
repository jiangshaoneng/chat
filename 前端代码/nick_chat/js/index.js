// websocket地址
var websocketUrl = "ws://127.0.0.1:8888/ws";
// 发送给后台的消息类别
var SEND_MSG_TYPE = {"connection":"connection", "normal":"normal"};
// 接收到的消息类别
var RECEIVED_MSG_TYPE = {"info":"info", "me":"me", "other":"other"};

var roomName = "myRoom";
var nickName = Math.round(Math.random()*10000);
var ws = null;

$(function(){
	// 打开webSocket
	openWebSocket();
	
	//监听发送消息事件
    $('#input-msg').bind('keyup', function(event) {
        if (event.keyCode == "13") {
           sendMsg();
        }
    });
})

 function openWebSocket(){
	if ("WebSocket" in window){
		ws = new WebSocket(websocketUrl);
		
		// 与后台简历连接,发送客户端nickName
		ws.onopen = function(){
			var message = createSendMsg(roomName, SEND_MSG_TYPE.connection, nickName, null);
			ws.send(message);
		};
		
		ws.onmessage = function (evt){
			// {"roomName":"xxx", "nickName":"xxx", "msg":"xxx", "type":"xxx","time":"xxxx"}
			var receivedMsg = JSON.parse(evt.data);
			if(receivedMsg.nickName == nickName){
				showReceivedMsg(receivedMsg.nickName, receivedMsg.msg, RECEIVED_MSG_TYPE.me);
			}else{
				showReceivedMsg(receivedMsg.nickName, receivedMsg.msg, receivedMsg.type);
			}
		};
		
		ws.onclose = function(){
			alert("连接已关闭...");
		};
		
	}else{
		// 浏览器不支持 WebSocket
		alert("您的浏览器不支持 WebSocket!");
	}
 }
 
 /**
  * 发送消息
  * */
 function sendMsg(){
	// 获取文本框中的消息
	var msg = $("#input-msg").val();
	// 组装成标准消息格式
	var message = createSendMsg(roomName,SEND_MSG_TYPE.normal,nickName,msg);
	console.log(message);
	// 发送
	ws.send(message);
	// 清空输入框
	$("#input-msg").val("");
 }
 
 /**
  * 创建一条发送给后台的标准消息格式,以json字符串格式返回
  * roomName:房间名称
  * type:消息类型 --> SEND_MSG_TYPE
  * nickName:发送者昵称
  * msg:消息内容
	*/
 function createSendMsg(roomName,type,nickName,msg){
	var message = new Object();
	message.roomName = roomName;
	message.type = type;
	message.nickName = nickName;
	message.msg = msg;
	return JSON.stringify(message);
 }
 
 /** 
  * 把消息显示在聊天界面中
  * nickName:消息昵称
  * msg:消息内容
  * type:接受到的消息类别 --> RECEIVED_MSG_TYPE
	*/
 function showReceivedMsg(nickName, msg, receivedType){	
	var html = "";
	html += "<div class='msg "+ receivedType +"'>";
	html += "<div class='nick'>"+ nickName +"</div>";
	html += "<div class='content'>"+ msg +"</div>";
	html += "<div class='clear'></div>";
	html += "</div>";
	// 追加到聊天框最后
	$("#msg-windows").append(html);
	scrolltoBottom();
 }
 
 /**
  * 滚动到浏览器底部
	*/
 function scrolltoBottom(){
	var docHeight = $(document).height();//获取页面文档的高度
	var winHeight = $(window).height();//获取当前窗体的高度
	var winScorllHeight = $(window).scrollTop();//获取滚动条滚动的距离
	if(docHeight > winHeight + winScorllHeight){
		window.scrollTo(0,docHeight-winHeight);
	}
 }