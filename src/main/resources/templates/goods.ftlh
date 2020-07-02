<html>
<meta charset="UTF-8">
<head>
    <script charset="UTF-8" type="text/javascript" src="./js/jquery-3.5.0.min.js"></script>
</head>
<body>
    <a href="#">秒杀开始</a>
</body>
<script type="text/javascript">
    $(function() {
        $("a").click(function () {
            $.getJSON("/seckill/1/user-999",function(a){
                console.log(a)
                if(a.code == 20000){
                    alert(a.msg)
                    alert("正在创建订单...")
                    var timer = setInterval(() => {
                        $.getJSON("/order/"+a.data.orderNo,function (data) {
                            if(data.code==20000){
                                alert(data.msg)
                                clearInterval(timer)
                                window.location.href="/pay";
                            }else{
                                alert("正在创建订单...")
                            }
                        })
                    }, 1000)
                }else{
                    alert(a.msg)
                }
            })
            return false
        })
    });

</script>
</html>