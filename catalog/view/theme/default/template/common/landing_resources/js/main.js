(function ($) {
    "use strict";
// debugger;
    // Initiate the wowjs

    // Dropdown on mouse hover
    const $dropdown = $(".dropdown");
    const $dropdownToggle = $(".dropdown-toggle");
    const $dropdownMenu = $(".dropdown-menu");
    const showClass = "show";

    $(window).on("load resize", function () {
        if (this.matchMedia("(min-width: 992px)").matches) {
            $dropdown.hover(
                function () {
                    const $this = $(this);
                    $this.addClass(showClass);
                    $this.find($dropdownToggle).attr("aria-expanded", "true");
                    $this.find($dropdownMenu).addClass(showClass);
                },
                function () {
                    const $this = $(this);
                    $this.removeClass(showClass);
                    $this.find($dropdownToggle).attr("aria-expanded", "false");
                    $this.find($dropdownMenu).removeClass(showClass);
                }
            );
        } else {

            $dropdown.off("mouseenter mouseleave");
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });


    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 500, 'easeInOutExpo');
        return false;
    });


    var dictionary = {
        "en": {
            "firstname_placeholder": "Type your first name",
            "lastname_placeholder": "Type your last name",
            "email_placeholder": "Type your email address",
            "phone_placeholder": "Type your phone number",
            "write_placeholder": "Write your message..",
            "placeholder_passwrod": "Type your password"

        },
        "arb": {
            "firstname_placeholder": "اكتب اسمك الأول",
            "lastname_placeholder": "اكتب اسمك الأخير",
            "email_placeholder": "اكتب البريد الالكتروني الخاص بك",
            "phone_placeholder": "اكتب رقم جوالك",
            "write_placeholder": "اكتب رسالتك..",
            "placeholder_passwrod": "اكتب كلمة المرور الخاصة بك"
        }
    };


    function switchLang(lang) {
        localStorage.setItem('livixia-language', lang);

        $("select#switchlang").find("option#" + lang + "").attr("selected", true);
        $("select#switchlang2").find("option#" + lang + "2").attr("selected", true);


        $("[data-" + lang + "]").text(function (i, e) {
            return $(this).data(lang);
        });


        $('#sumbit-button').attr('value', $('#sumbit-button').attr("data-" + lang + ""))

        if (lang == "arb") {
            $('body').addClass("arabic")
            $('#sheet2').remove();
            $('head').append('<link href="catalog/view/theme/default/template/common/landing_resources/css/arabic-font.css" rel="stylesheet" id="sheet1" />');

        } else {
            $('body').removeClass("arabic")
            $('#sheet1').remove();
            $('head').append('<link href="catalog/view/theme/default/template/common/landing_resources/css/en-font.css" rel="stylesheet" id="sheet2" />');
        }


    }

    var set_lang = function (lang) {
        $("[data-translate]").each(function () {
            if ($(this).is("input")) {
                $(this).attr('placeholder', dictionary[lang][$(this).data("translate")])
            } else {
                $(this).text(dictionary[lang][$(this).data("translate")])
            }
        })
    };


    var lang = localStorage.getItem('livixia-language')

    if (lang == null || lang == 'null') {
        console.log(lang);
        localStorage.setItem('livixia-language', "arb");
        lang = "arb";
    }
    console.log(lang);
    switchLang(lang);
    set_lang(lang);
    calcPrice(lang, "1")


    $("#switchlang").on('change', function () {

        switchLang($(this).val())
        set_lang($(this).val());
    });

    $("#switchlang2").on('change', function () {

        switchLang($(this).val())
        set_lang($(this).val());
    });


    $('.nav-link').click(function (e) {
        $('.nav-link').each(function () {
            $(this).removeClass('active');
        });
        $(this).addClass("active");
    });


    function calcPrice(lang, month) {
        if (month == "1") {
            if (lang == "en") {
                $("#price1").text('15.00 SAR');
                $("#price1m").text('15.00 SAR');

            } else if (lang == "arb") {
                $("#price1").text('15.00 ر.س');
                $("#price1m").text('15.00 ر.س');
            }

            if (lang == "en") {
                $("#price2").text('26.00 SAR');
                $("#price2m").text('26.00 SAR');

            } else if (lang == "arb") {
                $("#price2").text('26.00 ر.س');
                $("#price2m").text('26.00 ر.س');
            }

            if (lang == "en") {
                $("#price3").text('36.00 SAR');
                $("#price3m").text('36.00 SAR');
            } else if (lang == "arb") {
                $("#price3").text('36.00 ر.س');
                $("#price3m").text('36.00 ر.س');
            }

        } else if (month == "3") {

            if (lang == "en") {
                $("#price1").text('45.00 SAR');
                $("#price1m").text('45.00 SAR');
            } else if (lang == "arb") {
                $("#price1").text('45.00 ر.س');
                $("#price1m").text('45.00 ر.س');
            }

            if (lang == "en") {
                $("#price2").text('78.00 SAR');
                $("#price2m").text('78.00 SAR');
            } else if (lang == "arb") {
                $("#price2").text('78.00 ر.س');
                $("#price2m").text('78.00 ر.س');
            }

            if (lang == "en") {
                $("#price3").text('108.00 SAR');
                $("#price3m").text('108.00 SAR');
            } else if (lang == "arb") {
                $("#price3").text('108.00 ر.س');
                $("#price3m").text('108.00 ر.س');
            }

        } else if (month == "12") {
            if (lang == "en") {
                $("#price1").text('180.00 SAR');
                $("#price1m").text('180.00 SAR');
            } else if (lang == "arb") {
                $("#price1").text('180.00 ر.س');
                $("#price1m").text('180.00 ر.س');
            }

            if (lang == "en") {
                $("#price2").text('312.00 SAR');
                $("#price2m").text('312.00 SAR');
            } else if (lang == "arb") {
                $("#price2").text('312.00 ر.س');
                $("#price2m").text('312.00 ر.س');
            }

            if (lang == "en") {
                $("#price3").text('432.00 SAR');
                $("#price3m").text('432.00 SAR');
            } else if (lang == "arb") {
                $("#price3").text('432.00 ر.س');
                $("#price3m").text('432.00 ر.س');
            }

        }
    }


    $('.month').click(function () {
        var month = $(this).attr('data-month');

        $('.month').each(function () {
            $(this).removeClass('active');
        });
        $(this).addClass("active");

        calcPrice(lang, month)
    });


})(jQuery);


new WOW().init();