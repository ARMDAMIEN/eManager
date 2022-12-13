
(function ($) {
    "use strict";





    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }


})(jQuery);


/*==================================================================
   [ Carousel ]*/


document.querySelectorAll(".carousel").forEach(carousel => {


    const items = carousel.querySelectorAll(".img-carousel");
    const right = carousel.querySelector(".nav-carousel-right");
    const left = carousel.querySelector(".nav-carousel-left");
    let cmtImg = 0;
    let totalImg = items.length;



    right.addEventListener("click", switchRight);

    function switchRight(){

        if(cmtImg!=totalImg-1){

            items[cmtImg].classList.remove("img-carousel-selected");
            items[cmtImg+1].classList.add("img-carousel-selected");
            cmtImg += 1;
        }else{
            items[cmtImg].classList.remove("img-carousel-selected")
            cmtImg=0;
            items[cmtImg].classList.add("img-carousel-selected")
            console.log(cmtImg)
        }


    }
    left.addEventListener("click", switchLeft);

    function switchLeft(){
        if(cmtImg!=0){
            items[cmtImg].classList.remove("img-carousel-selected");
            items[cmtImg-1].classList.add("img-carousel-selected");
            cmtImg -= 1;
        }else{
            items[cmtImg].classList.remove("img-carousel-selected")
            cmtImg=2;
            items[cmtImg].classList.add("img-carousel-selected")
            console.log(cmtImg)
        }


    }


});




