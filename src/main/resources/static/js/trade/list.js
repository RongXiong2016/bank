/**
 * Created by Administrator on 2018/4/8 0008.
 */

layui.use(['table','layer'], function(){
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    var tableIns = table.render({
        elem: '#test1'
        ,url:'/trade/list1'
        ,cellMinWidth: 100
        ,id:"idTest"
        ,cols: [[
            {field:'id', width:80, title: 'ID', sort: true}
            ,{field:'trade_no', width:166, title: '交易流水号', sort: true}
            ,{field:'user_name', width:150, title: '客户名称'}
            ,{field:'product_name', width:160, title: '产品名称', sort: true}
            ,{field:'income', width:120, title: '状态'}
            ,{field:'amount',width: 100, title: '预期收益'}
            ,{field:'trade_time', width:120, title: '交易时间'}
           /* ,{title: '操作', width:170, templet:'#barDemo',fixed:"right",align:"center"}*/
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
            layer.open({
                type: 2,
                maxmin: true,
                title: '编辑理财产品',
                content: '/product/toEdit?id='+data.id, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                area: ['700px', '450px'],
                offset: ['50px', '250px'],
                shadeClose:true, //点击弹出框外可关闭弹出框
                success: function (layero, index) {
                    var name = $("#name").val();
                }
            });
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