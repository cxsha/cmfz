<%@page pageEncoding="UTF-8"%>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main1" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main1'));

    option = {
        title : {
            text: '持明法洲App用户分布图',
            subtext: '2018年12月25日 最新数据',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['男','女']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        series : [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[]
            },{
                name: '女',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[]
            }

        ]
    };
    myChart.setOption(option)
    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/user/manProvince",
        dataType: "JSON",
        success: function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '男',
                    data: data
                }]
            })
        }
    })

    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/user/womanProvince",
        dataType: "JSON",
        success: function (data) {
            console.log(data);
            myChart.setOption({
                series: [{
                    // 根据名字对应到相应的系列
                    name: '女',
                    data: data
                }]
            })
        }
    })
</script>