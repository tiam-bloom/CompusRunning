var $Tz_wrap = $('#Tz_wrap');
var $upLoad = $('#upload');
var $send = $('#send');
var $input = $('#input');
var $rR = $('#rotateRightBtn');
var $rL = $('#rotateLeftBtn');
var cropper;
//初始化
init();
//拖拽上传
drag();
//图片操作
oper();

//打开本地图片
function init() {
    //绑定
    var imgData = '';
    cropper = new ImageCropper(500, 500, 180, 180);
    cropper.setCanvas("Tz_wrap");
    cropper.addPreview("preview180");
    //检测用户浏览器是否支持imagecropper插件
    if (!cropper.isAvaiable()) {
        alert("您的浏览器并不支持图像剪裁");
    }
    ;
    $('body').on({
        //进入
        dragenter: function (e) {
            e = e || window.event;
            e.preventDefault();
            e.stopPropagation();
        },
        //离开
        dragleave: function (e) {
            e = e || window.event;
            e.preventDefault();
            e.stopPropagation();
        },
        //在内部移动
        dragover: function (e) {
            e = e || window.event;
            e.preventDefault();
            e.stopPropagation();
        },
        drop: function (e) {
            e = e || window.event;
            e.preventDefault();
            e.stopPropagation();
        },
    });
};

function drag() {
    $Tz_wrap.get(0).ondrop = function (e) {
        e.preventDefault();
        //获取拖过来的文件
        var fs = e.dataTransfer.files;
        var _type = fs[0].type;
        if (_type.indexOf("image") != -1) {//判断他是不是图片文件
            var fd = new FileReader();
            cropper.loadImage(fs[0])
            $('.ptit').css({zIndex: -1})
            fd.readAsDataURL(fs[0]);
        }
        ;
    };
};

function oper() {
    /*上传图片点击事件 start*/
    $upLoad.click(function () {
        console.log("点击了上传图片");
        $input.click();
    });
    $input.click(function () {
        $(this).change(function () {
            $('.ptit').css({zIndex: -1})
            selectImage(this.files)
        })
    })

    function selectImage(fileList) {
        cropper.loadImage(fileList[0]);
    };
    /*上传图片点击事件 end*/
    /*旋转图片 start*/
    $rL.click(function () {
        cropper.rotate(-90)
    });
    $rR.click(function () {
        cropper.rotate(90)
    });
    /*旋转图片 end*/
    /*上传图片 start*/
    $send.click(function () {
        imgData = cropper.getCroppedImageData(180, 180);
        $('#imgg').attr('src', imgData)
        //console.log("上传了：" + imgData);
        $.ajax({
            //async:"true",
            type:"POST",
            url: "/addPicture",
            data: {bigHead: imgData},
            dataType: String,
            success: function (data) {
                // alert(data);
                // var r=confirm(data);
                // if (r==true){
                //     location.href="/home.action";
                // }
                // else{
                //     return false;
                // }
            }
        })
    })
    /*上传图片 end*/
}