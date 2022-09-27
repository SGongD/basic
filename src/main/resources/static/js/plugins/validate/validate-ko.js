/**
 * Created by zifan on 2015/10/16.
 */
$.validator.setDefaults({
    ignore: ":hidden",
    success: function (span) {
      if(span.prev().length!=0){
        if (span.prev().is(":radio") || span.prev().is(":checkbox") || $(span).prev().prop("tagName").toLowerCase() == "select") {
            $(span).addClass("hide")
        }
        else {
            $(span).addClass("success fa fa-check");
        }
      }
    },
    highlight: function (element, errorClass) {
        $(element).addClass("error");
        $(element).parent().find("." + errorClass).removeClass("hide success fa fa-check");
    }
});

jQuery.extend(jQuery.validator.messages, {
    required: "필수 항목입니다.",
    remote: "항목을 수정하세요.",
    email: "유효하지 않은 E-Mail주소입니다.",
    url: "유효하지 않은 URL입니다.",
    date: "올바른 날짜를 입력하세요.",
    dateISO: "올바른 날짜(ISO)를 입력하세요.",
    number: "유효한 숫자가 아닙니다.",
    digits: "숫자만 입력 가능합니다.",
    creditcard: "신용카드 번호가 바르지 않습니다.",
    equalTo: "같은 값을 다시 입력하세요.",
    extension: "올바른 확장자가 아닙니다.",
    maxlength: jQuery.validator.format("{0}자를 넘을 수 없습니다. "),
    minlength: jQuery.validator.format("{0}자 이상 입력하세요."),
    rangelength: jQuery.validator.format("문자 길이가 {0} 에서 {1} 사이의 값을 입력하세요."),
    range: jQuery.validator.format("{0} 에서 {1} 사이의 값을 입력하세요."),
    max: jQuery.validator.format("{0} 이하의 값을 입력하세요."),
    min: jQuery.validator.format("{0} 이상의 값을 입력하세요.")
});

jQuery.validator.addMethod("isDigits", function (value, element) {
    return this.optional(element) || /^\d+$/.test(value);
}, "숫자만 입력 가능합니다.");

jQuery.validator.addMethod("isChinese", function (value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);
}, "중국어를 포함합니다。");

jQuery.validator.addMethod("isEnglish", function (value, element) {
    return this.optional(element) || /^[A-Za-z]+$/.test(value);
}, "영문을 포함 합니다");

jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
}, "휴대폰 번호가 유효하지 않습니다");

jQuery.validator.addMethod("isPhone", function (value, element) {
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(element) || (tel.test(value));
}, "전화번호가 유효하지 않습니다");

jQuery.validator.addMethod("isTel", function (value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(element) || tel.test(value) || (length == 11 && mobile.test(value));
}, "연락처를 입력하세요");

$("form").find('*[data-toggle-name]').on('change', function () {
    var val = $(this).find(":selected").attr("data-box-name");
    var $target = $(this).attr('data-toggle-name');
    $($target + " #" + val).removeClass().addClass("show").siblings().removeClass().addClass("hide");
    //$target.find("[data-box]").toggle();
});



