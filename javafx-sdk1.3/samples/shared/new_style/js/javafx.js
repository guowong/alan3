$(function() {
	//--- Add the has script class to the body.
	scriptEnabled();
	
	
	//--- TUTORIALS 
	attachToSourceCodeBlocks();
	
	
	//--- DOWNLOAD
	attachToDownloadButton();
	attachToInputBoxes();
	attachToGetStarted();
	attachToHomeDownload();
	
	//--- GALLERY
	setTimeout(attachToGallery, 1);
	
	//--- HOMEPAGE
	attachToNewsTicker();
	
	//--- POPUP
	attachToCloseButton();
	
	if($.ifixpng != null) {
	   $.ifixpng('../images/pixel.gif')
	   $('img[@src$=.png], div.optionswrap, p.slider').ifixpng();
	}
	
});

function attachToNewsTicker() {
   var ticker = $("dl.news-ticker");
   if(!ticker.length)return;
   var id;
   var items = ticker.children("dd");
      items.hover(function() {
         clearTimeout(id);
      }, startTicker);
   
   startTicker();
   
   function startTicker() {   
      id = setTimeout(function() {
         var curr = ticker.children("dd.on");
         var next = curr.next();
         if(!next.length) next = $(items[0]);
         
         //--- Transition the current one out.
         curr
            .css({opacity: 1})
            .animate({ top: "0px", opacity: 0 }, 500, "swing", function() {
               curr
                  .removeClass("on")
                  .addClass("off");
               
               //--- Transition the new one in.
               next
                  .removeClass("off")
                  .addClass("on")
                  .css({top: "25px", opacity: 0})
                  .animate({top: "15px", opacity: 1}, 500, "swing");
            })
            
         startTicker();
      }, 5000);
      
   }
}

// This allows us to toggle elements in css based on whether this class exists or not.
function scriptEnabled() {
    $("BODY").addClass("script-enabled");
}

// When you click on a code header, collapse or expand it depending on the presence of a class.
function attachToSourceCodeBlocks() {
    $(".code-header").click(function() {
        $(this).parent().toggleClass("collapsed");
    });
}

// Start the download transition.
// This is where you would use script to override the form and handle the download with a transition.
function attachToDownloadButton() {
    var btn = $("#downloads #download_button");
    if(!btn) return;
    
    btn.click(function(ev) {
       //--- Transition in thank you.
       var postdownload_div = $("#post_download");
       var iNewHeight = postdownload_div.height();
       
       $("#pre_download")
        .animate({height: iNewHeight}, 500);
       
       
       postdownload_div
        .height(0)
        .css("visibility", "visible")
        .animate({"height": iNewHeight}, 1500, "swing", function() {
            $("#post_download").css("position", "static");
            $("#pre_download").hide();
        });
       
       //--- Don't cancel the form submit!
       //ev.preventDefault();
    });

}

function attachToInputBoxes() {
   
   $(".fieldwrap INPUT")
      .focus( function() {
         if(this.value == $(this).attr("title")) {
            this.value = '';
         }
      })
      .blur( function() {
         if(this.value == '') {
            this.value = $(this).attr("title");
         }
      })
      .each( function(i) {
         this.value = $(this).attr("title");
      });
}


function attachToGetStarted() {
	
	/* 
	 * starting out with visibility hidden on #pre_download
	 * - get height as rendered invisibly
	 * - set height = 0
	 * - animate height to previously rendered value
	 * 
	*/
	$('#homepage #menu h3').click(function(ev) {
		
		// disable/enable gallery animation
		$('ul.gallery').toggleClass('disabled');
		
		var preDown  = $('#pre_download');
		
		// this value will be different across browsers
		var preRendHeight = preDown.height();
		
		if(preDown.css('visibility') == "hidden") {
			
			preDown.height(0)
				.css('visibility', 'visible')
				.animate({
					height: preRendHeight + "px"
				}, 600, "linear", function() {
					$(this).height("auto");
				});
			
		} else {
			
			// not hidden, we need to hide it
			// animate height to 0, then visibility hidden
			
			// hide #pre_download
			preDown.animate({
				height: "0px"
			}, 600, function(){
					preDown
						.css('visibility', 'hidden')
						.height(preRendHeight);
				});
			
		}
		
		ev.preventDefault();
	});
	
}

