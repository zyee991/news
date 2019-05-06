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
    <table id="treeTable" lay-filter="flist">
    </table>
</div>
<script>
    var editObj = null, ptable = null, treeGrid = null, tableId = 'treeTable', layer = null;
    layui.config({
        base: '/lib/'
    }).extend({
        treeGrid: 'treeGrid'
    }).use(['jquery', 'treeGrid', 'layer'], function () {
        var $ = layui.jquery;
        treeGrid = layui.treeGrid;//很重要
        layer = layui.layer;
        ptable = treeGrid.render({
            id: tableId
            , elem: '#' + tableId
            , url: 'data'
            , cellMinWidth: 100
            , idField: 'id'//必須字段
            , treeId: 'id'//树形id字段名称
            , treeUpId: 'parentId'//树形父id字段名称
            , treeShowName: 'name'//以树形式显示的字段
            , height: '100%'
            , isFilter: false
            , iconOpen: false//是否显示图标【默认显示】
            , isOpenDefault: false//节点默认是展开还是折叠【默认展开】
            , loading: true
            , method: 'post'
            , isPage: false
            , cols: [[
                {type: 'numbers'}
                , {field: 'name', title: '名称', sort: true}
                , {field: 'url', title: 'url', sort: true}
                , {title: '操作', align: 'center', toolbar: '#operateBar'}
            ]],
            toolbar: "#titleBar"
        });

        treeGrid.on('tool(flist)', function (obj) {
            var id = obj.data.id;
            if (obj.event === 'del') {//删除行
                layer.confirm('确定删除？', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    $.post("remove", {id: id}, function (result) {
                        if (result.code == '0') {
                            layer.msg('已删除!', {icon: 1, time: 1000})
                        }
                    })
                });
            } else if (obj.event === "edit") {//编辑
                x_admin_show('编辑', 'edit?id=' + id, 600, 400);
            } else if (obj.event === "add") {
                x_admin_show('新增', 'add?parentId=' + id, 600, 400);
            }
        });
    });
</script>
<script type="text/html" id="operateBar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add">新增</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="titleBar">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal layui-btn-xs" onclick="x_admin_show('新增', 'add?parentId=abstract', 600, 400);">新增</a>
    </div>
</script>
</body>

</html>