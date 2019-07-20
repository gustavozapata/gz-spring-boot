$(".category button").on("click", function(e) {
  $(".category button").removeClass("category-selected");
  $(this).toggleClass("category-selected");
  if (e.target.id === "private") {
    $(".generate-key").toggleClass("generate-key-active");
  } else {
    $(".generate-key").removeClass("generate-key-active");
    $(".loading").css("visibility", "hidden");
  }
});

$(".requests button").on("click", function() {
  $(".requests button").removeClass("request-selected");
  $(this).toggleClass("request-selected");
});

$(".generate-key").on("click", function() {
  $(".loading").css("visibility", "visible");
  setInterval(function() {
    loading();
  }, 5000);
});

function loading() {
  $(".loading p:first-child").text("a key has been generated");
  $(".loading img").css("display", "none");
  $("#key").css("display", "block");
}
