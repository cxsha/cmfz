<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>持名法州后台管理中心</title>
			
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="css/common.css" type="text/css"></link>
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	<script type="text/javascript" src="script/jquery.js"></script>
	<script type="text/javascript" src="script/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
		$(function(){
            //初始化表单控件---username
            $("#loginFormUsername").textbox({
                required:true
            });
            //初始化表单控件---password
            $("#loginFormPassword").validatebox({
                required:true,
                validType:"Length[6]"
            });
            //自定义规则
            $.extend($.fn.validatebox.defaults.rules, {
                Length: {
                    validator: function(value,params){
                        return value.length == params[0];
                    },
                    message: '请输入{0}位密码'
                }
            });
			//点击更换验证码
			$("#captchaImage").click(function(){//点击更换验证码
                $("#captchaImage").prop("src","${pageContext.request.contextPath}/image/code?time="+new Date());
			});
            //初始化表单控件---登录按钮
            $("#login").linkbutton({
                onClick:function(){
                    //  form 表单提交
                    $('#loginForm').form('submit', {
                        dataType: "json",
                        url:"${pageContext.request.contextPath }/admin/login",
                        onSubmit: function(){
                            var isValid = $(this).form('validate');
                            if (!isValid){
                                $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                            }
                            return isValid;	// 返回false终止表单提交
                        },
                        success: function(value){
                            value = eval("("+value+")");
                            if(value == "ok"){
                                location.href="${pageContext.request.contextPath }/main/main.jsp";
                                $.messager.progress('close');	// 如果提交成功则隐藏进度条
								return true;
                            }else{
                                $.messager.show({
                                    title:"系统提示",
                                    msg:value
                                });
                            }
                        }
                    });
				}
			});

		});
	</script>
</head>
<body>
	
		<div class="login">
			<form id="loginForm" method="post" >
				
				<table>
					<tbody>
						<tr>
							<td width="190" rowspan="2" align="center" valign="bottom">
								<img src="img/header_logo.gif" />
							</td>
							<th>
								用户名:
							</th>
							<td>
								<input id="loginFormUsername" type="text"  name="name" class="text" value="" maxlength="20"/>
							</td>
					  </tr>
					  <tr>
							<th>
								密&nbsp;&nbsp;&nbsp;码:
							</th>
							<td>
								<input id="loginFormPassword" type="password" name="password" maxlength="20" autocomplete="off"/>
							</td>
					  </tr>
					
						<tr>
							<td>&nbsp;</td>
							<th>验证码:</th>
							<td>
								<input type="text" id="enCode" name="enCode" class="text captcha" maxlength="4" autocomplete="off"/>
								<img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/image/code" title="点击更换验证码"/>
							</td>
						</tr>					
					<tr>
						<td>
							&nbsp;
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<th>&nbsp;</th>
						<td>
							<input type="button" class="homeButton" value="" onclick="location.href='/'"><input id="login" class="loginButton" value="登录">
						</td>
					</tr>
				</tbody></table>
				<div class="powered">COPYRIGHT © 2008-2017.</div>
				<div class="link">
					<a href="http://www.chimingfowang.com/">持名佛网首页</a> |
					<a href="http://www.chimingbbs.com/">交流论坛</a> |
					<a href="">关于我们</a> |
					<a href="">联系我们</a> |
					<a href="">授权查询</a>
				</div>
			</form>
		</div>
</body>
</html>