<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function(){
        //初始化表单控件---title
        $("#addBannerFormTitle").textbox({
            required:true
        });
        //初始化表单控件---imgPath
        $("#addBannerFormImgPath").textbox({
            required:true
        });
        //初始化表单控件---status
        $("#addBannerFormStatus").validatebox({
            required:true,
            validType:"status"
        });
        //自定义规则
        $.extend($.fn.validatebox.defaults.rules, {
            status: {
                validator: function(value,params){
                    return value=="Y"||value=="N";
                },
                message: '输入N或Y(N代表否,Y代表是)'
            }
        });
        //初始化表单控件---pubDate
        $("#addBannerFormPubDate").datebox({
            required:true
        });
        //初始化表单控件---description
        $("#addBannerFormDescription").textbox({
            required:true
        });
        //初始化表单控件---添加按钮
        $("#addBannerFormSaveBtn").linkbutton({
            onClick:function(){
                //关闭添加对话框---调用对话框的close方法
                $("#addBannerDialog").dialog("close");
                //提交表单----调用form表单的submit方法
                $("#addBannerForm").form("submit",{
                    url:"${pageContext.request.contextPath }/banner/addBanner",
                    onSubmit:function(){
                        //表单验证----调用form的validate方法
                        return $("#addBannerForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"系统提示",
                            msg:"添加成功！"
                        });
                        //刷新dg中的数据----调load方法
                        $("#dg").datagrid("load");
                    }
                });
            }
        });
        //初始化表单控件---重置按钮
        $("#addBannerFormResetBtn").linkbutton({
            onClick:function(){
                $("#addBannerForm").form("reset");
            }
        });
    })
</script>
<form id="addBannerForm" method="post">
    名称<input id="addBannerFormTitle" name="title"/><br/>
    图片<input id="addBannerFormImgPath" name="imgPath"/><br/>
    状态<input id="addBannerFormStatus" name="status"/><br/>
    发布日期<input id="addBannerFormPubDate" name="pubDate"/><br/>
    描述<input id="addBannerFormDescription" name="description"/><br/>
    <a id="addBannerFormSaveBtn">添加</a> <a id="addBannerFormResetBtn">重置</a>
</form>