<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
        
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>
    <script type='text/javascript' src='js/organictabs.jquery.js'></script>
    <script type='text/javascript'>

    (function($) {

    $.organicTabs = function(el, options) {

        var base = this;
        base.$el = $(el);
        base.$nav = base.$el.find(".nav");

        base.init = function() {

            base.options = $.extend({},$.organicTabs.defaultOptions, options);

            // Accessible hiding fix
            $(".hide").css({
                "position": "relative",
                "top": 0,
                "left": 0,
                "display": "none"
            }); 

            base.$nav.delegate("li > a", "click", function() {

                // Figure out current list via CSS class
                var curList = base.$el.find("a.current").attr("href").substring(1),

                // List moving to
                    $newList = $(this),

                // Figure out ID of new list
                    listID = $newList.attr("href").substring(1),

                // Set outer wrapper height to (static) height of current inner list
                    $allListWrap = base.$el.find(".list-wrap"),
                    curListHeight = $allListWrap.height();
                $allListWrap.height(curListHeight);

                if ((listID != curList) && ( base.$el.find(":animated").length == 0)) {

                    // Fade out current list
                    base.$el.find("#"+curList).fadeOut(base.options.speed, function() {

                        // Fade in new list on callback
                        base.$el.find("#"+listID).fadeIn(base.options.speed);

                        // Adjust outer wrapper to fit new list snuggly
                        var newHeight = base.$el.find("#"+listID).height();
                        $allListWrap.animate({
                            height: newHeight
                        });

                        // Remove highlighting - Add to just-clicked tab
                        base.$el.find(".nav li a").removeClass("current");
                        $newList.addClass("current");

                    });

                }   

                // Don't behave like a regular link
                // Stop propegation and bubbling
                return false;
            });

        };
        base.init();
    };

    $.organicTabs.defaultOptions = {
        "speed": 300
    };

    $.fn.organicTabs = function(options) {
        return this.each(function() {
            (new $.organicTabs(this, options));
        });
    };

    })(jQuery);
    
    </script>
    
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>

        <div id="grailsLogo" class="logo"><a href="/user/home"><img src="${resource(dir:'images',file:'biblivro_logo.png')}" alt="Grails" border="0" /></a></div>

        <div id=menu class="dialog" align="right" style="margin-right:0px;width:90%;">
        <g:if test="${session.user}">            
            <g:link action="logout" controller="auth">Logout</g:link>            
        </g:if>
        </div>
        <g:layoutBody />
    </body>
</html>