function attachToHomeDownload() {
	
	var btn = $("#homepage #download_button");
	if(!btn) return;
	
	btn.click(function(ev) {
		
		// unbind original show download
		$('#homepage #menu h3').unbind("click");
		
		var preDown  = $('#pre_download');
		var postDown = $('#post_download');
		
		// this value will be different across browsers
		preRendHeight = preDown.height();
		postRendHeight = postDown.height();
		
		// hide pre download - height to 0, vis hidden, height back to rendered
		preDown.animate({height: "0px"}, 600, function(){
			preDown
				.css('visibility', 'hidden')
				.height(preRendHeight);
			
			postDown.height(0)
				.css('visibility', 'visible')
				.animate({
					height: postRendHeight + "px"
				}, 600);
		});
		
		// bind show/hide thankyou
		$('#homepage #menu h3').click(function(){
			
			// disable/enable gallery animation
			$('ul.gallery').toggleClass('disabled');
			
			
			if(postDown.css('visibility') == "hidden") {
				postDown.height(0)
					.css('visibility', 'visible')
					.animate({
						height: postRendHeight + "px"
					}, 600);
					
			} else {
				// not hidden, we need to hide it
				// animate height to 0, then visibility hidden
				
				// hide #post_download
				postDown.animate({
					height: "0px"
				}, 600, function(){
						postDown
							.css('visibility', 'hidden')
							.height(postRendHeight);
					});
					
			}
			
		});
		
		//--- Don't prevent default, we want to submit the form to the iframe.
		//ev.preventDefault();
	});
	
}
var pageNames=["New & Featured", "Beginner", "Intermediate", "Advanced & More"];
function addPagingComponent(pageCount) {
	if(pageCount <= 1)return;

	var pageUl = $("<ul class='gallery-paging'></ul>");
	pageUl.append($("<li class='prev'></li>"));
	
	for(var c=0,curr;c<pageCount;c++) {
		curr = $("<li><div>" + pageNames[c] + "</div></li>")
			.attr("page", c)
			.addClass("paging-" + c);
		if(c == 0){
			curr.addClass("current");
		}
		pageUl.append(curr);
	}
	
	pageUl.append($("<li class='next'></li>"));
	
	
	
	$("ul.gallery")
	    .before( pageUl.clone().addClass("top") )
	    .after( pageUl.addClass("bottom") );
	
	$("ul.gallery-paging li")
	    .click(pageClick);
	
	function pageClick() {
		var prev,next;
		var current = $("ul.gallery-paging li.current");
		var currIndex = current.attr("page")*1;
		
		if(this.className == "next") {
			var nextSib = current.next();
			if(nextSib.hasClass("next")) return;
				next = nextSib.attr("page")*1;
				
		} else if(this.className == "prev") {
			if(currIndex <= 0) return;
			var prevSib = current.prev();
			if(prevSib.hasClass("prev")) return;
			next = prevSib.attr("page")*1;
		} else {
			next = $(this).attr("page")*1;
		}
		
		if(currIndex == next)return;
		
		//goToPage(next);
		$.history.load(next);
	}
   
	function goToPage(newPage) {
		
		if(!newPage) {
			newPage = 0;
		}
		
		//$(".page" + prevPage).hide(); // need to convert this to hide ALL pages
		$("ul.gallery li.item").hide();
		$(".page" + newPage).show(); // then just show the page we're on
		
		//--- change paging control
		//$(".paging-" + prevPage).removeClass("current"); // need to convert this to remove current class anywhere in pager set
		$("ul.gallery-paging li").removeClass("current");
		$(".paging-" + newPage).addClass("current"); // then just add it back to current page
		
	}
	
	$.history.init(goToPage);
	
}

