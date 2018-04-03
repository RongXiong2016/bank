var $ = layui.jquery;

$(function ()
{
    // 整点红包抽奖
    $(".redbao").click(function ()
    {
        redbaogive();
    });
});

// 欢迎关闭
function welclose()
{
    layer.closeAll();
    //redbaogive();
}

//整点红包
function redbaogive()
{
    layer.load(1);
    $.post("/apis/chouredbao", function (data)
    {
        layer.closeAll('loading');
        layer.alert(data.message);
    }, 'json');
}

// 签到
function signin(obj)
{
    layer.load(1);
    $.post("/apis/signin", function (data)
    {
        layer.closeAll('loading');
        if (data.num == 1 || data.num == 2)
        {
            layer.msg("打卡成功", { icon: 1 });
            $(".sumnumber").text(data.inte.sumnumber + "G");
            $(obj).removeAttr("onclick").addClass("layui-btn-disabled").html("哟！您已连续打卡 <span class=\"layui-badge-rim\">" + data.inte.day + "</span> 天");
        } else
        {
            layer.alert(data.message);
        }
    }, 'json');
}

function duihuan(pid)
{
    layer.confirm('您是否要兑换该商品？', {
        title: '兑换信息',
        skin: 'layui-layer-lan',
        btn: ['确定', '取消'] //按钮
    }, function ()
        {
            layer.load(1);
            $.post("/apis/duihuan", { pid: pid }, function (data)
            {
                layer.closeAll('loading');
                layer.alert(data.message, {
                    title: '兑换信息',
                    skin: 'layui-layer-lan',
                });
            }, 'json');
        });
}

function shoucang(obj, pid)
{
    layer.load(1);
    $.post("/apis/collect", { pid: pid }, function (data)
    {
        layer.closeAll('loading');
        layer.alert(data.message, {
            title: '兑换信息',
            skin: 'layui-layer-lan',
        });
    }, 'json');
}