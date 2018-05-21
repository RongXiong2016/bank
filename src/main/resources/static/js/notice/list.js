/**
 * Created by Administrator on 2018/5/21 0021.
 */
layui.use(['table','layer'], function(){
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    var active = {
        confirmTrans: function(){
            //配置一个透明的询问框
            layer.msg('大部分参数都是可以公用的<br>合理搭配，展示不一样的风格', {
                time: 20000, //20s后自动关闭
                btn: ['明白了', '知道了', '哦']
            });
        }
    }

    var tableIns = table.render({
        elem: '#test1'
        ,url:'/notice/list1'
        ,cellMinWidth: 100
        ,id:"idTest"
        ,cols: [[
            {field:'id', width:80, title: 'ID', sort: true}
            ,{field:'content', width:130, title: '内容', sort: true}
            ,{field:'status', width:180, title: '状态'}
            ,{field:'createTime', width:160, title: '创建时间', sort: true}
            ,{field:'type', width:120, title: '类型'}
           /* ,{field:'phone', title: '电话', width: 120}
            ,{field:'email', title: '邮箱', width: 120}*/
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
                    url:'/user/delete?id='+data.id,
                    success:function(msg){

                    }
                });

            });
        } else if(obj.event === 'edit'){
            //layer.alert('编辑行：'+ JSON.stringify(data))
            layer.open({
                type: 2,
                maxmin: true,
                title: '编辑用户',
                content: '/user/toEdit?id='+data.id, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                area: ['600px', '450px'],
                offset: ['50px', '250px'],
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
       /* getCheckData: function(){ //获取选中数据
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
        },*/


    };

    var $ = layui.$, active = {
        reload: function(){
            var demoReload = $(".searchVal");
            //alert(123)
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