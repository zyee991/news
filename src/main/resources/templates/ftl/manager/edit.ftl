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
        <input type="hidden" name="saveType" value="edit"/>
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="x-red">*</span>用户名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="new_username" required="" lay-verify="username" value="${manager.username}" readonly autocomplete="off" class="layui-input"/>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为您唯一的登入名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_nickname" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_nickname" name="new_nickname" required="" lay-verify="nickname"
                       value="${manager.nickname}"
                       autocomplete="off" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"><span class="x-red">*</span>角色</label>
            <div class="layui-input-block">
                <#list roles as role>
                    <#if role.checked == true>
                        <input checked lay-filter="checkbox" type="checkbox" name="roles" title="${role.name}"
                               value="${role.id}"/>
                    <#else>
                        <input lay-filter="checkbox" type="checkbox" name="roles" title="${role.name}"
                               value="${role.id}"/>
                    </#if>
                </#list>
            </div>
        </div>
        <input type="hidden" name="id" value="${manager.id}"/>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
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
            nikename: function (value) {
                if (!value) {
                    return '请输入昵称';
                }
            }
        });

        var roleList = new Array();
        <#list roles as role>
            <#if role.checked == true>
               roleList.push(${role.id});
            </#if>
        </#list>
        console.log(roleList);
        form.on('checkbox(checkbox)', function (data) {
            var checked = data.elem.checked;
            if (checked) {
                roleList.push(data.value); //复选框value值，也可以通过data.elem.value得到
            } else {
                roleList.remove(data.value);
            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var param = data.field;
            param.roles = roleList.join(",");
            $.post('save', param, function (result) {
                if (result.code == 0) {
                    layer.alert("修改成功", {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        // 可以对父窗口进行刷新
                        x_admin_father_reload();
                    });
                } else {
                    layer.alert("修改失败:" + result.msg);
                }
            })
            return false;
        });


    });
</script>
</body>

</html>