<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>jeDate日期控件</title>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.3.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/jedate/jedate.js" ></script>

<style>
body{ padding:50px 0 0 50px;}
.datainp{ width:200px; height:30px; border:1px #ccc solid;}
.datep{ margin-bottom:40px; line-height:24px;}
</style>
</head>

<body>
<div style="width:100%;">
 
 <p class="datep"><input class="datainp" id="form" type="text" placeholder="请选择"  readonly></p>
  <p class="datep"><input class="datainp" id="end" type="text" placeholder="请选择"  readonly></p>
 
 </div>
<script type="text/javascript">
    //jeDate.skin('gray');
    
    function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
   
    return year + "-" + month + "-" + date;
      }
    var d = new Date();
    d.setTime(d.getTime()-24*60*60*1000);
    var s = d.getFullYear()+"-" + (d.getMonth()+1) + "-" + d.getDate();
   
     var time =timeStamp2String(d);
      $("#form").val(time)      
    	    
    jeDate({
    	dateCell:"#form",
		format:"YYYY-MM-DD",
		isinitVal:true,
		festival:true,
		marks:['2015-01-03','2016-01-01','2016-01-10','2016-01-15','2016-02-15','2016-03-10','2016-04-18'],
		isTime:true, //isClear:false,
	    maxDate: jeDate.now(0),
		okfun:function(val){alert(val)}
	})
	
	 jeDate({
		dateCell:"#end",
		format:"YYYY-MM-DD",
		isinitVal:true,
		festival:true,
		marks:['2015-01-03','2016-01-01','2016-01-10','2016-01-15','2016-02-15','2016-03-10','2016-04-18'],
		isTime:true, //isClear:false,
		 okfun:function(val){alert(val)}
	})

    //alert("YYYY/MM".match(/\w+|d+/g).join("-"))
</script>
</body>
</html>
