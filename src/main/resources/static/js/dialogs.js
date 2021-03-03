function timeoutDemo(){
    Metro.dialog.create({
        title: "Loft Player",
        content: "<div><div class=\"player\"><div class=\"info\"><div><label class=\"slidecontainer\"> <input class=\"slider\" type=\"range\" id=\"volumeFred\" min=\"0\" max=\"100\"></label></div><div class=\"controls\" id=\"controlsFred\"><div class=\"power\" id=\"powerFred\"><i class=\"fa mif-switch\"></i></div><div class=\"previous\" id=\"prevFred\"><i class=\"fa mif-previous mif-2x\"></i></div><div class=\"play\" id=\"playFred\"><i class=\"fa mif-play mif-2x\"></i></div><div class=\"pause\" id=\"pauseFred\"><i class=\"fa mif-pause mif-2x\"></i></div><div class=\"next\" id=\"nextFred\"><i class=\"fa mif-next mif-2x\"></i></div></div></div></div></div>",
        autoHide: 10000,
        width: 600,
        onShow: function(){
            var el = $(this);
            el.addClass("ani-swoopInTop");
            setTimeout(function(){
                el.removeClass("ani-swoopInTop");
            }, 500);
        },
        onHide: function(){
            console.log("hide");
            var el = $(this);
            el.addClass("ani-swoopOutTop");
        }
      });
}

function customsDemo(){
    Metro.dialog.create({
        title: "Customs demo",
        content: "<div>This dialog with customs classes</div>",
        clsDialog: "bg-dark",
        clsTitle: "fg-yellow",
        clsContent: "fg-white",
        clsDefaultAction: "alert"
    });
}

function animateDemo(){
    Metro.dialog.create({
        title: "Animation demo",
        content: "<div>This dialog animated with onShow, onHide events</div>",
        onShow: function(){
            var el = $(this);
            el.addClass("ani-swoopInTop");
            setTimeout(function(){
                el.removeClass("ani-swoopInTop");
            }, 500);
        },
        onHide: function(){
            console.log("hide");
            var el = $(this);
            el.addClass("ani-swoopOutTop");
            setTimeout(function(){
                //el.removeClass("ani-swoopOutTop");
            }, 5000);
        }
    });
}

function actionsDemo(){
    Metro.dialog.create({
        title: "Dialog actions",
        content: "<div>This dialog with custom actions</div>",
        actions: [
            {
                caption: "Yes, i'am",
                cls: "js-dialog-close alert",
                onclick: function(){
                    alert("You choose YES");
                }
            },
            {
                caption: "No, thanks",
                cls: "js-dialog-close",
                onclick: function(){
                    alert("You choose NO");
                }
            }
        ]
    });
}
