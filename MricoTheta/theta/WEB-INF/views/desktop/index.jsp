<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	
		<title>MricoTheta</title>
		
		<script type="text/javascript" src="<%=basePath %>resource/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>resource/js/myLib.js"></script>
		
		<script type="text/javascript">

		$(function(){
			myLib.progressBar();
		});
		
		$.include(['<%=basePath %>resource/css/desktop.css', 
		           '<%=basePath %>resource/css/jquery-ui-1.8.18.custom.css',
		           '<%=basePath %>resource/css/smartMenu.css' ,
		           '<%=basePath %>resource/js/jquery-ui-1.8.18.custom.min.js',
		           '<%=basePath %>resource/js/jquery.winResize.js',
		           '<%=basePath %>resource/js/jquery-smartMenu-min.js',
		           '<%=basePath %>resource/js/desktop.js']);

		$(window).load(function(){
			myLib.stopProgress();
			var lrBarIconData={
				'app0':{
					   'title':'jsfoot网页特效',
					   'url':'http://www.jsfoot.com/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app1':{
					   'title':'jQuery特效',
					   'url':'http://www.jsfoot.com/jquery/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app2':{
					   'title':'javascript特效',
					   'url':'http://www.jsfoot.com/js/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app3':{
					   'title':'资源分享',
					   'url':'ziyuan.html',
					   'winWidth':1100,
					   'winHeight':650
					   }
					   };
		 	var deskIconData={
			        'kuwoMusic':{
					   'title':'酷我音乐盒',
					   'url':'http://mbox.kuwo.cn/',
					   'winWidth':950,
					   'winHeight':500
						},
					'hudong':{
					   'title':'互动百科',
					   'url':'http://lab.hudong.com/webqq/index.html',
					   'winWidth':950,
					   'winHeight':500
						},
					'dubianFim':{
					   'title':'豆瓣FIM',
					   'url':'http://douban.fm/partner/qq_plus',
					   'winWidth':550,
					   'winHeight':480
						},
					'Pixlr':{
					   'title':'Pixlr',
					   'url':'http://pixlr.com/editor/?loc=zh-cn',
					   'winWidth':942,
					   'winHeight':547
						},
					'qidian':{
					   'title':'起点中文',
					   'url':'http://webqq.qidian.com',
					   'winWidth':942,
					   'winHeight':547
						},
					'leshiwang':{
					   'title':'乐视网',
					   'url':'http://www.letv.com/cooperation/qq.html',
					   'winWidth':842,
					   'winHeight':547
						},
					'qianqianMusic':{
					   'title':'千千音乐',
					   'url':'http://www.qianqian.com/paihang.html',
					   'winWidth':930,
					   'winHeight':500
						},
					'zfMeishi':{
					   'title':'主妇美食',
					   'url':'http://www.zhms.cn/',
					   'winWidth':930,
					   'winHeight':500
						},
					'mglvyou':{
						'title':'芒果旅游',
					   'url':'http://www.mangocity.com/webqq/bookFlight.html',
					   'winWidth':930,
					   'winHeight':500
						},	
					'taobao':{
						'title':'淘宝网',
					   'url':'http://marketing.taobao.com/home/webqq/index.htm',
					   'winWidth':930,
					   'winHeight':500
						},	
					'qingshu':{
						'title':'情书',
					   'url':'http://www.qingshu8.net/',
					   'winWidth':930,
					   'winHeight':500
						},
					'fenghuang':{
						'title':'凤凰网',
					   'url':'http://www.ifeng.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'zhongguancun':{
						'title':'中关村在线',
					   'url':'http://www.zol.com.cn/',
					   'winWidth':930,
					   'winHeight':500
						},
					'win35':{
						'title':'搜狐汽车',
					   'url':'http://auto.sohu.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'win36':{
						'title':'布丁电影票',
					   'url':'http://piao.buding.cn/',
					   'winWidth':900,
					   'winHeight':500
						},	
					'win37':{
						'title':'中国数学资源网',
					   'url':'http://www.mathrs.net/',
					   'winWidth':930,
					   'winHeight':500
						},
					'win38':{
						'title':'火影忍者漫画动画',
					   'url':'http://www.manmankan.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'win39':{
						'title':'潇湘书院',
					   'url':'http://www.xxsy.net/',
					   'winWidth':930,
					   'winHeight':500
						}  
			};			   
 		   
			//存储桌面布局元素的jquery对象
			myLib.desktop.desktopPanel();
 		   
		   //初始化桌面背景
			myLib.desktop.wallpaper.init("resource/images/blue_glow.jpg");
		   
		   //初始化任务栏
			myLib.desktop.taskBar.init();
		   
		   //初始化桌面图标
			myLib.desktop.deskIcon.init(deskIconData);
		   
		   //初始化桌面导航栏
			myLib.desktop.navBar.init();
		   
		   //初始化侧边栏
			myLib.desktop.lrBar.init(lrBarIconData);
		   
		   //欢迎窗口
		/*	myLib.desktop.win.newWin({
				WindowTitle:'欢迎窗口',
				iframSrc:"welcome.html",
				WindowsId:"welcome",
				WindowAnimation:'none', 
				WindowWidth:740,
				WindowHeight:520
			});*/
		});		

		</script>
	</head>
	
	<body>

		<a href="http://www.baidu.com/" class="powered_by">昆山思拓</a>

		<div id="wallpapers"></div>

		<div id="navBar">
			<a href="#" class="currTab" title="桌面1"></a>
			<a href="#"  title="桌面2"></a>
			<a href="#"  title="桌面3"></a>
			<a href="#"  title="桌面4"></a>
		</div>

		<div id="desktopPanel">
			<div id="desktopInnerPanel">
				<ul class="deskIcon currDesktop">
					<li class="desktop_icon" id="leshiwang"><span class="icon"><img src="resource/icon/icon4.png"/></span><div class="text">乐视网<s></s></div></li>
					<li class="desktop_icon" id="Pixlr"><span class="icon"><img src="resource/icon/icon6.png"/></span><div class="text">Pixlr<s></s></div></li>
					<li class="desktop_icon" id="dubianFim"><span class="icon"><img src="resource/icon/icon7.png"/></span><div class="text">豆瓣FIM<s></s></div></li>
					<li class="desktop_icon" id="kuwoMusic"><span class="icon"><img src="resource/icon/icon8.png"/></span><div class="text">酷我音乐盒<s></s></div></li>
					<li class="desktop_icon" id="qidian"><span class="icon"><img src="resource/icon/icon9.png"/></span><div class="text">起点中文<s></s></div></li>
					<li class="desktop_icon" id="hudong"><span class="icon"><img src="resource/icon/icon10.png"/></span><div class="text">互动百科<s></s></div></li>
					<li class="desktop_icon" id="qianqianMusic"><span class="icon"><img src="resource/icon/icon5.png"/></span><div class="text">千千音乐<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">添加<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="zfMeishi"><span class="icon"><img src="resource/icon/icon13.png"/></span><div class="text">主妇美食<s></s></div></li>
					<li class="desktop_icon" id="mglvyou"><span class="icon"><img src="resource/icon/icon12.png"/></span><div class="text">芒果旅游<s></s></div></li>
					<li class="desktop_icon" id="taobao"><span class="icon"><img src="resource/icon/icon14.png"/></span><div class="text">淘宝网<s></s></div></li>
					<li class="desktop_icon" id="qingshu"><span class="icon"><img src="resource/icon/icon15.png"/></span><div class="text">情书<s></s></div></li>
					<li class="desktop_icon" id="fenghuang"><span class="icon"><img src="resource/icon/icon16.png"/></span><div class="text">凤凰网<s></s></div></li>
					<li class="desktop_icon" id="zhongguancun"><span class="icon"><img src="resource/icon/icon17.png"/></span><div class="text">中关村在线<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">添加<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="win35"><span class="icon"><img src="resource/icon/icon18.png"/></span><div class="text">搜狐汽车<s></s></div></li>
					<li class="desktop_icon" id="win36"><span class="icon"><img src="resource/icon/icon19.png"/></span><div class="text">布丁电影票<s></s></div></li>
					<li class="desktop_icon" id="win37"><span class="icon"><img src="resource/icon/icon8.png"/></span><div class="text">中国数学资源网<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">添加<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="win38"><span class="icon"><img src="resource/icon/icon20.png"/></span><div class="text">火影忍者漫画动画<s></s></div></li>
					<li class="desktop_icon" id="win39"><span class="icon"><img src="resource/icon/icon21.png"/></span><div class="text">潇湘书院<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">添加<s></s></div></li>
				</ul>
			</div>
		</div><!--desktopPanel end-->

		<div id="taskBarWrap">
			<div id="taskBar">
				<div id="leftBtn"><a href="#" class="upBtn"></a></div>
				<div id="rightBtn"><a href="#" class="downBtn"></a></div>
				<div id="task_lb_wrap"><div id="task_lb"></div></div>
			</div>
		</div><!--taskBarWrap end-->

		<div id="lr_bar">
			<ul id="default_app">
				<li id="app0"><span><img src="resource/icon/icon1.png" title="我的博客"/></span><div class="text">我的博客<s></s></div></li>
				<li id="app1"><span><img src="resource/icon/icon2.png" title="关于我"/></span><div class="text">关于我<s></s></div></li>
				<li id="app2"><span><img src="resource/icon/icon3.png" title="作品"/></span><div class="text">作品<s></s></div></li>
				<li id="app3"><span><img src="resource/icon/icon11.png" title="资源分享"/></span><div class="text">资源分享<s></s></div></li>
			</ul>
	
			<div id="default_tools"><span id="showZm_btn" title="全屏"></span><span id="shizhong_btn" title="时钟"></span><span id="weather_btn" title="天气"></span><span id="them_btn" title="主题"></span></div>
	
			<div id="start_block">
				<a title="开始" id="start_btn"></a>
				<div id="start_item">
					<ul class="item admin">
						<li><span class="adminImg"></span> jsfoot</li>
					</ul>
					<ul class="item">
						<li><span class="sitting_btn"></span>系统设置</li>
						<li><span class="help_btn"></span>使用指南 <b></b></li>
						<li><span class="about_btn"></span>关于我们</li>
						<li><span class="logout_btn"></span>退出系统</li>
					</ul>
				</div>
			</div>
	
		</div><!--lr_bar end-->
	
	</body>
</html>