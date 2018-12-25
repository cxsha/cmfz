<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function(){
        //初始化表单控件---title
        $("#addChapterFormTitle").textbox({
            required:true
        });
        //初始化表单控件---uploadDate
        $("#addChapterFormUploadDate").datebox({
            required:true
        });
        //初始化表单控件---audio(url)
        $("#addChapterFormUrl").filebox({
            required:true
        });
        //初始化表单控件---添加按钮
        $("#addChapterFormSaveBtn").linkbutton({
            onClick:function(){
                //关闭添加对话框---调用对话框的close方法
                $("#addChapterDialog").dialog("close");
                //提交表单----调用form表单的submit方法
                $("#addChapterForm").form("submit",{
                    url:"${pageContext.request.contextPath }/chapter/addChapter?albumId="+cId,
                    onSubmit:function(){
                        //表单验证----调用form的validate方法
                        return $("#addChapterForm").form("validate");
                    },
                    success:function(){
                        $.messager.show({
                            title:"系统提示",
                            msg:"添加成功！"
                        });
                        //刷新album中的数据----调load方法
                        $("#album").treegrid("reload");
                    }
                });
            }
        });
        //初始化表单控件---重置按钮
        $("#addChapterFormResetBtn").linkbutton({
            onClick:function(){
                $("#addChapterForm").form("reset");
            }
        });
    })
</script>
<form id="addChapterForm" method="post" enctype="multipart/form-data">
    名字<input id="addChapterFormTitle" name="title"/><br/>
    上传日期<input id="addChapterFormUploadDate" name="uploadDate"/><br/>
    下载路径<input id="addChapterFormUrl" name="audio"/><br/>
    <a id="addChapterFormSaveBtn">添加</a> <a id="addChapterFormResetBtn">重置</a>
</form>