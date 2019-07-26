var hasKeyGenerated = false;
var isPrivate = false;
var keyInterval;
var url = "";
var key = "";
var request = "";
var response = "";
var one = false;
var two = false;
var three = false;
var postWeatherDone = false;
var postCurrencyDone = false;

$("#weather").on("click", function(e) {
  buildUrl(e);
  isPrivate = false;
  $(this).toggleClass("category-selected");
  $(".generate-key").removeClass("generate-key-active");
  $(".loading").css("visibility", "hidden");
  if (request === "post") {
    $(".post-body").css("display", "block");
    showPostBody();
  }
});

$("#currency").on("click", function(e) {
  buildUrl(e);
  isPrivate = true;
  $(this).toggleClass("category-selected");
  $(".generate-key").addClass("generate-key-active");
  if (hasKeyGenerated) {
    $(".loading").css("visibility", "visible");
    $(".input-text").val((url += "/" + key));
    if (request === "post") {
      $(".post-body").css("display", "block");
    }
  }
});

$(".requests button").on("click", function(e) {
  $(".requests button").removeClass("request-selected");
  $(this).toggleClass("request-selected");
  request = e.target.id;
  two = true;
  if (one && request === "post") {
    $(".post-body").css("display", "block");
    showPostBody();
  } else {
    $(".post-body").css("display", "none");
  }
});

$(".generate-key").on("click", function() {
  $(this).css("pointer-events", "none");
  if (!hasKeyGenerated) {
    $(".loading").css("visibility", "visible");
    hasKeyGenerated = true;
    key = generateKey();
    key = "u7asdfkjc23";
    keyInterval = setInterval(function() {
      loaded(key);
    }, 2000);
  }
});

$("#send").on("click", function() {
  if (one && two) {
    if (request === "get") {
      $.get(url, function(result) {
        response = result;
        $(".response textarea").val(JSON.stringify(response, null, 2));
        if (isPrivate) {
          $(".only-currency").css("display", "block");
          $("#titulo").text("LA MONEDA");
        } else {
          $(".only-currency").css("display", "none");
          $("#titulo").text("EL TIEMPO");
        }
      });
      three = true;
      $(".web-body div").html('<p class="no-data"><i>no data...</i></p>');
      $("#useApi").css("pointer-events", "all");
    } else if (request === "post") {
      if (!postWeatherDone) {
        postWeather();
      } else if (!postCurrencyDone) {
        if (hasKeyGenerated) {
          postCurrency();
        }
      } else {
        $(".response textarea").val(
          "Post request: success. Make a 'Get' request to see the result."
        );
      }
    }
  }
});

$("#useApi").on("click", function() {
  if (three) {
    if (isPrivate) {
      renderCurrency();
    } else {
      renderWeather();
    }
    $(this).css("pointer-events", "none");
  }
});

function showPostBody() {
  if (isPrivate) {
    $(".post-textarea").val(JSON.stringify(newCurrency(), null, 2));
  } else {
    $(".post-textarea").val(JSON.stringify(newWeather(), null, 2));
  }
}

function newCurrency() {
  var dataPost = {
    id: "5",
    currencyCode: "AUD",
    currencyName: "Australian Dollar",
    currencySymbol: "&#36;",
    value: "1.7709",
    imageUrl: "./images/currency/aud.png"
  };
  return dataPost;
}

function newWeather() {
  var dataPost = {
    id: "5",
    location: "Dunedin",
    country: "New Zealand",
    temperature: "3",
    description: "Snow",
    imageUrl: "./images/weather/snow.png"
  };
  return dataPost;
}

function postCurrency() {
  var dataPost = newCurrency();
  $.ajax({
    url: url,
    data: JSON.stringify(dataPost),
    contentType: "application/json",
    type: "POST",
    success: function(result, status) {
      $(".response textarea").val(
        "Post request: " + status + ". Make a 'Get' request to see the result."
      );
      postCurrencyDone = true;
    }
  });
}

function postWeather() {
  var dataPost = newWeather();
  $.ajax({
    url: url,
    data: JSON.stringify(dataPost),
    contentType: "application/json",
    type: "POST",
    success: function(result, status) {
      $(".response textarea").val(
        "Post request: " + status + ". Make a 'Get' request to see the result."
      );
      postWeatherDone = true;
    }
  });
}

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
  $(".web-body div").html(respondRender);
}

function renderWeather() {
  var respondRender = "";
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
  $(".web-body div").html("<div class='tiempo'>" + respondRender + "</div>");
}

function buildUrl(e) {
  one = true;
  $(".category button").removeClass("category-selected");
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
  if (request === "post") {
    $(".post-body").css("display", "block");
    showPostBody();
  }
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
