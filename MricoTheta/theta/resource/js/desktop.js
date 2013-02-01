/*-----------------------------------------------------------------------------
*����:Muzi*lei , email:530624206@qq.com
*http://www.muzilei.com/
*version:1.0  , ʱ�䣺2011-09-14
*desktop������myLib.js���������ռ䣬��jquery �� jquery ui,contextMenu jquery���
-----------------------------------------------------------------------------*/
//����desktop�ռ�,��װ��ز���
myLib.NS("desktop");
myLib.desktop={
	winWH:function(){
		$('body').data('winWh',{'w':$(window).width(),'h':$(window).height()});
 	},
	desktopPanel:function(){
		$('body').data('panel',{
					   		'taskBar':{
					   			'_this':$('#taskBar'),
					   			'task_lb':$('#task_lb')
							},
							'navBar':$('#navBar'),//�����ǩ 
							'wallpaper':$('#wallpapers'),
							'lrBar':{
								'_this':$('#lr_bar'),	   
								'default_app':$('#default_app'),
								'start_block':$('#start_block'),
								'start_btn':$('#start_btn'),
								'start_item':$('#start_item'),
								'default_tools':$('#default_tools')
							},		
							'desktopPanel':{
								'_this':$('#desktopPanel'),
								'innerPanel':$('#desktopInnerPanel'),
								'deskIcon':$('ul.deskIcon')
							},
							'powered_by':$('a.powered_by')
					});
		},
	iconDataInit:function(data){
		 for(var a in data){
			 $("#"+a).data("iconData",data[a]);
			 }
		},	
	getMydata:function(){
		return $('body').data();
		},
	mouseXY:function(){
		var mouseXY=[];
		$(document).bind('mousemove',function(e){ 
			mouseXY[0]=e.pageX;
			mouseXY[1]=e.pageY;
           });
		return mouseXY;
		},	
	contextMenu:function(jqElem,data,menuName,textLimit){
		  var _this=this
		      ,mXY=_this.mouseXY();
		  
          jqElem
		  .smartMenu(data,{
            name: menuName,
			textLimit:textLimit,
			afterShow:function(){
				var menu=$("#smartMenu_"+menuName);
				var myData=myLib.desktop.getMydata(),
		            wh=myData.winWh;//��ȡ��ǰdocument���
 				var menuXY=menu.offset(),menuH=menu.height(),menuW=menu.width();
				if(menuXY.top>wh['h']-menuH){
					menu.css('top',mXY[1]-menuH-2);
					}
				if(menuXY.left>wh['w']-menuW){
					menu.css('left',mXY[0]-menuW-2);
					}	
				}
           });
		  $(document.body).click(function(event){
			event.preventDefault(); 			  
			$.smartMenu.hide();
						  });
		}	
	}
	
