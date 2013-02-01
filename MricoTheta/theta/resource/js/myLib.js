/*-----------------------------------------------------------------------------
*����:Muzi*lei , email:530624206@qq.com
*http://www.muzilei.com/
*version:1.0  , ʱ�䣺2011-09-14
-----------------------------------------------------------------------------*/
var myLib={
	//�����������ռ�,�õ���yui�ķ���------
	NS:function(ns) {
       if (!ns || !ns.length) {
        return null;
       }
       var levels = ns.split(".");
       var nsobj = myLib;
      for (var i=(levels[0] == "myLib") ? 1 : 0; i<levels.length; ++i) {
        nsobj[levels[i]] = nsobj[levels[i]] || {};
        nsobj = nsobj[levels[i]];
      }
      return nsobj;
      },
 	 //��ȡ����������,["Array", "Boolean", "Date", "Number", "Object", "RegExp", "String", "Window", "HTMLDocument"]
	_getType:function(object){
		return Object.prototype.toString.call(object).match(/^\[object\s(.*)\]$/)[1];
		},
	//�����ж϶�������
	_is:function(object,typeStr){
		return this._getType(object)==typeStr;
		},
	textLength:function(text){
		var intLength=0;
		for (var i=0;i<text.length;i++){
			if ((text.charCodeAt(i) < 0) || (text.charCodeAt(i) > 255)){
				intLength=intLength+2;
			}else{
				intLength=intLength+1;
				}
		 }
		 return intLength;
		 },	
	//���ؽ�����
	progressBar:function(){
		 $("<div id='myCover'></div><div id='loadimg'><span>���ڼ���,���Եȣ�</span></div>").appendTo('body');
		 var w=$(window).width(),h=$(window).height();
		 $('#myCover').css({'width':'100%','height':h,'position':'absolute','background':'#fff','z-index':9999,'left':0,'top':0}).fadeTo('slow',0.8);
		 $('#loadimg').css({'position':'absolute','background':'url(./resource/images/loading.gif) no-repeat center center','z-index':10000,'width':'110px','height':'64px','left':(w-110)/2,'top':((h-64)/2)-50}).find('span').css({'position':'absolute','left':0,'bottom':'-40px','width':110,'display':'block','height':40,'text-align':'center'});
		},
	//ֹͣ������
	stopProgress:function(){
		$('#myCover').remove();
		$('#loadimg').remove();
		},
	getImgWh:function(url, callback) {
        var width, height, intervalId, check, div, img = new Image(),
            body = document.body;
        img.src = url;

        //�ӻ����ж�ȡ
        if (img.complete) {
          return callback(img.width, img.height);
        };

        //ͨ��ռλ��ǰ��ȡͼƬͷ������
        if (body) {
          div = document.createElement('div');
          div.style.cssText = 'visibility:hidden;position:absolute;left:0;top:0;width:1px;height:1px;overflow:hidden';
          div.appendChild(img);
          body.appendChild(div);
          width = img.offsetWidth;
          height = img.offsetHeight;
          check = function() {
            if (img.offsetWidth !== width || img.offsetHeight !== height) {
              clearInterval(intervalId);
              callback(img.offsetWidth, img.clientHeight);
              img.onload = null;
              div.innerHTML = '';
              div.parentNode.removeChild(div);
            };
          };
          intervalId = setInterval(check, 150);
        };
        // ������Ϻ�ʽ��ȡ
        img.onload = function() {
          callback(img.width, img.height);
          img.onload = img.onerror = null;
          clearInterval(intervalId);
          body && img.parentNode.removeChild(img);
        };
      },
	//ȫ��
	fullscreen:function(){
		 var docElm = document.documentElement;
             if (docElm.requestFullscreen) {
                 docElm.requestFullscreen();
               }
             else if (docElm.mozRequestFullScreen) {
             docElm.mozRequestFullScreen();
               }
             else if (docElm.webkitRequestFullScreen) {
             docElm.webkitRequestFullScreen();
                    }
		},
	//�˳�ȫ��
	exitFullscreen:function(){
		if (document.exitFullscreen) {
                document.exitFullscreen();
				}
				else if (document.mozCancelFullScreen) {
					document.mozCancelFullScreen();
					}
					else if (document.webkitCancelFullScreen) {
						document.webkitCancelFullScreen();
						}
		},
	//IEȫ��
	fullscreenIE:function(){
		if($.browser.msie){
						var  wsh =  new  ActiveXObject("WScript.Shell");  
						wsh.sendKeys("{F11}");
					}
		}
	}

/*------------------------------------------	
 *jquery��չ�����ؼ����ļ���css�ļ�
-------------------------------------------*/
$.extend({
    includePath: '',
    include: function(file)
    {
        var files = typeof file == "string" ? [file] : file;
        for (var i = 0; i < files.length; i++)
        {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) document.write("<" + tag + attr + link + "></" + tag + ">");
        }
    }
});