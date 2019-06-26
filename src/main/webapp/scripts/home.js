$("#gitinput").on("click", function() {
   $(this).select();
});

$(".copy").on("click", function() {
    $("#gitinput").select();
    $(this).focus();
    document.execCommand("copy");
});