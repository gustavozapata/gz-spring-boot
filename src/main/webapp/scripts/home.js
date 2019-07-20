$(".input-text").on("click", function() {
   $(this).select();
});

$(".copy").on("click", function() {
    $(".input-text").select();
    $(this).focus();
    document.execCommand("copy");
});