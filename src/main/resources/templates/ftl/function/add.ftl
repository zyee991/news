<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.1</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
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
<div class="x-body">
    <form class="layui-form">
        <input type="hidden" name="saveType" value="add"/>
        <div class="layui-form-item">
            <label for="parentId" class="layui-form-label">
                <span class="x-red">*</span>父菜单
            </label>
            <div class="layui-input-inline">
                <select id="parentId" name="parentId" lay-verify="parentId">
                <#list functionList as function>
                    <#if function.id == parentId>
                    <option selected value="${function.id}">${function.name}</option>
                    <#else>
                    <option value="${function.id}">${function.name}</option>
                    </#if>
                </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="name" class="layui-form-label">
                <span class="x-red">*</span>名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" required="" lay-verify="name" value=""
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="url" class="layui-form-label">
                <span class="x-red">*</span>url
            </label>
            <div class="layui-input-inline">
                <input type="text" id="url" name="url" required="" lay-verify="url" value=""
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="add" lay-submit="">
                保存
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
                , layer = layui.layer;

        //自定义验证规则
        form.verify({
            name: function (value) {
                if (!value) {
                    return '名称不能为空';
                }
            }
            , url: function (value) {
                if (!value) {
                    return 'url不能为空';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var param = data.field;
            $.post('save', param, function (result) {
                if (result.code == 0) {
                    layer.alert("增加成功", {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        // 可以对父窗口进行刷新
                        x_admin_father_reload();
                    });
                } else {
                    layer.alert("增加失败:" + result.msg);
                }
            })
            return false;
        });


    });
</script>
</body>

</html>