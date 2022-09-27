<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 콘솔</title>
    <meta name="keyword" content="">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Author" content="zifan">
    <meta name="copyright" content="Crackers。All Rights Reserved">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">

</head>

<body class="fixed-sidebar">
<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation" id="leftnav"></nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row ">
            <nav class="navbar navbar-fixed-top" role="navigation" id="topnav"></nav>
        </div>

        <div class="row  border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>뉴스 등록</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index.html">대시보드</a>
                    </li>
                    <li>뉴스</li>
                    <li class="active">등록</li>
                </ol>
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="ibox float-e-margins ">
                <div class="ibox-content p-t-slg">
                    <form name="entity" id="input_form" class="form-horizontal">

                        <div class="form-group">
                            <label class="col-sm-12 col-md-4 col-lg-2 control-label" for="title">
                                <span class="text-danger">* </span>
                                제목
                            </label>
                            <div class="col-sm-12 col-md-7 col-lg-9">
                                <input type="text" id="title" name="title" value="" placeholder="제목을 입력하세요" class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-12 col-md-4 col-lg-2 control-label" for="description">
                                <span class="text-danger">*</span>
                                내용
                            </label>
                            <div class="col-sm-12 col-md-7 col-lg-9">
                                <input type="text" id="description" name="description" value="" placeholder="내용을 입력하세요" class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-12 col-md-4 col-lg-2 control-label" for="address">
                                <span class="text-danger">*</span>
                                URL
                            </label>
                            <div class="col-sm-12 col-md-7 col-lg-9">
                                <input type="text" id="address" name="address" value="" placeholder="URL을 입력하세요" class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-12 col-md-4 col-lg-2 control-label" for="newsTime">
                                <span class="text-danger">*</span>
                                사건 발생 시간
                            </label>
                            <div class="col-sm-12 col-md-7 col-lg-9">
                                <input type="text" id="newsTime" name="newsTime" value="" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm',firstDayOfWeek:1,readOnly:true})" placeholder="날짜와 시간을 선택하세요" class="form-control Wdate" required>
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>


                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">
                                    <i class="fa fa-check"></i>
                                    저장
                                </button>
                                <button class="btn btn-white" type="reset">초기화</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="footer">
            <div class="pull-right">
                10GB of
                <strong>250GB</strong>
                Free.
            </div>
            <div>
                <strong>Copyright</strong>
                Example Company &copy; 2014-2015
            </div>
        </div>
    </div>

</div>

<script src="${ctx}/static/js/jquery-2.1.1.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>
<script> var ctx = '${ctx}';</script>
<script src="${ctx}/static/js/menu-support.js"></script>
<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>
<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${ctx}/static/js/plugins/toastr/toastr.min.js" async></script>
<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/plugins/validate/validate-ko.js"></script>
<script src="${ctx}/static/js/plugins/fileinput/fileinput.js"></script>
<script src="${ctx}/static/js/plugins/fileinput/fileinput_locale_ko.js"></script>
<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>

<script>
    var _ctx = '${ctx}';
    $(".chosen-select").chosen({
        no_results_text: '선택할 아이템이 없습니다',
        width: "100%",
        allow_single_deselect: true,
        disable_search_threshold: 10
    });
    $(document).ready(function () {
        $("#input_form").validate({
            debug: true,
            submitHandler: function (form) {
                addform(form);
            }
        });

        function addform(form) {
            $.ajax({
                url: _ctx + "/news/add",
                type: "post",
                data: $(form).serialize(),
                success: function (data) {
                    if (data.status === '1') {
                        toastr.success('', data.msg);
                        window.location.href = _ctx + "/news/list";
                    } else
                        toastr.error('', data.msg);
                },
                error: function (data) {
                    toastr.error('', '게시 실패');
                }
            });
        }

    });
</script>
</body>
</html>
