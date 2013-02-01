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
					   'title':'jsfoot��ҳ��Ч',
					   'url':'http://www.jsfoot.com/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app1':{
					   'title':'jQuery��Ч',
					   'url':'http://www.jsfoot.com/jquery/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app2':{
					   'title':'javascript��Ч',
					   'url':'http://www.jsfoot.com/js/',
					   'winWidth':1100,
					   'winHeight':650
					   },
				'app3':{
					   'title':'��Դ����',
					   'url':'ziyuan.html',
					   'winWidth':1100,
					   'winHeight':650
					   }
					   };
		 	var deskIconData={
			        'kuwoMusic':{
					   'title':'�������ֺ�',
					   'url':'http://mbox.kuwo.cn/',
					   'winWidth':950,
					   'winHeight':500
						},
					'hudong':{
					   'title':'�����ٿ�',
					   'url':'http://lab.hudong.com/webqq/index.html',
					   'winWidth':950,
					   'winHeight':500
						},
					'dubianFim':{
					   'title':'����FIM',
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
					   'title':'�������',
					   'url':'http://webqq.qidian.com',
					   'winWidth':942,
					   'winHeight':547
						},
					'leshiwang':{
					   'title':'������',
					   'url':'http://www.letv.com/cooperation/qq.html',
					   'winWidth':842,
					   'winHeight':547
						},
					'qianqianMusic':{
					   'title':'ǧǧ����',
					   'url':'http://www.qianqian.com/paihang.html',
					   'winWidth':930,
					   'winHeight':500
						},
					'zfMeishi':{
					   'title':'������ʳ',
					   'url':'http://www.zhms.cn/',
					   'winWidth':930,
					   'winHeight':500
						},
					'mglvyou':{
						'title':'â������',
					   'url':'http://www.mangocity.com/webqq/bookFlight.html',
					   'winWidth':930,
					   'winHeight':500
						},	
					'taobao':{
						'title':'�Ա���',
					   'url':'http://marketing.taobao.com/home/webqq/index.htm',
					   'winWidth':930,
					   'winHeight':500
						},	
					'qingshu':{
						'title':'����',
					   'url':'http://www.qingshu8.net/',
					   'winWidth':930,
					   'winHeight':500
						},
					'fenghuang':{
						'title':'�����',
					   'url':'http://www.ifeng.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'zhongguancun':{
						'title':'�йش�����',
					   'url':'http://www.zol.com.cn/',
					   'winWidth':930,
					   'winHeight':500
						},
					'win35':{
						'title':'�Ѻ�����',
					   'url':'http://auto.sohu.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'win36':{
						'title':'������ӰƱ',
					   'url':'http://piao.buding.cn/',
					   'winWidth':900,
					   'winHeight':500
						},	
					'win37':{
						'title':'�й���ѧ��Դ��',
					   'url':'http://www.mathrs.net/',
					   'winWidth':930,
					   'winHeight':500
						},
					'win38':{
						'title':'��Ӱ������������',
					   'url':'http://www.manmankan.com/',
					   'winWidth':930,
					   'winHeight':500
						},	
					'win39':{
						'title':'������Ժ',
					   'url':'http://www.xxsy.net/',
					   'winWidth':930,
					   'winHeight':500
						}  
			};			   
 		   
			//�洢���沼��Ԫ�ص�jquery����
			myLib.desktop.desktopPanel();
 		   
		   //��ʼ�����汳��
			myLib.desktop.wallpaper.init("resource/images/blue_glow.jpg");
		   
		   //��ʼ��������
			myLib.desktop.taskBar.init();
		   
		   //��ʼ������ͼ��
			myLib.desktop.deskIcon.init(deskIconData);
		   
		   //��ʼ�����浼����
			myLib.desktop.navBar.init();
		   
		   //��ʼ�������
			myLib.desktop.lrBar.init(lrBarIconData);
		   
		   //��ӭ����
		/*	myLib.desktop.win.newWin({
				WindowTitle:'��ӭ����',
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

		<a href="http://www.baidu.com/" class="powered_by">��ɽ˼��</a>

		<div id="wallpapers"></div>

		<div id="navBar">
			<a href="#" class="currTab" title="����1"></a>
			<a href="#"  title="����2"></a>
			<a href="#"  title="����3"></a>
			<a href="#"  title="����4"></a>
		</div>

		<div id="desktopPanel">
			<div id="desktopInnerPanel">
				<ul class="deskIcon currDesktop">
					<li class="desktop_icon" id="leshiwang"><span class="icon"><img src="resource/icon/icon4.png"/></span><div class="text">������<s></s></div></li>
					<li class="desktop_icon" id="Pixlr"><span class="icon"><img src="resource/icon/icon6.png"/></span><div class="text">Pixlr<s></s></div></li>
					<li class="desktop_icon" id="dubianFim"><span class="icon"><img src="resource/icon/icon7.png"/></span><div class="text">����FIM<s></s></div></li>
					<li class="desktop_icon" id="kuwoMusic"><span class="icon"><img src="resource/icon/icon8.png"/></span><div class="text">�������ֺ�<s></s></div></li>
					<li class="desktop_icon" id="qidian"><span class="icon"><img src="resource/icon/icon9.png"/></span><div class="text">�������<s></s></div></li>
					<li class="desktop_icon" id="hudong"><span class="icon"><img src="resource/icon/icon10.png"/></span><div class="text">�����ٿ�<s></s></div></li>
					<li class="desktop_icon" id="qianqianMusic"><span class="icon"><img src="resource/icon/icon5.png"/></span><div class="text">ǧǧ����<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">���<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="zfMeishi"><span class="icon"><img src="resource/icon/icon13.png"/></span><div class="text">������ʳ<s></s></div></li>
					<li class="desktop_icon" id="mglvyou"><span class="icon"><img src="resource/icon/icon12.png"/></span><div class="text">â������<s></s></div></li>
					<li class="desktop_icon" id="taobao"><span class="icon"><img src="resource/icon/icon14.png"/></span><div class="text">�Ա���<s></s></div></li>
					<li class="desktop_icon" id="qingshu"><span class="icon"><img src="resource/icon/icon15.png"/></span><div class="text">����<s></s></div></li>
					<li class="desktop_icon" id="fenghuang"><span class="icon"><img src="resource/icon/icon16.png"/></span><div class="text">�����<s></s></div></li>
					<li class="desktop_icon" id="zhongguancun"><span class="icon"><img src="resource/icon/icon17.png"/></span><div class="text">�йش�����<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">���<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="win35"><span class="icon"><img src="resource/icon/icon18.png"/></span><div class="text">�Ѻ�����<s></s></div></li>
					<li class="desktop_icon" id="win36"><span class="icon"><img src="resource/icon/icon19.png"/></span><div class="text">������ӰƱ<s></s></div></li>
					<li class="desktop_icon" id="win37"><span class="icon"><img src="resource/icon/icon8.png"/></span><div class="text">�й���ѧ��Դ��<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">���<s></s></div></li>
				</ul>
				<ul class="deskIcon">
					<li class="desktop_icon" id="win38"><span class="icon"><img src="resource/icon/icon20.png"/></span><div class="text">��Ӱ������������<s></s></div></li>
					<li class="desktop_icon" id="win39"><span class="icon"><img src="resource/icon/icon21.png"/></span><div class="text">������Ժ<s></s></div></li>
					<li class="desktop_icon add_icon"><span class="icon"><img src="resource/images/add_icon.png"/></span><div class="text">���<s></s></div></li>
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
				<li id="app0"><span><img src="resource/icon/icon1.png" title="�ҵĲ���"/></span><div class="text">�ҵĲ���<s></s></div></li>
				<li id="app1"><span><img src="resource/icon/icon2.png" title="������"/></span><div class="text">������<s></s></div></li>
				<li id="app2"><span><img src="resource/icon/icon3.png" title="��Ʒ"/></span><div class="text">��Ʒ<s></s></div></li>
				<li id="app3"><span><img src="resource/icon/icon11.png" title="��Դ����"/></span><div class="text">��Դ����<s></s></div></li>
			</ul>
	
			<div id="default_tools"><span id="showZm_btn" title="ȫ��"></span><span id="shizhong_btn" title="ʱ��"></span><span id="weather_btn" title="����"></span><span id="them_btn" title="����"></span></div>
	
			<div id="start_block">
				<a title="��ʼ" id="start_btn"></a>
				<div id="start_item">
					<ul class="item admin">
						<li><span class="adminImg"></span> jsfoot</li>
					</ul>
					<ul class="item">
						<li><span class="sitting_btn"></span>ϵͳ����</li>
						<li><span class="help_btn"></span>ʹ��ָ�� <b></b></li>
						<li><span class="about_btn"></span>��������</li>
						<li><span class="logout_btn"></span>�˳�ϵͳ</li>
					</ul>
				</div>
			</div>
	
		</div><!--lr_bar end-->
	
	</body>
</html>