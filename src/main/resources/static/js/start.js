var setTilesAreaSize = function(){
    var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
    var groups = $(".tiles-group");
    var tileAreaWidth = 80;
    $.each(groups, function(){
        if (width <= Metro.media_sizes.LD) {
            tileAreaWidth = width;
        } else {
            tileAreaWidth += $(this).outerWidth() + 80;
        }
    });

    $(".tiles-area").css({
        width: tileAreaWidth
    });

    if (width > Metro.media_sizes.LD) {
        $(".start-screen").css({
            overflow: "auto"
        })
    }
};

setTilesAreaSize();
updateTimer();

$.each($('[class*=tile-]'), function(){
    var tile = $(this);
    setTimeout(function(){
        tile.css({
            opacity: 1,
            "transform": "scale(1)",
            "transition": ".3s"
        }).css("transform", false);

    }, Math.floor(Math.random()*500));
});

$(".tiles-group").animate({
    left: 0
});

$(window).on(Metro.events.resize + "-start-screen-resize", function(){
    setTilesAreaSize();
});

$(window).on(Metro.events.mousewheel, function(e){
    var up = e.deltaY < 0 ? -1 : 1;
    var scrollStep = 50;
    $(".start-screen")[0].scrollLeft += scrollStep * up;
});

function updateHouseStats() {

    updateInfoFor(15, "#currentTemp", 1.0, "&nbsp;C");
    updateInfoFor(10, "#laundryTemp", 1.0, "&nbsp;C");
    updateInfoFor(11, "#kitchenTemp", 1.0, "&nbsp;C");
    updateInfoFor(12, "#entranceTemp", 1.0, "&nbsp;C");
    updateInfoFor(13, "#masterBedTemp", 1.0, "&nbsp;C");
    updateInfoFor(14, "#garageTemp", 1.0, "&nbsp;C");
    updateInfoFor(20, "#marcOfficeTemp", 1.0, "&nbsp;C");
    updateInfoFor(22, "#bethOfficeTemp", 1.0, "&nbsp;C");

    updateInfoFor(38, "#mainPower", 0.001, "&nbsp;kw");
    updateInfoFor(23, "#hotWaterPower", 0.001, "&nbsp;kw");
    updateInfoFor(35, "#geothermalPower", 0.001, "&nbsp;kw");

}

function updateInfoFor(feedId, tagId, multiplier, suffix) {

	$.ajax({method: "GET", 
            url: "http://emoncms.athome/emoncms/feed/value.json",
            data: {
		        id : feedId,
                apikey : "d460194eaa7cc9012c9bf285de892fcd"
            }
	}).then( 
        function(response) {
            var value = response * multiplier;
		var html = value.toFixed(2) + suffix
		$(tagId).html(html);
	});
}

function updateTimer() {
	setInterval(function() {
		updateHouseStats();
	}, 5000);
}
