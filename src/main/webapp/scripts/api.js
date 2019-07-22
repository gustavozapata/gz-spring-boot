var hasKeyGenerated = false;
var keyInterval;
var url = "";
var request = "";
var response = "";

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
    hasKeyGenerated = false;
    $(".generate-key").removeClass("generate-key-active");
    $(".loading").css("visibility", "hidden");
  }
});

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

$("#send").on("click", function() {
  if (request === "get") {
    $.get(url, function(result) {
      response = result;
      $(".response textarea").val(JSON.stringify(response, null, 2));
      if (hasKeyGenerated) {
        $(".only-currency").css("display", "block");
        $("#titulo").text("LA MONEDA");
      } else {
        $(".only-currency").css("display", "none");
        $("#titulo").text("EL TIEMPO");
      }
    });
  }
});

$("#useApi").on("click", function() {
  if (hasKeyGenerated) {
    renderCurrency();
  } else {
    renderWeather();
  }
  $(this).css("pointer-events", "none");
});

function renderCurrency() {
  var respondRender = "";
  for (let i = 0; i < response.length; i++) {
    respondRender +=
      "<div class='currency-item'><img src='" +
      response[i].imageUrl +
      "' alt='" +
      response[i].currencyCode +
      " icon' /><span class='currency-info'><p>" +
      response[i].currencyCode +
      "</p><br /><p>" +
      response[i].currencyName +
      "</p></span><span class='currency-value'><p>" +
      response[i].currencySymbol +
      "</p><p>" +
      response[i].value +
      "</p></span></div>";
  }
  $(".web-body div").replaceWith(respondRender);
}

function renderWeather() {
  var respondRender = "<div class='tiempo'>";
  for (let i = 0; i < response.length; i++) {
    respondRender +=
      "<div class='currency-item'><img src='" +
      response[i].imageUrl +
      "' alt='" +
      response[i].description +
      " icon' /><span class='currency-info'><p>" +
      response[i].location +
      "</p><br /><p>" +
      response[i].country +
      "</p></span><span class='currency-value weather-value'><p id='degrees'>" +
      response[i].temperature +
      "<span id='celsius'>&deg;C</span></p><br/><p id='desc'>" +
      response[i].description +
      "</p></span></div>";
  }
  $(".web-body div").replaceWith(respondRender + "</div>");
}

function buildUrl(e) {
  // url = "/api/" + e.target.id;
  url = "http://localhost:8080/api/" + e.target.id;
  $(".input-text").val(url);
}

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

//a more aggresive unique id
function uuidv4() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(c) {
    var r = (Math.random() * 16) | 0,
      v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}