//�������ڣ�֧����ҷ���ı��С���رգ���󻯣���С��
myLib.NS("desktop.win");
myLib.desktop.win={
	winHtml:function(title,url,id){
		return "<div class='windows corner' id="
		+id
		+"><div class='win_title'><b>"
		+title
		+"</b><span class='win_btnblock'><a href='#' class='winMinimize' title='��С��'></a><a href='#' class='winMaximize' title='���'></a><a href='#' class='winHyimize' title='��ԭ'></a><a href='#' class='winClose' title='�ر�'></a></span></div><iframe frameborder='0' name='myFrame_"
		+id
		+"' id='myFrame_"
		+id
		+"' class='winframe' scrolling='auto' width='100%' src=''></iframe></div>";
		},
	 //������ϲ㣬�޸�iframe ��꾭���¼�bug	
	iframFix:function(obj){
		obj.each(function(){
		var o=$(this);								
		if(o.find('.zzDiv').size()<=0)
		o.append($("<div class='zzDiv' style='width:100%;height:"+(o.innerHeight()-26)+"px;position:absolute;z-index:900;left:0;top:26px;'></div>"));
				 })
		},	
	//��ȡ��ǰ��������z-indexֵ
	maxWinZindex:function($win){
		   return Math.max.apply(null, $.map($win, function (e, n) {
           if ($(e).css('position') == 'absolute')
            return parseInt($(e).css('z-index')) || 1;
              }));
			},
	//��ȡ��ǰ��㴰��		
	findTopWin:function($win,maxZ){
		var topWin;
		$win.each(function(index){
 						   if($(this).css("z-index")==maxZ){
							   topWin=$(this);
							   return false;
							   } 
 						   });
		return topWin;  
		},		
	//�رմ���	
	closeWin:function(obj){
		var _this=this,$win=$('div.windows').not(".hideWin"),maxZ,topWin;
 		myLib.desktop.taskBar.delWinTab(obj);
		obj.hide(200,function(){
			$(this).remove();
				         });
		//���رմ��ں�Ѱ�����z-index�Ĵ��ڲ�ʹ�����ѡ��״̬
		if($win.size()>1){
		maxZ=_this.maxWinZindex($win.not(obj));
		topWin=_this.findTopWin($win,maxZ);
		_this.switchZindex(topWin);
		}
		},
	//��С������	
	minimize:function(obj){
		var _this=this,$win=$('div.windows').not(".hideWin"),maxZ,topWin,objTab;
		//obj.hide();
		obj.css({"left":obj.position().left-10000,"visibility":"hidden"}).addClass("hideWin");
		
		//��С�����ں�Ѱ�����z-index��������
		if($win.size()>1){
		maxZ=_this.maxWinZindex($win.not(obj));
		topWin=_this.findTopWin($win,maxZ);
		_this.switchZindex(topWin);
		}else{
			objTab=myLib.desktop.taskBar.findWinTab(obj);
			objTab.removeClass('selectTab').addClass('defaultTab');
			}
		},	
	//��󻯴��ں���	
	maximizeWin:function(obj){
		var myData=myLib.desktop.getMydata(),
		    panel=$("#desktopInnerPanel").offset(), 
		    wh=myData.winWh;//��ȡ��ǰdocument���
		obj
		.css({'width':wh['w'],'height':wh['h'],'left':-panel.left,'top':-panel.top})
		.draggable( "disable" )
		.resizable( "disable" )
		.fadeTo("fast",1)
		.find(".winframe")
		.css({'width':wh['w'],'height':wh['h']-26});
		},
	//��ԭ���ں���	
	hyimizeWin:function(obj){
		var myData=obj.data(),
		    winLocation=myData.winLocation;//��ȡ�������ǰ��λ�ô�С
			
		obj.css({'width':winLocation['w'],'height':winLocation['h'],'left':winLocation['left'],'top':winLocation['top']})
		.draggable( "enable" )
		.resizable( "enable" ) 
		.find(".winframe")
		.css({'width':winLocation['w'],'height':winLocation['h']-26});
		},	
	//��������z-indexֵ		
	switchZindex:function(obj){
		var myData=myLib.desktop.getMydata()
		    ,$topWin=myData.topWin
			,$topWinTab=myData.topWinTab
		    ,curWinZindex=obj.css("z-index")
			,maxZ=myData.maxZindex
			,objTab=myLib.desktop.taskBar.findWinTab(obj);
			
		if(!$topWin.is(obj)){
			
		obj.css("z-index",maxZ);
		objTab.removeClass('defaultTab').addClass('selectTab');
		
		$topWin.css("z-index",curWinZindex);
		$topWinTab.removeClass('selectTab').addClass('defaultTab');
		this.iframFix($topWin);
		//������㴰�ڶ���
		$('body').data("topWin",obj).data("topWinTab",objTab);
		}
		},
	//�½�һ������	
    newWin:function(options){
		
		var myData=myLib.desktop.getMydata(),
		    wh=myData.winWh,//��ȡ��ǰdocument���
			$windows=$("div.windows"),
			_this=this,
			curwinNum=myLib._is(myData.winNum,"Number")?myData.winNum:0;//�жϵ�ǰ���ж��ٴ���
			_this.iframFix($windows);
			
		//Ĭ�ϲ�������
        var defaults = {
            WindowTitle:          null,
			WindowsId:            null,
            WindowPositionTop:    'center',                          /* Posible are pixels or 'center' */
            WindowPositionLeft:   'center',                          /* Posible are pixels or 'center' */
            WindowWidth:          Math.round(wh['w']*0.6),           /* Only pixels */
            WindowHeight:         Math.round(wh['h']*0.8),           /* Only pixels */
            WindowMinWidth:       250,                               /* Only pixels */
            WindowMinHeight:      250,                               /* Only pixels */
		    iframSrc:             null,                              /* ��ܵ�src·��*/ 
            WindowResizable:      true,                              /* true, false*/
            WindowMaximize:       true,                              /* true, false*/
            WindowMinimize:       true,                              /* true, false*/
            WindowClosable:       true,                              /* true, false*/
            WindowDraggable:      true,                              /* true, false*/
            WindowStatus:         'regular',                         /* 'regular', 'maximized', 'minimized' */
            WindowAnimationSpeed: 500,
            WindowAnimation:      'none'
        };
		  
		var options = $.extend(defaults, options);
 		 
		//�жϴ���λ�ã�����ʹ��Ĭ��ֵ
		var dxy=Math.floor((Math.random()*100))+30;
		var panelLeft=$("#desktopInnerPanel").position();
		 
		var wLeft=myLib._is(options['WindowPositionLeft'],"Number")?options['WindowPositionLeft']+dxy-panelLeft.left:(wh['w']-options['WindowWidth'])/2+dxy-panelLeft.left;
		var wTop=myLib._is(options['WindowPositionTop'],"Number")?options['WindowPositionTop']+dxy/2:(wh['h']-options['WindowHeight'])/2+dxy/2;
 		 
		//�����ڸ����µ�z-indexֵ
		var zindex=curwinNum+500;
		var id="myWin_"+options['WindowsId'];//���ݴ�����id����Ϊ�´���id
 		$('body').data("winNum",curwinNum+1);//���´�������
		
		//�ж������id�Ĵ��ڴ��ڣ��򲻴�������
		if($("#"+id).size()<=0){
			//�������������tab
			myLib.desktop.taskBar.addWinTab(options['WindowTitle'],options['WindowsId']);
			//��ʼ���´��ڲ���ʾ
			$(_this.winHtml(options['WindowTitle'],options['iframSrc'],id)).appendTo('#desktopInnerPanel');
		
		var $newWin=$("#"+id)
		   ,$icon=$("#"+options['WindowsId'])
		   ,$iconOffset=$icon.offset()
		   ,$fram=$newWin.find(".winframe")
		   ,$winTitle=$newWin.find(".win_title")
		   ,winMaximize_btn=$newWin.find('a.winMaximize')//��󻯰�ť
		   ,winMinimize_btn=$newWin.find('a.winMinimize')//��С����ť
		   ,winClose_btn=$newWin.find('a.winClose')//�رհ�ť
		   ,winHyimize_btn=$newWin.find('a.winHyimize');//��ԭ��ť
		   
		   winHyimize_btn.hide();
		   if(!options['WindowMaximize']) winMaximize_btn.hide();
		   if(!options['WindowMinimize']) winMinimize_btn.hide();
		   if(!options['WindowClosable']) winClose_btn.hide();
		
		//�洢��������z-indexֵ,����㴰�ڶ���
		$('body').data({"maxZindex":zindex,"topWin":$newWin});
		
		//�жϴ����Ƿ����ö���Ч��
		if(options.WindowAnimation=='none'){
			
		 $newWin
		 .css({"width":options['WindowWidth'],"height":options['WindowHeight'],"left":wLeft,"top":wTop,"z-index":zindex})
		 .addClass("loading")
		 .show(10,function(){
				$(this).find(".winframe").attr("src",options['iframSrc']).load(function(){
																						$(this).show();
																						});
						   });
		 
			}else{
	 		
		 $newWin
		 .css({"left":$iconOffset.left,"top":$iconOffset.top,"z-index":zindex})
		 .addClass("loading")
		 .show()
		 .animate({ 
            width: options['WindowWidth'], 
            height:options['WindowHeight'],
            top: wTop, 
            left: wLeft}, 100,function(){
				$(this).find(".winframe").attr("src",options['iframSrc']).load(function(){
																						$(this).show();
																						});
				});
 				}
				
        $newWin
		//�洢���ڵ�ǰλ�ô�С
		.data('winLocation',{
			  'w':options['WindowWidth'],
			  'h':options['WindowHeight'],
			  'left':wLeft,
			  'top':wTop
			  })
		//��������л����ڣ�ʹ�˴�����ʾ��������
		.bind({
			"mousedown":function(event){  
			_this.switchZindex($(this));
					},
			"mouseup":function(){
			$(this).find('.zzDiv').remove();
						}		
				})
		.find(".winframe")
		.css({"width":options['WindowWidth'],"height":options['WindowHeight']-26});
					   
 		 //���ô����϶�,�������϶��ķ�Χ�������ң�����id�ͣ���������Ӵ��ڴ�С
		 if(options['WindowDraggable']){
		   _this.drag([0,0,wh['w']-options['WindowWidth']-10,wh['h']-options['WindowHeight']-35],$newWin,wh);
		  }
		//���ô���resize,���������С��Ⱥ͸߶ȣ��´��ڶ���id����������Ӵ��ڴ�С
		if(options['WindowResizable']){
		   _this.resize(options['WindowMinWidth'],options['WindowMinHeight'],wh['w']-wLeft,wh['h']-wTop-35,$newWin,wh);
		 }
        
		//˫�����ڱ�����
		$winTitle.dblclick(function(){
								var hasMaximizeBtn=$(this).find(winMaximize_btn);
								
									if(!hasMaximizeBtn.is(":hidden")){
										winMaximize_btn.trigger("click");
									}else{
 										winHyimize_btn.trigger("click");
										}
										
									});
  	    		
		//������󻯣���С�������ر�
		winClose_btn.click(function(event){
					 event.stopPropagation();
 					 _this.closeWin($newWin);
									  });
		//���
		winMaximize_btn.click(function(event){
					 event.stopPropagation();			   
					 if(options['WindowStatus']=="regular"){								 
					 _this.maximizeWin($newWin);
					 $(this).hide();
					 winHyimize_btn.show();
					 options['WindowStatus']="maximized";
					 $("#desktopPanel").css("z-index",95);
					 }
 						});
		
		//�����������ڴ�С�ı䣬����´��ڴ�С
		$(window).wresize(function(){
							if(options['WindowStatus']=="maximized"){	   
                              _this.maximizeWin($newWin);
							}
								   });
		//��ԭ����
		winHyimize_btn.click(function(event){
					 event.stopPropagation();				  
					 if(options['WindowStatus']=="maximized"){								 
					 _this.hyimizeWin($newWin);
					 $(this).hide();
					 winMaximize_btn.show();
					 options['WindowStatus']="regular";
					 $("#desktopPanel").css("z-index",70);
					 }		  
									  });
		//��С������
		winMinimize_btn.click(function(){
						_this.minimize($newWin);		   
									   });
		             }else{
  						 
						 //����Ѵ��ڴ˴��ڣ��ж��Ƿ�����
					     var wins=$("#"+id),objTab=myLib.desktop.taskBar.findWinTab(wins);
						 if(wins.is(":hidden")){
							  wins.show();
							  objTab.removeClass('defaultTab').addClass('selectTab');//��ֻ��һ������ʱ
						      myLib.desktop.win.switchZindex(wins);
							 }else{
								 
								 }
 						 }
		},
	upWinResize_block:function(win){
		    
			//���´��ڿɸı��С��Χ,whΪ��������ڴ�С
            var offset=win.offset();
		    win.resizable( "option" ,{'maxWidth':$(window).width()-offset.left-10,'maxHeight':$(window).height()-offset.top-35})
		},
 	drag:function(arr,$newWin,wh){
		var _this=this;
		$newWin
		.draggable({ 
	    handle:'div.win_title',
	    iframeFix:false,
		scroll: false
		})
		.bind("dragstart",function(event,ui){
 					_this.iframFix($(this));
					$("#desktopPanel").css("z-index",95);
						  })
		.bind( "dragstop", function(event, ui) {
			$("#desktopPanel").css("z-index",70);	
			
			var obj_this=$(this);	
			
			var offset=obj_this.offset();
			//�������ҷ��Χ
			_this.upWinResize_block(obj_this);
			
		    obj_this
			//���´��ڴ洢��λ������
			.data('winLocation',{
			'w':obj_this.width(),
			'h':obj_this.height(),
			'left':offset.left,
			'top':offset.top
			})
			.find('.zzDiv')
			.remove();
			 
			if(event.pageY>wh.h-50){
							  $(this).css("top",event.pageY-90);
 		    }else if(event.pageY<-35){
							  $(this).css("top",-35);  
								  }
         }); 
		
		   $("div.win_title").css("cursor","move");
 		 
		},
	resize:function(minW,minH,maxW,maxH,$newWin,wh){
		var _this=this;
		$newWin
		.resizable({
		minHeight:minH,
		minWidth:minW,
		containment:'document',
		maxWidth:maxW,
		maxHeight:maxH
		})
		.css("position","absolute")
		.bind( "resize", function(event, ui) {
			var h=$(this).innerHeight(),w=$(this).innerWidth(); 	
 			 _this.iframFix($(this));
			 
			//��ҷ�ı䴰�ڴ�С������iframe��Ⱥ͸߶ȣ�����ʾiframe					 
			$(this).children(".winframe").css({"width":w,"height":h-26});
				
        })
	   .bind( "resizestop", function(event, ui) {					 
			var obj_this=$(this);
			var offset=obj_this.offset();
			var h=obj_this.innerHeight(),w=obj_this.innerWidth();
  			
		    obj_this
			//���´��ڴ洢��λ������
			.data('winLocation',{
			'w':w,
			'h':h,
			'left':offset.left,
			'top':offset.top
			  })
			 //ɾ������iframe�Ĳ�
			.find(".zzDiv")
			.remove();
       });
		}
	}
	
