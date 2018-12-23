<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function(){
        //初始化表单控件---title
        $("#addAlbumFormTitle").textbox({
            required:true
        });
        //初始化表单控件---coverImg
        $("#addAlbumFormCoverImg").textbox({
            required:true
        });
        //初始化表单控件---score
        $("#addAlbumFormScore").textbox({
            required:true
        });
        //初始化表单控件---author
        $("#addAlbumFormAuthor").textbox({
            required:true
        });
        //初始化表单控件---broadcast
        $("#addAlbumFormBroadcast").textbox({
            required:true
        });
        //初始化表单控件---brief
        $("#addAlbumFormBrief").textbox({
            required:true
        });
        //初始化表单控件---pubDate
        $("#addAlbumFormPubDate").datebox({
            required:true
        });
        //初始化表单控件---添加按钮
        $("#addAlbumFormSaveBtn").linkbutton({
            onClick:function(){
                //关闭添加对话框---调用对话框的close方法
                $("#addAlbumDialog").dialog("close");
                //提交表单----调用form表单的submit方法
                $("#addAlbumForm").form("submit",{
                    url:"${pageContext.request.contextPath }/album/addAlbum",
                    onSubmit:function(){
                        //表单验证----调用form的validate方法
                        return $("#addAlbumForm").form("validate");
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
        $("#addAlbumFormResetBtn").linkbutton({
            onClick:function(){
                $("#addAlbumForm").form("reset");
            }
        });
    })
</script>
<form id="addAlbumForm" method="post">
    名字<input id="addAlbumFormTitle" name="title"/><br/>
    封面<input id="addAlbumFormCoverImg" name="coverImg"/><br/>
    评分<input id="addAlbumFormScore" name="score"/><br/>
    作者<input id="addAlbumFormAuthor" name="author"/><br/>
    播音<input id="addAlbumFormBroadcast" name="broadcast"/><br/>
    内容简介<input id="addAlbumFormBrief" name="brief"/><br/>
    发布日期<input id="addAlbumFormPubDate" name="pubDate"/><br/>
    <a id="addAlbumFormSaveBtn">添加</a> <a id="addAlbumFormResetBtn">重置</a>
</form>