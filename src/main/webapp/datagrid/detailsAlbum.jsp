<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        //页面加载成功后，调load查一个，并且自动填充表单数据
        $("#detailsAlbumForm").form("load","${pageContext.request.contextPath }/album/selectOneAlbum?id="+aId);
        $("#detailsAlbumForm").form({
            onLoadSuccess:function (data) {
                $("#detailsAlbumFormCoverImg").prop("src","${pageContext.request.contextPath}"+data.coverImg);
            }
        })
        //初始化退出按钮
        $("#detailsAlbumFormExitBtn").linkbutton({
            onClick:function () {
                $("#albumDialog").dialog("close");
            }
        })

    })
</script>
<form id="detailsAlbumForm" method="post">
    <input id="detailsAlbumFormId" name="id" hidden="hidden"/>
    名字<input id="detailsAlbumFormTitle" name="title" readonly/><br/>
    集数<input id="detailsAlbumFormCount" name="count" readonly/><br/>
    封面<input id="detailsAlbumFormCoverImg" type="image" name="coverImg" width="30" height="25"/><br/>
    评分<input id="detailsAlbumFormScore" name="score" readonly/><br/>
    作者<input id="detailsAlbumFormAuthor" name="author" readonly/><br/>
    播音<input id="detailsAlbumFormBroadcast" name="broadcast" readonly/><br/>
    内容简介<input id="detailsAlbumFormBrief" name="brief" readonly/><br/>
    发布日期<input id="detailsAlbumFormPubDate" name="pubDate" readonly/><br/>
    <a id="detailsAlbumFormExitBtn">退出</a>
</form>