//��߹�����
myLib.NS("desktop.lrBar");
myLib.desktop.lrBar={
	upLrBar:function(){
		var myData=myLib.desktop.getMydata()
 			,$lrBar=myData.panel.lrBar['_this']
			,wh=myData.winWh;
		    $lrBar.css({'top':Math.floor((wh['h']-$lrBar.height())/2)-60});
 			
		},
	init:function(iconData){
		//��ȡԪ�ض�������
		var myData=myLib.desktop.getMydata()
	        ,$default_tools=myData.panel.lrBar['default_tools']
		    ,$def_tools_Btn=$default_tools.find('span')
			,$start_btn=myData.panel.lrBar['start_btn']
			,$start_block=myData.panel.lrBar.start_block
			,$start_item=myData.panel.lrBar['start_item']
			,$default_app=myData.panel.lrBar['default_app']
			,$lrBar=myData.panel.lrBar['_this']
			,wh=myData.winWh
			,_this=this;
  	
		//��ʼ������λ��
		_this.upLrBar();
		
		//����data����
		myLib.desktop.iconDataInit(iconData);
		
		//������ڴ�С�ı䣬����²����λ��
		$(window).wresize(function(){
				 myLib.desktop.winWH();//���´��ڴ�С����			   
 				_this.upLrBar();
								   });
		
		//�������ұ�Ĭ��������򽻻�Ч��	
		$def_tools_Btn.hover(function(){
							$(this).css("background-color","#999");	
								},function(){
									$(this).css("background-color","transparent");
									});	
		//Ĭ��Ӧ�ó�����
		$default_app
		.droppable({
				scope:'a',
                drop: function(event,ui) {	
				var title=ui.draggable.find(".text").text();	
				ui.draggable
 				.removeClass("desktop_icon")
				.attr({"style":"",
					   "title":title
					   })
				.find("span")
				.removeClass("icon") 
				.end()
  				.appendTo($default_app);
				myLib.desktop.deskIcon.init(); 
				_this.init();	
 					}
           })
		.find('li')
		.hover(function(){
						    $(this).addClass('btnOver');
								 },function(){
									$(this).removeClass('btnOver');		  
										})
		.click(function(){
		                
 						var data=$(this).data("iconData"),id=this.id;
							myLib.desktop.win.newWin({
													 WindowTitle:data.title,
													 iframSrc:data.url,
													 WindowsId:id,
													 WindowAnimation:'none', 
													 WindowWidth:data.winWidth,
													 WindowHeight:data.winHeight
 													 });	
							
						$(this).data("currPanel",$("ul.currDesktop").index("ul.deskIcon"));	
						
									})
		.draggable({
				helper: "clone",
				scroll:false,
				opacity: 0.7,
				scope:'a',
				appendTo:'parent',
				start:function(){
					$lrBar.css("z-index",90);
					}
					})
		 .droppable({
				scope:'a',
                drop: function(event,ui) {
				 
				var title=ui.draggable.find(".text").text();	
				ui.draggable
 				.removeClass("desktop_icon")
				.attr({"style":"",
					   "title":title
					   })
				.find("span")
				.removeClass("icon") 
				.end()
  				.insertBefore($(this));
 				_this.init();	
				myLib.desktop.deskIcon.init(); 
  					}
           });
		
		//��ʼ��ť���˵�����Ч��
		$start_btn.click(function(event){
								  event.preventDefault();
								  event.stopPropagation()
								  if($start_item.is(":hidden"))
								  $start_item.show();
								  else
								  $start_item.hide();
								  });
		
		$("body").click(function(event){
								 event.preventDefault();  
								 $start_item.hide();
									  });
		//ȫ��
		 $("#showZm_btn")
		 .toggle(function(){
							myLib.fullscreenIE();	
							myLib.fullscreen();
						},
				function(){
							myLib.fullscreenIE();
							myLib.exitFullscreen();
					});
		}
 	}
