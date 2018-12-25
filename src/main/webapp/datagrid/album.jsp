<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    var oneAlbum = null;
    var cId = null;
    var cUrl = null;
    $(function () {
        var toolbar = [{
            iconCls: 'icon-tip',
            text: "专辑详情",
            handler: function () {
                //获取选中行
                var row = $("#album").edatagrid("getSelected");
                if (row != null) {
                    if (row.albumId != null) {
                        alert("请选中专辑")
                    }else {
                        $("#albumDialog").dialog("open");
                        oneAlbum = row;
                    }
                } else {
                    alert("请先选中专辑")
                }
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-add',
            handler: function () {
                $("#addAlbumDialog").dialog("open");
            }
        }, '-', {
            text: "添加章节",
            iconCls: 'icon-save',
            handler: function () {
                //获取选中行
                var row = $("#album").edatagrid("getSelected");
                if (row != null) {
                    if (row.albumId != null) {
                        alert("请选中专辑")
                    }else {
                        $("#addChapterDialog").dialog("open");
                        cId = row.id;
                    }
                } else {
                    alert("请先选中专辑")
                }
            }
        }, '-', {
            text: "下载音频",
            iconCls: 'icon-undo',
            handler: function () {
                //获取选中行
                var row = $("#album").edatagrid("getSelected");
                if (row != null) {
                    if (row.albumId != null) {
                        location.href = "${pageContext.request.contextPath}/chapter/down?url="+row.url+"&title="+row.title;
                    }else {
                        alert("请选中音频")
                    }
                } else {
                    alert("请先选中音频")
                }
            }
        }]

        //初始化专辑详情对话框
        $("#albumDialog").dialog({
            title:"专辑详情",
            width:400,
            height:200,
            closed:true,
            href:"${pageContext.request.contextPath }/datagrid/detailsAlbum.jsp",
            modal:true,
            cache:false
        });

        //初始化添加专辑对话框
        $("#addAlbumDialog").dialog({
            title:"添加专辑",
            width:400,
            height:200,
            closed:true,
            href:"${pageContext.request.contextPath }/datagrid/addAlbum.jsp",
            modal:true,
            cache:false
        });

        //初始化添加章节对话框
        $("#addChapterDialog").dialog({
            title:"添加专辑",
            width:400,
            height:200,
            closed:true,
            href:"${pageContext.request.contextPath }/datagrid/addChapter.jsp",
            modal:true,
            cache:false
        });

        $('#album').treegrid({
            url:'${pageContext.request.contextPath}/album/albumQueryByPage',
            idField:'id',
            treeField:'title',
            columns:[[
                {field:'title',title:'名字',width:80},
                {field:'url',title:'下载路径',width:80},
                {field:'size',title:'章节大小',width:80},
                {field:'duration',title:'章节时长',width:80}
            ]],
            onDblClickCell:function(){
                //获取选中行
                var row = $("#album").edatagrid("getSelected");
                if (row != null) {
                    if (row.albumId != null) {
                        $("#audioDialog").dialog("open");
                        cUrl = row.url;
                    }else {
                        alert("请先选中章节")
                    }
                } else {
                    alert("请先选中章节")
                }

            },
            fit:true,
            fitColumns:true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            toolbar:toolbar
        });

        //初始化音频播放对话框
        $("#audioDialog").dialog({
            title:"音频播放",
            width:400,
            height:100,
            closed:true,
            href:"${pageContext.request.contextPath }/datagrid/audio.jsp",
            modal:true,
            cache:false
        });
    })

</script>

<table id="album"></table>
<!-- 专辑详情对话框 -->
<div id="albumDialog"></div>
<!-- 添加专辑对话框 -->
<div id="addAlbumDialog"></div>
<!-- 添加章节对话框 -->
<div id="addChapterDialog"></div>
<!-- 音频播放对话框 -->
<div id="audioDialog"></div>