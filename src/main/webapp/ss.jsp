<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>错误</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo_page.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo_table_jui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo_table.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jqueryui-1.8.5/themes/smoothness/jquery.ui.all.css" />	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DataTables-1.7.3/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.my/common.js"></script>

 <script type="text/javascript">

 $(document).ready(function() {
		$('#example').dataTable({"bJQueryUI": true,
			"sPaginationType": "full_numbers",
		    "oLanguage": {
		    	   "sProcessing":"处理中...",
		    	   "sLengthMenu": "每页 _MENU_条",
		    	   "sZeroRecords": "没有找到任何记录！",
		    	   "sInfo": "_START_-_END_/_TOTAL_条",
		    	   "sInfoEmpty": "0-0/0条",
		    	   "sInfoFiltered": "(从_MAX_条记录过滤)",
		    	   "sSearch": "查询",
		    	   "oPaginate": {
		    	    "sFirst":    "首页", 
		    	    "sPrevious": "上页",
		    	    "sNext":     "下页",
		    	    "sLast":     "末页"
		    	   }
		    	  }});		
	} );
</script>

 
</head>
<body>
    <table width="89%" border="0" cellpadding="0" cellspacing="0"
	class="display" id="example">
      <thead>
        <tr>
          <th width="100">操作</th>
          <th>菜单名称</th>
          <th>编码</th>
          <th>菜单URL</th>
          <th>状态</th>           
        </tr>
      </thead>
      <tbody>
      	<%
       
 		
      	for(int i=0; i<1000; i++){
      		out.write("<tr class=\"gradeA\">");
       		out.write("<td class=\"center\"><a href=\"sysUserRight!GetOneData.action?ID="+i+"\">修改</a>&nbsp;&nbsp;<a href=\"javascript:DelMsg("+i+")\">删除</a></td>");
      		out.write("<td class=\"center\">"+i+"</td>");
      		out.write("<td class=\"center\">"+i+"</td>");
      		out.write("<td class=\"center\">"+i+"</td>");
      	 
      			out.write("<td class=\"center\">有效</td>");
      		out.write("</tr>");
      }%>
      </tbody>
      <tfoot>
        <tr>
          <th width="100">操作</th>
          <th>菜单名称</th>
          <th>编码</th>
          <th>菜单URL</th>
          <th>状态</th> 
		</tr>
      </tfoot>
    </table>
</body>
</html>