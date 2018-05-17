<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>错误</title>


<style>
div{ padding:50px 0 0 50px;   }
.datainp{ width:200px; height:30px; border:1px #ccc solid;}
.datep{ margin-bottom:40px; line-height:24px;}
</style>
   
   <!--   <link rel="stylesheet" type="text/css" href=" //code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
  -->
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
 
  <script type="text/javascript" src="//code.jquery.com/jquery-1.12.0.min.js"></script>
 <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<!--  <script type="text/javascript" src="https://cdn.datatables.net/1.10.10/js/dataTables.jqueryui.min.js"></script>
 -->
<script src="${pageContext.request.contextPath}/js/jedate/jedate.js" ></script>


 <script type="text/javascript">
    
 $(document).ready(function() {
	 $("#tt").click(function(){
		   $('#tb').dataTable().fnDestroy();
		   $('#tb').dataTable( {
			  
			   'language': {  
	               'emptyTable': '没有数据',  
	               'loadingRecords': '加载中...',  
	               'processing': '查询中...',  
	               'search': '检索:',  
	               'lengthMenu': '每页 _MENU_ 条',  
	               'zeroRecords': '没有数据',  
	               'paginate': {  
	                   'first':      '第一页',  
	                   'last':       '最后一页',  
	                   'next':       '下一页',  
	                   'previous':   '上一页'  
	               },  
	               'info': '第 _PAGE_ 页 / 总 _PAGES_ 页',  
	               'infoEmpty': '没有数据',  
	               'infoFiltered': '(过滤总件数 _MAX_ 条)'  
	           },
	          
	           "bProcessing": false, // 是否显示取数据时的那个等待提示
	           "bServerSide": true,//这个用来指明是通过服务端来取数据
	           "sAjaxSource": "http://localhost:8080/smb6/gather/dateTable",//这个是请求的地址
	           "fnServerData": retrieveData // 获取数据的处理函数
	             });
		});
	
	   
	   
	 
       // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
       function retrieveData( sSource111,aoData111, fnCallback111) {
    	   // 将客户名称加入参数数组  
    	   aoData111.push( { "name": "from", "value": $("#form").val()  },{ "name": "end", "value": $("#end").val() } );//添加自己的额外参数  
    	  /*    alert(aoData111[0].name);  
    	     alert(JSON.stringify(aoData111));   */
           $.ajax({
               url : sSource111,//这个就是请求地址对应sAjaxSource
               data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
               type : 'post',
               dataType : 'json',
               async : false,
               success : function(result) {
                   fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
               },
               error : function(msg) {
               }
           });
       }
	} );
 
 
</script>

 
</head>
<body>
<br></br>
<div class="datep"  align="center">  开始时间 <input class="datainp" id="form" type="text" placeholder="请选择"  readonly> 
  结束时间 <input class="datainp" id="end" type="text" placeholder="请选择"  readonly> <input id="tt"  type="submit" value="一个提交按钮">
  </div>

 
    <table id="tb" class="display" >
        <thead>
            <tr>
                
                   <th>测站名称</th>
                <th>河流名称</th>
                  <th>流域名称</th>
                <th>行政区</th>
                  <th>时间</th>
                <th>日雨量</th>
                 <th> 天气状况</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
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
    d.setTime(d.getTime()-24*2*60*60*1000);
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