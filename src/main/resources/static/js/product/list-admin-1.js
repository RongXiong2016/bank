/**
 * Created by Administrator on 2018/4/8 0008.
 */

layui.use(['table','layer'], function(){
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    var tableIns = table.render({
        elem: '#test1'
        ,url:'/product/list2'
        ,cellMinWidth: 100
        ,id:"idTest"
        ,cols: [[
            {field:'id', width:80, title: 'ID', sort: true}
            ,{field:'productCode', width:166, title: '产品编码', sort: true}
            ,{field:'name', width:150, title: '名称'}
            ,{field:'type', width:160, title: '类型', sort: true}
            ,{field:'status', width:120, title: '状态'}
            ,{field:'currency',width: 100, title: '币种'}
            ,{field:'riskLevel',width: 100, title: '风险等级'}
            ,{field:'returnRate',width: 80, title: '收益率(%)'}
            ,{field:'start_sale_time', width:120, title: '起售时间'}
            ,{field:'end_sale_time', width:120, title: '停售时间'}
            ,{field:'ran_out_time', width:120, title: '到期时间'}
            ,{title: '操作', width:170, templet:'#barDemo',fixed:"right",align:"center"}
        ]]
        ,page: true
    });
    //监听表格复选框选择
    table.on('checkbox(demo)', function(obj){
        console.log(obj)
    });
    //监听工具条
    table.on('tool(demo)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
                $.ajax({
                    type:"GET",
                    url:'/product/delete?id='+data.id,
                    success:function(msg){

                    }
                });

            });
        } else if(obj.event === 'edit'){
            $.ajax({
                type:"GET",
                url:'/product/review?id='+data.id,
                success:function(msg){

                }
            });

            setTimeout(layer.msg('ID：'+ data.id + ' 审核通过'),5000)
            window.location.reload()
        }
    });
    //搜索框
    $(".search_btn").on("click",function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    var $ = layui.$, active = {
        getCheckData: function(){ //获取选中数据
            var checkStatus = table.checkStatus('idTest')
                ,data = checkStatus.data;
            layer.alert(JSON.stringify(data));
        }
        ,getCheckLength: function(){ //获取选中数目
            var checkStatus = table.checkStatus('idTest')
                ,data = checkStatus.data;
            layer.msg('选中了：'+ data.length + ' 个');
        }
        ,isAll: function(){ //验证是否全选
            var checkStatus = table.checkStatus('idTest');
            layer.msg(checkStatus.isAll ? '全选': '未全选')
        }
    };

    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $(".searchVal");
            //执行重载
            table.reload('idTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        name: demoReload.val()
                    }
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});

$(function() {
    $('#add-product').on('click', function () {
        layer.open({
            type: 2,
            maxmin: true,
            title: '新增理财产品',
            content: '/product/toAdd',
            area: ['700px', '450px'],
            offset: ['50px', '250px'],
            shadeClose:true,
            success: function (layero, index) {
                var name = $("#name").val();
            }
        });
    });

});