/*----------------------------------------------------------------------------------	
//�����������ռ䣬���������js����
----------------------------------------------------------------------------------*/
myLib.NS("desktop.taskBar");
myLib.desktop.taskBar={
	timer:function(obj){
		 var curDaytime=new Date().toLocaleString().split(" ");
		 obj.innerHTML=curDaytime[1];
		 obj.title=curDaytime[0];
		 setInterval(function(){obj.innerHTML=new Date().toLocaleString().split(" ")[1];},1000);
		},
	upTaskWidth:function(){
		var myData=myLib.desktop.getMydata()
		    ,$task_bar=myData.panel.taskBar['_this'];
		var maxHdTabNum=Math.floor($(window).width()/100);
		    //�������������
		    $task_bar.width(maxHdTabNum*100);	
			//�洢�������tabĬ������
			$('body').data("maxHdTabNum",maxHdTabNum-2);
		},	
	init:function(){
		//��ȡԪ�ض�������
		var myData=myLib.desktop.getMydata();
 		var $task_lb=myData.panel.taskBar['task_lb']
		    ,$task_bar=myData.panel.taskBar['_this']
			,wh=myData.winWh;
 
		 var _this=this;
		 _this.upTaskWidth();
		 //���ı���������ڴ�Сʱ�����¼������������
		 $(window).wresize(function(){
						_this.upTaskWidth();   
								   });
  		 
 		},
	contextMenu:function(tab,id){
		var _this=this;
		 //��ʼ��������Tab�Ҽ��˵�
		 var data=[
					[{
					text:"���",
					func:function(){
 						$("#myWin_"+tab.data('win')).find('a.winMaximize').trigger('click');
						}
					  },{
					text:"��С��",
					func:function(){
						myLib.desktop.win.minimize($("#myWin_"+tab.data('win')));
						}
						  }]
					,[{
					  text:"�ر�",
					  func:function(){
						  $("#smartMenu_taskTab_menu"+id).remove();
 						  myLib.desktop.win.closeWin($("#myWin_"+tab.data('win')));
						  } 
					  }]
					];
		 myLib.desktop.contextMenu(tab,data,"taskTab_menu"+id,10);
		},
	addWinTab:function(text,id){
		var myData=myLib.desktop.getMydata();
 		var $task_lb=myData.panel.taskBar['task_lb']
		    ,$task_bar=myData.panel.taskBar['_this']
			,$navBar=myData.panel.navBar
 			,$navTab=$navBar.find("a")
		    ,tid="myWinTab_"+id
			,allTab=$task_lb.find('a')
			,curTabNum=allTab.size()
		    ,docHtml="<a href='#' id='"+tid+"'>"+text+"</a>";
			
			//����µ�tab
		    $task_lb.append($(docHtml));
			var $newTab=$("#"+tid);
			//�Ҽ��˵�
			this.contextMenu($newTab,id);
			
			$task_lb
			.find('a.selectTab')
			.removeClass('selectTab')
			.addClass('defaultTab');
			 
			$newTab
			.data('win',id)
			.addClass('selectTab')
			.click(function(){
					var win=$("#myWin_"+$(this).data('win')),
					    tabId=this.id,
						iconId=tabId.split("_")[1],
						desk=$("#"+iconId).parent(),
 						i=desk.index("ul.deskIcon");	//�жϴ������Ǹ���������
					
					if(i<0){
						i=$("#"+iconId).data("currPanel");
						}
 					//����ǵ�ǰ����
					if(desk.is(".currDesktop")){
						if(win.is(".hideWin")){
						//win.show();
						win.css({"left":win.position().left+10000,"visibility":"visible"}).removeClass("hideWin");
						
 						$(this).removeClass('defaultTab').addClass('selectTab');//��ֻ��һ������ʱ
						myLib.desktop.win.switchZindex(win);
  						}else{
							if($(this).hasClass('selectTab')){
							myLib.desktop.win.minimize(win);
  							}else{
								myLib.desktop.win.switchZindex(win);
								} 
							  }
							  
				    //������ڵ�ǰ����			  
				    }else{
					 if(win.is(".hideWin")){
						//win.show();
						win.css({"left":win.position().left+10000,"visibility":"visible"}).removeClass("hideWin");
						
 						$(this).removeClass('defaultTab').addClass('selectTab');//��ֻ��һ������ʱ
						myLib.desktop.win.switchZindex(win);
  						}	
					 $navTab.eq(i).trigger("click");
							}
 							  
 							});
			
			$('body').data("topWinTab",$newTab);
			
			//�������������������ʱ
			if(curTabNum>myData.maxHdTabNum-1){
				var LeftBtn=$('#leftBtn')
				    ,rightBtn=$('#rightBtn')
					,bH;
				
                LeftBtn
				.show()
				.find("a")
				.click(function(){
							        var pos=$task_lb.position();
									if(pos.top<0){
										$task_lb.animate({
                                                  "top":pos.top+40
                                                      }, 50);
										}
									 });
				
				rightBtn
				.show()
				.find("a")
				.click(function(){
									var pos=$task_lb.position(),h=$task_lb.height(),row=h/40;
									if(pos.top>(row-1)*(-40)){
									$task_lb.animate({
                                                  "top": pos.top-40
                                                      }, 50);   
									}
									   });
				
				$task_lb.parent().css("margin","0 100");
				}
	 
		},
	delWinTab:function(wObj){
		var myData=myLib.desktop.getMydata()
 		    ,$task_lb=myData.panel.taskBar['task_lb']
			,$task_bar=myData.panel.taskBar['_this']
			,LeftBtn=$('#leftBtn')
			,rightBtn=$('#rightBtn')
		    ,pos=$task_lb.position();
			
		this.findWinTab(wObj).remove();
		
		var newH=$task_lb.height();
		if(Math.abs(pos.top)==newH){
			LeftBtn.find('a').trigger("click");
			}
		if(newH==40){
			LeftBtn.hide();
			rightBtn.hide();
			$task_lb.parent().css("margin",0);
			}	
		},
	findWinTab:function(wObj){
		var myData=myLib.desktop.getMydata(),
		    $task_lb=myData.panel.taskBar['task_lb'],
		    objTab;
		    $task_lb.find('a').each(function(index){
								var id="#myWin_"+$(this).data("win");		 
								if($(id).is(wObj)){
									objTab=$(this);
									}		 
 										 });
		    return objTab;
		}	
	}
