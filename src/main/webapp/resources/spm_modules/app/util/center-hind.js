/**
 * Created by jackieliu on 16/9/8.
 */
define(function (require, exports, module) {
    'use strict';
    var $=require('jquery'),
        Widget = require('arale-widget/1.2.0/widget');

    /**
     * 定时器
     */
    var _res;
    //定义页面组件类
    var centterHindUtil = Widget.extend({
        //重写父类
        setup: function () {
            centterHindUtil.superclass.setup.call(this);
            $("tbody").delegate(".center-hind","mouseenter",function(){
                $(this).parent().children('.showbj').slideToggle(100);
            });
            $("tbody").delegate(".center-hind","mouseleave",function(){
                $(this).parent().children('.showbj').hide(1);
            });
        },
        events: {
        }
    });
    module.exports = centterHindUtil;
});