function attachToGallery() {
	var galleryElements = $("ul.gallery li a");
	if(galleryElements.length == 0)return;
	
	var gallery = $("ul.gallery");
	var dim = {width: 183, height: 138, rows: gallery.attr("rows")*1||1, cols: gallery.attr("cols")*1||1};
	
	//--- Apply position class
	galleryElements.each(function(i){
		var galleryItem = $(this);
		var pos = getPositionInfo(i, dim);
		
		//--- Position the cell so we can grow it later.
		//galleryItem.parent().addClass("p" + (i%16+1));
		
		//--- Add frame image
		galleryItem
			.append('<img class="frame" src="shared/new_style/images/gray_frame.png"/>')
//			.append('<img class="frame" src="../images/gray_frame.png"/>')
			.parent()
			.css({
				position: "absolute",
				backgroundImage : "none",
				float: "none",
				top: pos.y + "px",
				left: pos.x + "px",
				display: (pos.page == 0)?"block":"none"
			})
			.addClass("page" + pos.page);
			
	})
	
	if(!($.fn.ifixpng == null)) {
		$("img.frame").ifixpng();
	}
	
	// add controls to allow us to page through gallery
	//
	if($("body#gallery"))
	    addPagingComponent( Math.ceil(galleryElements.length / (dim.rows * dim.cols)));
	
	function getPositionInfo(index, dim) {
		var maxItemsPerPage = dim.rows * dim.cols;
		var page = Math.floor(index / maxItemsPerPage);
		var modifiedIndex = index - (maxItemsPerPage * page);
		var row = Math.floor(modifiedIndex/dim.cols);
		var col = (modifiedIndex%dim.cols);
		var x = col * dim.width;
		var y = row * dim.height;
		
		return { x: x, y: y, page: page };
	}
	
	$("img.small").css({clip: 'rect(24px 222px 133px 43px)'})
	
	galleryElements
		.bind("mouseenter",function(event) {
			var galleryItem = $(this);
			
			if($("ul.gallery").hasClass("disabled"))
				return;
			
			//--- Toggle the items z-index
			galleryItem.parent("li.item")
				.css("z-index","99");
			
						
			galleryItem.children("img.frame")
				.css("z-index","999")
				.animate({
					width: "284px",
					height: "191px",
					//left: "-25px"
					top: '-33px',
					left: '-55px'
				}, 200, "linear", function() {
					var slider = galleryItem.children("p.slider");
					
					    slider
						.css({
						       display: "block"
						})
						.animate({
							top: "+=" + slider.height() + "px"
						}, 500);
				});
				
			galleryItem.children("img.small")
				.css("z-index","998")
				.animate({
					//width: "262px",
					//height: "139px",
					clip: "rect(0px 262px 139px 0px)"//,
					//top: "1px",
					//left: "-17px"
				}, 200);

			
			
			galleryItem
				.children("strong")
				.animate({
				    top: "120px",
				    //left: "-16px",
				    left: "-46px",
				    fontSize: "14px",
				    width: "250px"
				}, 200);
			/*
			galleryItem
				.animate({
				    //top: "-27px",
				   // left: "-28px"
				}, 200);
			*/
		
	}).bind("mouseleave",function(event) {
		var galleryItem = $(this);
		
		galleryItem.parent("li.item")
			.css("z-index","1");
		
		
		galleryItem.children("img.small")
			.stop()
			.css({
				//width: "177px",
				//height: "98px",
				clip: "rect(24px 222px 133px 43px)",
				top: "-26px",
				left: "-47px",
				zIndex: 10
			});
		
		
		galleryItem.children("img.frame")
			.stop()
			.css({
				width: "190px",
				height: "134px",
				top: "-6px",
				left: "-8px",
				zIndex: 20
			});			
		
		galleryItem.children("strong")
			.stop()
			.css({
				top: "100px",
				left: "1px",
				fontSize: "11px",
				width: "168px"
			});
		
		
		galleryItem.children("p.slider")
			.stop()
			.css({
                              display: "none",
                              top: "83px"
			})
		
		galleryItem
			.stop()
			.css({
				top: "0",
				left: "0"
			});
		
	});
}

/*
 Attach to Popup Close Button.
*/
function attachToCloseButton() {
    $("a.close-button").click(function() {
	try {
	    window.close();	    
	} catch(e){
	    //--- Sometimes it doesn't work.
	    if(window.console)console.debug(e);
	}
    })
    
}
	
/*
* jQuery css clip animation support -- Jim Palmer
* version 0.1.0
* idea spawned from jquery.color.js by John Resig
* Released under the MIT license.
*/
(function(jQuery){
       jQuery.fx.step.clip = function(fx){
	       if ( fx.state == 0 ) {
		       fx.start = /rect\(([0-9]{1,3})px ([0-9]{1,3})px ([0-9]{1,3})px ([0-9]{1,3})px\)/.exec( fx.elem.style.clip.replace(/,/g, '') );
		       fx.end = /rect\(([0-9]{1,3})px ([0-9]{1,3})px ([0-9]{1,3})px ([0-9]{1,3})px\)/.exec( fx.end.replace(/,/g, '') );
	       }
	       fx.elem.style.clip = 'rect(' + 
		       parseInt( ( fx.pos * ( parseInt(fx.end[1]) - parseInt(fx.start[1]) ) ) + parseInt(fx.start[1]) ) + 'px ' +
		       parseInt( ( fx.pos * ( parseInt(fx.end[2]) - parseInt(fx.start[2]) ) ) + parseInt(fx.start[2]) ) + 'px ' +
		       parseInt( ( fx.pos * ( parseInt(fx.end[3]) - parseInt(fx.start[3]) ) ) + parseInt(fx.start[3]) ) + 'px ' +
		       parseInt( ( fx.pos * ( parseInt(fx.end[4]) - parseInt(fx.start[4]) ) ) + parseInt(fx.start[4]) ) + 'px)';
       }
})(jQuery);	