//navbar
myLib.NS("desktop.navBar");
myLib.desktop.navBar={
	init:function(){
		 var myData=myLib.desktop.getMydata()
			 ,$navBar=myData.panel.navBar
			 ,$innerPanel=myData.panel.desktopPanel.innerPanel
			 ,$navTab=$navBar.find("a")
			 ,$deskIcon=myData.panel.desktopPanel['deskIcon']
			 ,desktopWidth=$deskIcon.width()
			 ,lBarWidth=myData.panel.lrBar["_this"].outerWidth();
			 
			 $navBar
			 .draggable({
 					scroll:false
						});
			 
			 $navTab
			 .droppable({
				scope:'a',
				over:function(event,ui){
					$(this).trigger("click");
					var i=$navTab.index($(this));
					//ui.draggable
					//.css({left:event.pageX+$deskIcon.width()*i});
 					},
                drop: function(event,ui) {
				var i=$navTab.index($(this));	
 				ui.draggable
				.addClass("desktop_icon")
				.insertBefore($deskIcon.eq(i).find(".add_icon"))
				.find("span")
				.addClass("icon"); 
                myLib.desktop.deskIcon.init();
				myLib.desktop.lrBar.init();
					}
			 })
			 .click(function(event){
					event.preventDefault();
					event.stopPropagation();
					var i=$navTab.index($(this));
 					myLib.desktop.deskIcon.desktopMove($innerPanel,$deskIcon,$navTab,500,desktopWidth+lBarWidth,i);
							 });
		}
	};

