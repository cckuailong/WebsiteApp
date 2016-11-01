<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
out.clear();

String action=request.getParameter("action");
if(action!=null){
	if(action.equals("1")){
		out.print("mmmmm");
	}else{
		out.print("zzzzzzz");
	}
}else{
	out.print("Input action");
}


%>
