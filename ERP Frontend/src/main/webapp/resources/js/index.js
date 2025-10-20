function gtag() {
    dataLayer.push(arguments)
}

function contact() {
    var e = grecaptcha.getResponse(1);
    "" == document.getElementById("fullname").value ? (document.getElementById("nameerror").innerHTML = "Please enter your name.", document.getElementById("fullname").focus()) : /^[a-zA-Z-,](\s{0,1}[a-zA-Z-, ])*[^\s]$/.test(document.getElementById("fullname").value) ? "" == document.getElementById("email").value ? (document.getElementById("emailerror").innerHTML = "Please enter your E-mail address.", document.getElementById("email").focus()) : 0 == /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test(document.getElementById("email").value) ? (document.getElementById("emailerror").innerHTML = "Please enter a valid E-mail address.", document.getElementById("email").focus()) : "" == document.getElementById("contactnumber").value ? (document.getElementById("contacterror").innerHTML = "Please enter a valid contact number.", document.getElementById("contactnumber").focus()) : /^[0-9]+$/.test(document.getElementById("contactnumber").value) ? document.getElementById("contactnumber").value.length < 10 || document.getElementById("contactnumber").value.length == 11 || document.getElementById("contactnumber").value.length == 12 ||document.getElementById("contactnumber").value.length > 13  ? (document.getElementById("contacterror").innerHTML = "Contact number should be 10 or 13 digits.", document.getElementById("contactnumber").focus()) : /^\s*$/g.test(document.getElementById("message").value) || -1 != document.getElementById("message").value.indexOf("\n") ? (document.getElementById("messagerror").innerHTML = "Please enter the message.", document.getElementById("message").focus()) : 0 == e.length ? document.getElementById("c1error").innerHTML = "Please fill captcha." : document.contactform.submit() : (document.getElementById("contacterror").innerHTML = "Please enter a valid contact number.", document.getElementById("contactnumber").focus()) : (document.getElementById("nameerror").innerHTML = "Please enter valid characters.", document.getElementById("fullname").focus())
}

function clearval() {
    "" != document.getElementById("fullname").value && (document.getElementById("nameerror").innerHTML = ""), "" != document.getElementById("email").value && (document.getElementById("emailerror").innerHTML = ""), "" != document.getElementById("contactnumber").value && (document.getElementById("contacterror").innerHTML = ""), "" != document.getElementById("message").value && (document.getElementById("messagerror").innerHTML = "")
}

function openNav() {
    document.getElementById("mySidenav").style.width = "250px"
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0"
}

function add_chatinline() {
    var e = document.createElement("script");
    e.async = !0, e.src = "https://mylivechat.com/chatinline.aspx?hccid=81400524";
    var t = document.getElementsByTagName("script")[0];
    t.parentNode.insertBefore(e, t)
}
window.dataLayer = window.dataLayer || [], gtag("js", new Date), gtag("config", "UA-122650904-1"), $(window).load(function() {
    $(".se-pre-con").fadeOut("slow")
}), $(document).ready(function() {
    $(".modal").on("hidden.bs.modal", function(e) {
        setTimeout(function() {
            $('[data-target="#' + e.currentTarget.id + '"]').blur()
        }, 100)
    }), "" != document.getElementById("errormessage").value && $("#modalid").modal("show"), $(window).scroll(function() {
        $(window).scrollTop() > 300 ? $(".header_bg").css("background", "#150d0e") : $(".header_bg").css("background", "transparent")
    }), $("#fullpage").fullpage({
        anchors: ["index", "howitworks", "features", "downloadapp", "products", "contact"],
        navigation: !0,
        navigationPosition: "right",
        menu: "#menu",
        slidesNavigation: !0,
        scrollOverflow: !0,
        scrollOverflowReset: !0,
        scrollBar: !0,
        slideSelector: ".in_slide",
        responsiveWidth: 767,
        afterRender: function() {}
    }), $(".mobileapp_slider").slick({
        dots: !1,
        arrows: !1,
        infinite: !0,
        speed: 500,
        fade: !0,
        autoplay: !0,
        autoplaySpeed: 2e3,
        cssEase: "linear"
    })
});
var xmlHttp = null;