//���汳��
myLib.NS("desktop.wallpaper");
myLib.desktop.wallpaper={
	init:function(imgUrl){
		
		    //����ǰ���ڿ�Ⱥ͸߶����ݴ洢��bodyԪ����
		    myLib.desktop.winWH();
			
		    var myData=myLib.desktop.getMydata()
		         ,winWh=myData.winWh
			     ,wallpaper=myData.panel.wallpaper
				 ,_this=this;
 				 
				 if(imgUrl!==null){
				 wallpaper.html("<img src='"+imgUrl+"'></img>");
			     var img=wallpaper.find("img");	
 				
					 myLib.getImgWh(imgUrl,function(imgW,imgH){
 								 if(imgW<=winWh.w){
								      img.css('width',winWh.w);
									}else{
										img.css({"margin-left":-(imgW-winWh.w)/2});
										}
								if(imgH<=winWh.h){
								img.css('height',winWh.h);
									}else{
										img.css({"margin-top":-(imgH-winWh.h)/2});
										}	
												});	
				 }
				 
		//������ڴ�С�ı䣬���±������ִ�С
		 window.onresize=function(){
     							_this.init(imgUrl);
								};
		}
	};

//����ͼ������
myLib.NS("desktop.deskIcon");
myLib.desktop.deskIcon={
 	//����ͼ������
	arrangeIcons:function(desktop){
          var myData=myLib.desktop.getMydata()
		     ,winWh=myData.winWh
			 ,$navBar=myData.panel.navBar
			 ,navBarHeight=$navBar.outerHeight()
		     //����һ���ж���ͼ��
		     ,iconNum=desktop.find("li").size();
			
		 //�洢��ǰ�ܹ��ж�������ͼ��
		 desktop.data('deskIconNum',iconNum);
		 
		 var gH=120;//һ��ͼ���ܸ߶ȣ���������margin
		 var gW=120;//ͼ���ܿ��,��������margin
		 var rows=Math.floor((winWh['h']-navBarHeight-75)/gH);
		 var cols=Math.ceil(iconNum/rows);
		 var curcol=0,currow=0;

		 desktop.
		 find("li")
		 .css({
				   "position":"absolute",
				   "margin":0,
				   "left":function(index,value){
					       var v=curcol*gW+30;
					           if((index+1)%rows==0){
							       curcol=curcol+1;
					              }
						   return v;	 
 						},
					"top":function(index,value){
 							var v=(index-rows*currow)*gH+20;
								if((index+1)%rows==0){
									 currow=currow+1;
									}
						    return v;
							}});
		},
	upDesktop:function($deskIcon,$deskIconBlock,$innerPanel,$deskIconNum,navBarHeight,lBarWidth){
		 var myData=myLib.desktop.getMydata()
		    ,winWh=myData.winWh
		    ,w=winWh['w']-lBarWidth
		    ,h=(winWh['h']-75-navBarHeight)
		    ,_this=this;
		
		//��������ͼ������Ԫ�������С
		 $innerPanel.css({"width":((w+lBarWidth)*$deskIconNum)+"px","height":h+"px"});
		 $deskIcon.css({"width":w+"px","height":h+"px",'margin-right':lBarWidth});
		 $deskIconBlock.css({"width":w+"px","height":h+"px","margin-top":navBarHeight,'margin-left':lBarWidth+'px','margin-bottom':75+"px"});
		
		 $deskIcon.each(function(){
				_this.arrangeIcons($(this));
			
				$(this)
				.droppable({
				scope:'a',
                drop: function(event,ui) {
 				ui.draggable
				.addClass("desktop_icon")
				.insertBefore($(this).find(".add_icon"))
				.find("span")
				.addClass("icon"); 
                _this.init();
				myLib.desktop.lrBar.init();
					}
					});
								 });
		},
	desktopMove:function($innerPanel,$deskIcon,$navTab,dates,moveDx,nextIndex){
		 $innerPanel
		 .stop()
		 .animate({
				 left:-(nextIndex)*moveDx
				},dates,function(){
							$deskIcon
							.removeClass("currDesktop")
							.eq(nextIndex)
							.addClass("currDesktop");
												 
							$navTab
							.removeClass("currTab")
							.eq(nextIndex)
							.addClass("currTab");
 							});
		},	
	init:function(iconData){
 		 
 		 var myData=myLib.desktop.getMydata()
		    ,winWh=myData.winWh
			,$deskIconBlock=myData.panel.desktopPanel['_this']
			,$innerPanel=myData.panel.desktopPanel.innerPanel
			,$deskIcon=myData.panel.desktopPanel['deskIcon']
			,$deskIconNum=$deskIcon.size()
			,$navBar=myData.panel.navBar
			,navBarHeight=$navBar.outerHeight()
			,$navTab=$navBar.find("a")
			,lBarWidth=myData.panel.lrBar["_this"].outerWidth()
			,_this=this;
 		
 		 _this.upDesktop($deskIcon,$deskIconBlock,$innerPanel,$deskIconNum,navBarHeight,lBarWidth);
		
		 //������ڴ�С�ı䣬����������ͼ��
		 $(window).wresize(function(){
							myLib.desktop.winWH();//���´��ڴ�С����
							_this.upDesktop($deskIcon,$deskIconBlock,$innerPanel,$deskIconNum,navBarHeight,lBarWidth);
   								   });
 		 //����data����
		 myLib.desktop.iconDataInit(iconData);
		 
		 //�����ʹ������϶��л�
		 var timeStart,timeEnd,dxStart,dxEnd;
		 
		 $innerPanel
		 .draggable({
					axis:'x',
					start:function(event,ui){
						 
						$(this).css("cursor","move");
						timeStart=new Date().getTime();
						dxStart=event.pageX;
						},
					stop:function(event,ui){
						$(this).css("cursor","inherit");
						timeEnd=new Date().getTime();
						dxEnd=event.pageX;
						var timeCha=timeEnd-timeStart
						    ,dxCha=dxEnd-dxStart
							,currDesktop=$(this).find("ul.currDesktop")
							,deskIndex=$deskIcon.index(currDesktop)
							,moveDx=$deskIcon.width()+lBarWidth
							,dates=1000+timeCha;
						
						//����	
						if(dxCha < -150 && deskIndex<3){
 						_this.desktopMove($(this),$deskIcon,$navTab,dates,moveDx,deskIndex+1);
						//����	 
 						}else if(dxCha > 150 && deskIndex>0){
 						_this.desktopMove($(this),$deskIcon,$navTab,dates,moveDx,deskIndex-1);
 						}else{
						$(this)
						.animate({
								left:-(deskIndex)*moveDx
								},500);
						}
 						}	
					});
		 
 		 
		 //ͼ����꾭��Ч��
		 $deskIcon
		 .find("li")
 		 .hover(function(){
						 $(this).addClass("desktop_icon_over");
						 },
						 function(){
							  $(this).removeClass("desktop_icon_over");
							 })
		 .not("li.add_icon")
		 //˫��ͼ��򿪴���
		 .click(function(){
						   var data=$(this).data("iconData"),id=this.id;
							myLib.desktop.win.newWin({
													 WindowTitle:data.title,
													 iframSrc:data.url,
													 WindowsId:id,
													 WindowAnimation:'none', 
													 WindowWidth:data.winWidth,
													 WindowHeight:data.winHeight
 													 });	
 							})
		 .draggable({
					helper: "clone",
					scroll:false,
					opacity: 0.7,
					scope:'a',
					appendTo: 'body' ,
					zIndex:91,
					start: function(event, ui) {
 						ui.helper.removeClass("desktop_icon_over");
						} 
					})
		 .droppable({
				scope:'a',
                drop: function(event,ui) {
 				ui.draggable
				.unbind("dblclick")
				.addClass("desktop_icon")
				.insertBefore($(this))
				.find("span")
				.addClass("icon"); 
                _this.init();
				myLib.desktop.lrBar.init();
					}
           });
		 
		 //��ʼ�������Ҽ��˵�
		 var data=[		           
		            [{
		            	text:"��ʾ����",
		            	func:function(){
					
		            	}
		            }],
		            [{
						text:"ϵͳ����",
						func:function(){
							
						}
					}],
					[{
						text:"��������",
						func:function(){
							
						}
					}],
					[{
						text:"�˳�ϵͳ",
						func:function(){
							
						}
					}],
					[{
						text:"����fleiCms",
						func:function(){
							
						}
					}]
		         ];
		 myLib.desktop.contextMenu($(document.body),data,"body",10);
		}
	}
