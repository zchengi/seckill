<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>秒杀详情页</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<br/><br/>
<div class="container">
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="pannel-heading">
                <h1>${seckill.name}</h1>
            </div>

            <div class="panel-body">
                <h2 class="text-danger">
                    <!--显示time图标--> <span class="glyphicon glyphicon-time"></span>
                    <!--展示倒计时--> <span class="glyphicon" id="seckill-box"></span>
                </h2>
            </div>
        </div>
    </div>
    <!--登录弹出层 输入电话-->
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killphone" id="killphoneKey" placeholder="填手机号···"
                                   class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killphoneMessage" class="glyphicon"></span>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery文件 -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- cookie操作插件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery countDown倒计时插件 -->
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<!-- 开始编写交互逻辑 -->
<script src="/resources/js/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId: ${seckill.seckillId},
            startTime: ${seckill.startTime.time},//毫秒
            endTime: ${seckill.endTime.time}
        });
    });
</script>
</body>
</html>