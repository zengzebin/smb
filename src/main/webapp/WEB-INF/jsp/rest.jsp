<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>REST 示例</title>
<script type="text/javascript" >
     var ctx='${pageContext.request.contextPath}';
</script>
</head>
<body>
这是从CONTROLLER定向过来的一个页面。本页面演示了HTTP REST相关的东东。<br>
${msg}
<br><br><br><br><br><br>
<form id="form1" >
写点内容（可用中文）<input id="txt1" type="text" />
<button id="btnPost1" type="button" >执行REST POST</button>
</form>
<br><br>
<form id="form2" >
写点内容（可用中文）
<input id="txt2" type="text" />
<input id="txt2hid" type="hidden" value="2325" />
<button id="btnPost2" type="button" >执行REST PUT</button>
</form>
<br><br><br>
<br><br><br>让哥见识一下js的跨域问题：<br><br>
<form id="form3" >
写点内容（可用中文）<input id="txt3" type="text" />
<button id="btnPost3" type="button" >执行REST POST(跨域)</button>
</form>
<br><br><br>
客串：<br>
<button id="btn4" type="button" >调一下“非REST”的</button>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.3.min.js" ></script>
<script type="text/javascript">
//     headers: { Authorization: "Basic " + Base64.encode(username + ":" + password) },
	$(function () {
		$('#btnPost1').click(function () {
		    var txt1 = $('#txt1').val();
		    $.ajax({
		        type: "POST",
		        url: ctx+"/rs/obj?nowis="+new Date().getTime(),
		        data: {
		        	txt1:txt1
		        },
		        success:function(dt){
		        	alert(dt);
// 		        	alert("good");
// 		            window.location.href="mng.html";
		        }
		    });
		});
		
		$('#btnPost2').click(function () {
		    var txt = $('#txt2').val();
		    var idhid = $('#txt2hid').val();
		    $.ajax({
		        type: "PUT",
		        url: ctx+"/rs/obj/"+idhid,
		        data: {
		        	txt1:txt
		        },
		        success:function(dt){
		        	alert(dt);
// 		        	alert("good");
// 		            window.location.href="mng.html";
		        }
		    });
		});	
		
		//注意下面的url调了另外一个主机端口的资源
		$('#btnPost3').click(function () {
		    var txt = $('#txt3').val();
		    var ctx2="http://172.16.5.28:8082/smb";
		    $.ajax({
		        type: "POST",
		        url: ctx2+"/rs/obj?nowis="+new Date().getTime(),
		        data: {
		        	txt1:txt
		        },
		        success:function(dt){
		        	alert(dt);
		        },
		        error:function(){
		        	alert('error，跨域调用没返回东西。见识了吧！\n被访问域：'+ctx2);
		        }
		    });
		});
		
		$('#btn4').click(function () {
		    $.ajax({
		        type: "GET",
		        url: ctx+"/admin",
		        data: {
		        },
		        success:function(dt){
		        	alert(dt);
		        }
		    });
		});		
		
	});
	</script>	
</body>
</html>