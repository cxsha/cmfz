<%@page pageEncoding="UTF-8"%>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main2" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main2'));
    var option = {
        title: {
            text: '持明法洲App活跃用户'
        },
        tooltip: {},
        legend: {
            type: "scroll",
            data: ['用户数量']
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: []
        }]
    }

    myChart.setOption(option);
    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/user/count",
        dataType: "JSON",
        success: function (data) {
            console.log(data);
            myChart.setOption({
                xAxis: {
                    data: data.intervals
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '活跃用户',
                    data: data.data
                }]
            })
        }
    })


</script>