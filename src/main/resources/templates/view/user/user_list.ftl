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
    <meta name="copyright" content="胡桃夹子。All Rights Reserved">
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
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
                    <h2>사용자 관리</h2>
                    <ol class="breadcrumb">
                        <li>
                            <a href="${ctx}/index">대시보드</a>
                        </li>
                        <li class="active">
                            사용자 목록
                        </li>

                    </ol>
                </div>
            </div>

            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="ibox-content m-b-sm border-bottom">
                    <div class="row">
                        <div class="col-lg-8 col-md-6 col-sm-4 ">
                            <a id="editable-sample_new" class="btn btn-primary" data-toggle="modal"  href="#modal-form">
                                사용자 추가 <i class="fa fa-plus"></i>
                            </a>
                        </div>
                        <div class=" col-lg-4 col-md-6 col-sm-8 " >
                                <div class="tablesearch pull-right m-t-xs">
                                   <div class="table-td">
                                        <div class="input-group" >
                                                <input type="text" class="input-sm form-control" id="keywords" name="keywords" value="" placeholder="검색어를 입력하세요">
                                                <span class="input-group-btn"><button type="button" class="btn btn-sm btn-primary" id="queryUserBtn">검색</button></span>
                                        </div>
                                   </div>
                                   <div class="table-td m-l-sm pull-right">
                                       <a  class="btn btn-sm btn-primary dropdown-toggle" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"> 고급검색 <span class="caret"></span></a>
                                   </div>
                                </div>
                        </div>
                    </div>
                    <div class="collapse" id="collapseExample">
                        <div class="border-top m-t-md m-b-none sidedown-box" >
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label class="control-label" for="order_id">사용자 이름</label>
                                                <input type="text" id="order_id" name="order_id" value="" placeholder="사용자 이름을 입력하세요" class="form-control">

                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label class="control-label" >전화번호</label>
                                                <input type="text" id="order_id" name="order_id" value="" placeholder="전화번호를 입력하세요" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="control-label" >회사</label>
                                        <select data-placeholder="-- 소속회사 선택하세요 --" class="chosen-select" tabindex="2">
                                            <option value=""></option>
                                            <option value="1">베이직인터내셔널</option>
                                            <option value="2">비드넷씨엔씨</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="control-label" >역할</label>
                                        <select data-placeholder="-- 역할을 선택하세요 --" class="chosen-select" tabindex="2">
                                            <option value=""></option>
                                            <option value="1">사용자</option>
                                            <option value="2">관리자</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">

                                <div class="col-sm-4 col-sm-push-8 text-right">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-search"></i> 검색</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox" id="ibox">
                            <#include "/view/user/user_list_page.ftl"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer">
                <div class="pull-right">
                    10GB of <strong>250GB</strong> Free.
                </div>
                <div>
                    <strong>Copyright</strong> Example Company &copy; 2014-2015
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modal-form2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
            </div>
        </div>
    </div>


    <div class="modal fade" id="modal-form" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                    <h4 class="modal-title">사용자 추가</h4>
                </div>
                <#include "/view/user/user_add.ftl"/>
            </div>
        </div>
    </div>

    <div class="modal fade" id="user-edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary">
                    <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                    <h4 class="modal-title">사용자 수정</h4>
                </div>
                <#include "/view/user/user_edit_form.ftl"/>
            </div>
        </div>
    </div>


    <div class="modal inmodal fade" id="myModa-reset" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">비밀번호 변경</h4>
                    <small>인증코드는 SMS로 사용자 휴대폰에 전송됩니다.</small>
                </div>
                <div class="modal-body">
                    <form name="resetform" id="resetform">
                        <div class="form-group">
                            <label class="control-label" for="order_id">비밀번호 입력</label>
                            <input name="userid" id="userid" type="hidden" value="">
                            <input type="text" id="restcode" name="restcode" maxlength="6" placeholder="비밀번호를 입력하세요" class="form-control" value="${user.password}">
                        </div>
                        <div class="form-group m-t-sm" >
                                <button class="btn btn-md btn-primary " type="submit"><strong>확인</strong></button>
                                <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">취소</button>
                        </div>
                    </form>
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
    <script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js" async></script>
    <script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/plugins/validate/validate-ko.js" ></script>
    <script>
    var _ctx = '${ctx}';

    $(".chosen-select").chosen({no_results_text:'선택할 항목이 없습니다',width:"100%",disable_search_threshold:10});
    function appendSelect(){
        var json=[
            {name:'선택1',val:"1"},
            {name:'선택2',val:"1"},
            {name:'선택3',val:"1"},
            {name:'선택4',val:"1"},
            {name:'선택5',val:"1"},
            {name:'선택6',val:"1"},
            {name:'선택7',val:"1"}
        ];

        var select=$("#city2");
        for(var i=0; i<json.length; i++){
            var op=document.createElement('option');
            op.value=json[i].val;
            op.text=json[i].name;
            select.append(op);
        }

        $(".chosen-select").trigger("chosen:updated");

    }
    function setState (ev) {
        var el = $(ev);
        var elclass=el[0].className;
        var id=el.data("id");

        if (el.hasClass("btn-default")) {
            el.removeClass("btn-default").addClass("btn-primary");
        } else {
            el.removeClass("btn-primary").addClass("btn-default");
        }
        toastr.success('사용자 상태를 변경했습니다', '작업성공');
    };

    function handleOnId(e) {
        e.value = e.value.replace(/[^A-Za-z0-9]/ig, '');
    }

    function handleOnName(e) {
        e.value = e.value.replace();
    }

    function getFormData($form){
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }


        $(document).ready(function () {

            $("#add").validate({

                rules: {
                    usseracc: {
                        required: true,
                        isEngDi: true,
                        rangelength: [6, 12],
                    },
                    userName: {
                        required: true,
                        rangelength: [3, 6]
                    },
                    phoneNum: {
                        required: true,
                        isMobile:true
                    },
                    sphoneNum: {
                        required: true,
                        equalTo: "#phoneNum"
                    },
                    business: "required",
                    jiaos: "required",
                    round: "required"
                },
                messages: {
                    usseracc: {
                        required: "아이디를 입력하세요",
                        rangelength: jQuery.validator.format("6-12자 사이의 영어/숫자를 입력하세요"),
                        remote: jQuery.validator.format("{0} is already in use"),
                        isEngDi: jQuery.validator.format("영문과 숫자만 입력 가능합니다")
                    },
                    userName: {
                        required: "이름을 입력하세요",
                        minlength: jQuery.validator.format("Enter at least {0} characters")
                    },
                    phoneNum: {
                        required: "전화번호를 입력하세요",
                        isMobile: "유효한 전화번호를 입력하세요"
                    },
                    sphoneNum: {
                        required: "전화번호를 다시 한번입력하세요",
                        equalTo: "전화번호가 일치하지 않습니다"
                    },
                    business: "회사를 선택하세요",
                    jiaos: "역할을 선택하세요",
                    terms: " "
                },
                debug: true,
                submitHandler: function (form) {
                    userAddform(form);
                }
            });


            $("#queryUserBtn").click(function () {
                user_list_page();
            });

            function user_list_page() {
                $.ajax({
                    url: "/view/user/user_list_page",
                    type: "POST",
                    data: {"keywords": $("#keywords").val()},
                    success: function (data) {
                        $('#ibox').html(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        toastr.error('', '조회 에러');
                    }
                });
            }

        function userAddform(form) {
            var $form = $('#add')
            var data = getFormData($form)
            $.ajax({
                url: "/view/user/user_add",
                type: "post",
                data: data,
                success: function (data) {
                    swal("사용자를 추가 했습니다", "", "success");
                }
            });
        }


            $("#resetform").validate({
                rules: {
                    restcode: {
                        required: true,
                        rangelength: [6, 6]
                    }
                },
                messages: {
                    restcode: {
                        required: "비밀번호를 입력하세요",
                        rangelength: jQuery.validator.format("숫자 6자로 이루어진 비밀번호를 입력하세요"),
                        remote: jQuery.validator.format("{0} is already in use")
                    }
                },
                //debug: true,
                submitHandler: function (form) {
                    setform(form);
                }
            });

            function setform(form) {
                $.ajax(
                    {
                        url: "?",
                        type: "post",
                        data: $(form).serialize(),
                        success: function (data) {
                            toastr.success('비밀번호를 SMS로 보냈습니다','작업성공');
                            $("#myModa-reset").modal("hide");
                        },
                        error:function(error){
                            $("#myModa-reset").modal("hide");
                            toastr.error('비밀번호를 SMS로 보냈지 못했습니다！','작업실패');
                        }
                    }
                );
                return false;
            }

            $("#myModa-reset").on('show.bs.modal', function (event) {
                var button=$(event.relatedTarget);
                var userid=button.data("userid");
                $("#userid").val(userid);
                $('#codeimg').attr("src","http://www.wulingtest.com:8780/jcaptcha")
                alert("userid = "+ userid);
            });





            $("#myModa-reset").on('hidden.bs.modal', function (event) {
                $(this).find("input").val("");
            });

            $(document).on('click','#editable-sample button.delete', function () {
                var row=$(this).parents("tr")[0];
                var userid=$(this).data("userid");
                    swal({
                        title: "정말 삭제 하시겠습니까?",
                        text: "삭제후 복구 할수 없습니다",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#1ab394",
                        confirmButtonText: "삭제",
                        closeOnConfirm: false
                    }, function () {
                        row.className="animated bounceOut";
                        setTimeout(function(){
                            row.className="animated fadeInLeft";
                            swal("삭제 실패", "아이디="+userid+" “삭제에 실패 했습니다","error");
                            },1000
                        )
                    });
            });
    });

    </script>
</body>
</html>
