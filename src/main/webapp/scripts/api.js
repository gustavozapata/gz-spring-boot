var hasKeyGenerated = false;
var keyInterval;
var url = "";
var request = "";

$(".category button").on("click", function(e) {
  buildUrl(e);
  $(".category button").removeClass("category-selected");
  $(this).toggleClass("category-selected");
  if (e.target.id === "currency") {
    $(".generate-key").addClass("generate-key-active");
    if (hasKeyGenerated) {
      $(".loading").css("visibility", "visible");
    }
  } else {
    $(".generate-key").removeClass("generate-key-active");
    $(".loading").css("visibility", "hidden");
  }
});

function buildUrl(e) {
  url = "/api/" + e.target.id;
  $(".input-text").val(url);
}

$(".requests button").on("click", function(e) {
  $(".requests button").removeClass("request-selected");
  $(this).toggleClass("request-selected");
  request = e.target.id;
});

$(".generate-key").on("click", function() {
  $(this).css("pointer-events", "none");
  if (!hasKeyGenerated) {
    $(".loading").css("visibility", "visible");
    hasKeyGenerated = true;
    var key = generateKey();
    key = "u7asdfkjc23";
    keyInterval = setInterval(function() {
      loaded(key);
    }, 2000);
  }
});

function loaded(key) {
  $(".loading p:first-child").text("a key has been generated");
  $(".loading img").css("display", "none");
  $("#key")
    .css("display", "block")
    .text(key);
  $(".input-text").val((url += "/" + key));
  clearInterval(keyInterval);
}

function generateKey() {
  return Math.random()
      .toString(36)
      .substr(2, 12);
}

$("#send").on("click", function() {
  if (request === "get") {
    $.get(url, function(result) {
      $(".response textarea").val(JSON.stringify(result, null, "\t"));
    });
  }
});

//a more aggresive unique id
function uuidv4() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(c) {
    var r = (Math.random() * 16) | 0,
      v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}
