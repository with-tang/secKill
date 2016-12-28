var seckill = {

    URL: {

        now: function () {        	
            return  '/seckill/seckill/time/now'
        },
        exposer: function(seckillId){
            return '/seckill/seckill/' + seckillId + '/exposer';
        },
        execution: function(seckillId, md5){
            return '/seckill/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handleSeckill: function(seckillId, node){
        node.hide()
            .html('<button class="btn bg-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{}, function(result){
        	console.info("秒杀处理开始");
        	console.info("seckillId"+seckillId);
            console.log(result.success);
            console.log(result.data);
            if(result && result['success']){
            	console.info(result['data']);
                var exposer = result['data'];
                if(exposer['exposed']){
                    //start seckill
                    //get seckill address
                    var md5 = exposer['md5'];
                    console.info("md5 = "+md5); 
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.info("killUrl:"+killUrl);
                    //只绑定一次点击事件
                    $('#killBtn').one('click', function(){
                        $(this).addClass('disabled');
                        console.info($(this));
                        $.post(killUrl, {}, function(result){
                        //	console.info("result:"+result['success']+" "+result['data']);
                            if(result && result['success']){
                                var killResult = result['data'];
                                console.info(killResult);
                                var status = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //do not start seckill
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countdown(seckillId, now, start, end);
                }
            }else{
                console.log('result : ' + result);
            }
        });
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countdown : function(seckillId, nowTime, startTime, endTime){
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){
            seckillBox.html('秒杀已结束');
            console.info("秒杀结束");
        }else if(nowTime < startTime){
        	console.info("秒杀倒计时：");
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function(event){
                var format = event.strftime('秒杀倒计时： %D天 %H小时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function(){
            	console.info("seckillId:"+seckillId);
                seckill.handleSeckill(seckillId, seckillBox);
            });
        }else{
        	console.info("处理结束");
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    detail: {
        //
        init: function (params) {
            var killPhone = $.cookie('killPhone');
            console.info(killPhone);
            //模拟登录
            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false  //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killphoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }

            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            console.info(seckillId);
            console.info(startTime);
            $.get(seckill.URL.now(), {}, function (result) {
                if(result && result['success']){
                    var nowTime = result['data'];
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                    	console.info("count down:    seckill.countdown(seckillId, nowTime, startTime, endTime);");
                }else{
                    console.log('result : ' + result);
                }
            });
        }
    }
};
