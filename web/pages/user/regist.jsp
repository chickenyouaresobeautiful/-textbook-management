<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {

            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jsp?d=" + new Date();
            });

            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法！");

                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();

                //去掉验证码前后空格
                // alert("去空格前：["+codeText+"]")
                codeText = $.trim(codeText);
                // alert("去空格后：["+codeText+"]")

                if (codeText == null || codeText == "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空！");

                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");

            });

        });

    </script>
    <style type="text/css">
        .login_form {
            height: 1000px;
            margin-top: 25px;
        }

    </style>
</head>
<body>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="registerServlet" method="post">
                        <input type="hidden" name="action" value="register">
                        <input type="radio" name="identity" value="student">学生
                        <input type="radio" name="identity" value="teacher">老师<br>
                        <label>用户名：</label>
                        <input class="itxt" type="text" placeholder="请输入学号或工号"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>姓名：</label>
                        <input class="itxt" type="text" placeholder="请输入姓名"
                               value="${requestScope.name}"
                               autocomplete="off" tabindex="1" name="name" id="name"/>
                        <br/>
                        <br/>
                        <label>性别：</label>
                        <input type="radio" value="男" name="sex"/>男
                        <input type="radio" value="女" name="sex"/>女
                        <br/>
                        <br/>
                        <label>专业：</label>
                        <input class="itxt" type="text" placeholder="请输入您的专业"
                               value="${requestScope.major}"
                               autocomplete="off" tabindex="1" name="major" id="major"/>
                        <br/>
                        <br/>
                        <label>年级：</label>
                        <input class="itxt" type="text" placeholder="请输入您的年级"
                               value="${requestScope.grade}"
                               autocomplete="off" tabindex="1" name="grade" id="grade"/>
                        <br/>
                        <br/>
                        <label>班级：</label>
                        <input class="itxt" type="text" placeholder="请输入您的班级"
                               value="${requestScope.classId}"
                               autocomplete="off" tabindex="1" name="classId" id="classId"/>
                        <br/>
                        <br/>
                        <label>电话号码：</label>
                        <input class="itxt" type="text" placeholder="请输入您的电话号码"
                               value="${requestScope.phone}"
                               autocomplete="off" tabindex="1" name="phone" id="phone"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 100px;" id="code"/>
                        <img alt="" id="code_img" src="kaptcha.jsp"
                             style="float: right; margin-right: 40px; width: 100px; height: 28px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>