<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <script type="text/javascript" src="/js/cookie.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <table id="data" lay-filter="flist">
    </table>
</div>
<script>
    layui.config({
        base: '/lib/',
    })
    layui.use('treeTable', function () {
        var treeTable = layui.treeTable;
        var	re = treeTable.render({
            elem: '#data',
            url: 'data',
            icon_key: 'title',
            cols: [
                {
                    key: 'name',
                    title: '名称',
                    width: '100px',
                    template: function(item){
                        if(item.level == 0){
                            return '<span style="color:red;">'+item.title+'</span>';
                        }else if(item.level == 1){
                            return '<span style="color:green;">'+item.title+'</span>';
                        }else if(item.level == 2){
                            return '<span style="color:#aaa;">'+item.title+'</span>';
                        }
                    }
                },
                {
                    key: 'url',
                    title: 'url',
                    width: '100px',
                    align: 'center'
                },
                {
                    title: '操作',
                    align: 'center',
                    toolBar: '#operateBar'
                }
            ],
        });

        treeTable.on('tool(flist)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent === 'del') { //删除
                layer.confirm('确定删除？', function (index) {
                    $.post("remove", {id: data.id}, function (result) {
                        if (result.code == '0') {
                            layer.msg('已删除!', {icon: 1, time: 1000})
                        }
                    })
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                //do something
                x_admin_show('编辑', 'edit?id=' + data.id, 600, 400);
            }
        });

        treeTable.on('toolbar(flist)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'add') { //查看
                x_admin_show('新增', 'add', 600, 400);
            }
        });
    })
</script>
<script type="text/html" id="operateBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="titleBar">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="add">新增</a>
    </div>
</script>
</body>

</html>