<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#addBannerDialog").dialog("open");
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                console.log(row);
                $.messager.confirm("确认对话框","您确定删除吗？",function(r){
                    if(r){
                        $.get(
                            "${pageContext.request.contextPath }/banner/deleteBanner",
                            row,
                            function(){
                                //右下角提示框
                                $.messager.show({
                                    title:"系统提示",
                                    msg:"删除成功！"
                                });
                            }
                        );
                        //刷新dg中的数据----调load方法
                        $("#dg").datagrid("reload");
                    }
                });
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow");
                $.messager.show({
                    title:"系统提示",
                    msg:"修改成功！"
                });

            }
        }]
        //初始化添加对话框
        $("#addBannerDialog").dialog({
            title:"添加",
            width:400,
            height:200,
            closed:true,
            href:"${pageContext.request.contextPath }/datagrid/addBanner.jsp",
            modal:true,
            cache:false
        });

        $('#dg').edatagrid({
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            url: '${pageContext.request.contextPath}/banner/queryByPage',
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required:true}
                    }
                },
                {field: 'pubDate', title: '时间', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.description + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

</script>

<table id="dg"></table>
<!-- 添加对话框 -->
<div id="addBannerDialog"></div>