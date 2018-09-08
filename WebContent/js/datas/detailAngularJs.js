var basePath = window.location.protocol+'//'+window.location.host+'/myLog';
var allLi = [];
var allLiDis = [];

function toUrl(arg){
	$(arg).parent().addClass("liHover");
	$(arg).addClass("aHover");
	window.location.href=$(arg).attr('url');
	for ( var i = 0; i < $("li").length; i++) {
		if($($("li")[i]).attr("class")==='liHover'&&$("li")[i]!==$(arg).parent()[0]){
			$($("li")[i]).removeClass("liHover");
			$($("li")[i]).children().removeClass("aHover");
		}
	}
};
function toUrl2(arg){
	$(arg).parent().addClass("liHover");
	$(arg).addClass("aHover");
	for ( var i = 0; i < $("li").length; i++) {
		if($($("li")[i]).attr("class")==='liHover'&&$("li")[i]!==$(arg).parent()[0]){
			$($("li")[i]).removeClass("liHover");
			$($("li")[i]).children().removeClass("aHover");
		}
	}
};

function anchorLimit(string){
	var newString = '';
		if(string!=undefined){
		if(string.length>15){
			newString = string.substring(0,15)+' ...';
		}else{
			newString = string;
		}
	}
	return newString;
}

/*获取所有锚点*/
function getAnchor(){
	var as = $('a[name]');
	for ( var i = 0; i < as.length; i++) {
		$(as[i]).attr('id',$(as[i]).attr('name'));
		$('.readTitle').append('<li><a url=\''+document.URL.split('#')[0]+'#'+$(as[i]).attr('name')+'\' onclick=\'toUrl(this)\'; title=\''+$(as[i]).attr('name')+'\'>'+anchorLimit($(as[i]).attr('name'))+'</a></li>');
		$(as[i]).removeAttr('name');
	}
}

window.onscroll = function(){
	var index = -1;
	var top = $(window).scrollTop()+3;
	if(top<60){$('#goTop').hide();}else{$("#goTop").show()}
	if(allLi.length===0){
		for ( var i = 0; i < $('a[id]').length-1; i++) {
			allLi.push($($('a[id]')[i]).attr("id"));
			allLiDis.push($($('a[id]')[i]).offset().top);
		}
	}
	for ( var j = 0; j < allLiDis.length; j++) {
		if(allLiDis[j]>top){
			if(j===0){
				j=1;
			}
			if(index!==j){
				toUrl2($('a[url]')[j-1]);
				index=j;
				return;
			}
		}else{
			if(top>allLiDis[allLiDis.length-1]){
				if(index!==9999){
					toUrl2($('a[url]')[allLiDis.length-1]);
					index=9999;
					return;
				}
			}
		}
	}
}

$(function() {
	hljs.initHighlighting();
	getAnchor();
	$.get(basePath+"/front/addClick/"+window.location.pathname.split("/")[3].split(".")[0])
});