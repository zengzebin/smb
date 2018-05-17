<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--     <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" > -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="shortcut icon" href="../../assets/ico/favicon.ico"> -->

    <title>登录模板 Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="3.3.4/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" >
         var ctx='${pageContext.request.contextPath}';
    </script>
    
  </head>

  <body>

    <div class="container">
      <form class="form-signin" role="form" >
        <h2 class="form-signin-heading">Please sign in</h2>
	        <input id="name" type="text" class="form-control" placeholder="登录ID" required autofocus>
	        <input id="pass" type="password" class="form-control" placeholder="Password 密码" required>
	        <label class="checkbox">
	          <input type="checkbox" value="remember-me"> 下次自动登录
	        </label>
        <button id="login" class="btn btn-lg btn-primary btn-block" type="button" >登录</button>
      </form>

    </div> <!-- /container -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.3.min.js" ></script>
	<script type="text/javascript">
	function startOfDay(dt) {
	    dt = dt.replace(/-/g, "/");
	    return new Date(dt).Format("yyyy-MM-dd 00:00:00");
	}
   //  var time1 = new Date().format("yyyy-MM-dd HH:mm:ss");  
     startOfDay('2013-06-06');
//     headers: { Authorization: "Basic " + Base64.encode(username + ":" + password) },
	$(function () {
		$('#login').click(function () {
		    var username = $('#name').val();
		    var password = $('#pass').val();
		    if(username==''){
		    	alert("用户名必填！");
		    	$('#name').focus();
		    	return;
		    }
		    alert(username+password);
            var geturl = $.ajax({
                type: "POST",
                url: ctx+"/low",
                data: { name: username,psw:password }
            }).done(function (data) {
//                 $.cookie("username", username, { expires: 365 });
                alert("登录成功！");
//                 if (remeber == true)
//                     $.cookie("sessionid", data.Id, { expires: data.ExpriesIn / 3600 / 24 });
//                 else
//                     $.cookie("sessionid", data.Id);
                //$.cookie("allow_types", geturl.getResponseHeader("Allow-Types"), { expires: 7 });
                /*
                  if(remeber == true)
                  {
                     $.cookie("loginname", username, { expires: 7 });
                     $.cookie("psw", password, { expires: 7 });
                     $.cookie("remeber", "1", { expires: 7 });
                  }else{
                     $.cookie("loginname", null);
                     $.cookie("psw", null);
                     $.cookie("remeber", null);
                  }								  
                  */
                location.href = 'main';
            }).fail(function (data) {
                alert('登录帐号或密码错误，请重新输入！');
            });
		});
	});
	</script>
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  </body>
</html>
