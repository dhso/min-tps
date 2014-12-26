<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript" src="static/page/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/page/js/bootstrap.js"></script>
<script type="text/javascript" src="static/page/js/jquery.countdown.js"></script>
<script type="text/javascript" src="static/page/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="static/page/js/selectnav.min.js"></script>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101167626" charset="utf-8"></script>
<script>
	var threadId = null;
	jQuery(document).ready(function() {
		qqlogin();
		/* threadId = qqconnect();
		$("#qqLoginBtn").click(function(){
			var check = QC.Login.check();
			console.log(check);
			var count = 0;
			var is_true = false;
			if(!check){
				QC.Login.showPopup();
			}else{
				if(confirm("确定要退出登录？")){
					QC.Login.signOut();
					$("#qqLoginBtn").css("background","url(static/page/images/Connect_logo_1.png) center no-repeat");
					$("#wish_from").attr("placeholder","匿名");
					$("#qqLoginBtn").attr("data-original-title","登录QQ");
					threadId = qqconnect();
				};
			};
		}); */
		 
		
		$("[rel=tooltip]").tooltip();
		
		$("a[rel^='prettyPhoto']").prettyPhoto({
			animation_speed:'normal',
			theme:'facebook',
			slideshow:3000,
			social_tools:'',
			show_title: false,
			markup: '<div class="pp_pic_holder"> \
				<div class="ppt">&nbsp;</div> \
				<div class="pp_top"> \
					<div class="pp_left"></div> \
					<div class="pp_middle"></div> \
					<div class="pp_right"></div> \
				</div> \
				<div class="pp_content_container"> \
					<div class="pp_left"> \
					<div class="pp_right"> \
						<div class="pp_content"> \
							<div class="pp_loaderIcon"></div> \
							<div class="pp_fade"> \
								<div class="pp_hoverContainer"> \
									<a class="pp_next" href="#"></a> \
									<a class="pp_previous" href="#"></a> \
								</div> \
								<div id="pp_full_res"></div> \
								<div class="pp_details"> \
									<div class="pp_nav"> \
										<a href="#" class="pp_arrow_previous"></a> \
										<p class="currentTextHolder">0/0</p> \
										<a href="#" class="pp_arrow_next"></a> \
									</div> \
									<p class="pp_description"></p> \
									<div class="pp_social">{pp_social}</div> \
									<a class="pp_close" href="#"></a> \
								</div> \
							</div> \
						</div> \
					</div> \
					</div> \
				</div> \
				<div class="pp_bottom"> \
					<div class="pp_left"></div> \
					<div class="pp_middle"></div> \
					<div class="pp_right"></div> \
				</div> \
			</div> \
			<div class="pp_overlay"></div>'
		});
		$(".gallery a[rel^='prettyPhoto']:first").prettyPhoto({
			custom_markup: '<div id="map_canvas" style="width:260px; height:265px"></div>',
			changepicturecallback: function(){ initialize(); }
		});
		$(".gallery a[rel^='prettyPhoto']:last").prettyPhoto({
			custom_markup: '<div id="bsap_1259344" class="bsarocks bsap_d49a0984d0f377271ccbf01a33f2b6d6"></div><div id="bsap_1237859" class="bsarocks bsap_d49a0984d0f377271ccbf01a33f2b6d6" style="height:260px"></div><div id="bsap_1251710" class="bsarocks bsap_d49a0984d0f377271ccbf01a33f2b6d6"></div>',
			changepicturecallback: function(){ _bsap.exec(); }
		});
	});
	
	
	function qqconnect(){
		var checkLogin = false;
		var nickname = "";
		var figureurl ="";
		return setInterval(function(){
			console.log("qqconnect 监视线程");
				checkLogin = QC.Login.check();
				console.log("checkLogin："+checkLogin);
				if(checkLogin){
					if(nickname == "" && figureurl ==""){
						QC.Login.getMe(function(openId, accessToken){
							console.log(openId+","+accessToken);
							var url = "qqLogin?access_token="+accessToken+"&openid="+openId;
							$.getJSON(url, function(json){
								nickname = json.nickname;
								figureurl = json.figureurl_qq_1;
								console.log(nickname+","+figureurl);
								$("#qqLoginBtn").css("background","url("+figureurl+") center no-repeat");
								$("#wish_from").attr("placeholder",nickname);
								$("#qqLoginBtn").attr("data-original-title","退出账号");
								clearInterval(threadId);
								threadId = null;
							});
						});
					}
				};
			},2000);
	}
	
	$(function(){
		 
		 var note = $('#note'),
		  ts = new Date(2012, 0, 1),
		  newYear = true;
		 
		 if((new Date()) > ts){
		  // The new year is here! Count towards something else.
		  // Notice the *1000 at the end - time must be in milliseconds
		  ts = (new Date()).getTime() + 10*24*60*60*1000;
		  newYear = false;
		 }
		  
		 $('#countdown').countdown({
		  timestamp : ts,
		  callback : function(days, hours, minutes, seconds){
		   
		   var message = "";
		   
		   message += days + " day" + ( days==1 ? '':'s' ) + ", ";
		   message += hours + " hour" + ( hours==1 ? '':'s' ) + ", ";
		   message += minutes + " minute" + ( minutes==1 ? '':'s' ) + " and ";
		   message += seconds + " second" + ( seconds==1 ? '':'s' ) + " <br />";
		   if(newYear){
		    message += "left until the new year!";
		   }
		   else {
		    message += "left to 10 days from now!";
		   }
		   note.html(message);
		  }
		 });
		});
	selectnav('nav');
	
	function qqlogin(){
		var aObj = null;
		QC.Login({
		       //btnId：插入按钮的节点id，必选
		       btnId:"qqLoginBtn",    
		       //用户需要确认的scope授权项，可选，默认all
		       scope:"all",
		       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
		       size: "A_XL"
		   }, function(reqData, opts){//登录成功
		       //根据返回数据，更换按钮显示状态方法
		       var nickname = QC.String.escHTML(reqData.nickname);//做xss过滤
		       var figureurl = reqData.figureurl_qq_1;
		       console.log(nickname+","+figureurl);
				$("#qqLoginBtn").css("background","url("+figureurl+") center no-repeat");
				$("#wish_from").attr("placeholder",nickname);
				$("#qqLoginBtn").attr("data-original-title","退出QQ账号");
				$("#qqLoginBtn").attr("onclick","QC.Login.signOut();");
		   }, function(opts){//注销成功
		         alert('QQ登录 注销成功');
					$("#qqLoginBtn").css("background","url(static/page/images/Connect_logo_1.png) center no-repeat");
					$("#wish_from").attr("placeholder","匿名");
		         $("#qqLoginBtn").attr("data-original-title","登录QQ账号");
		         $("#qqLoginBtn").attr("onclick",aObj);
		   }
		);
		
		var sId = setInterval(function(){
			aObj = $("#qqLoginBtn").find("a").attr("onclick");
			console.log("#"+sId+" 进程正在运行...");
			if(aObj){
				console.log("log "+aObj);
				$("#qqLoginBtn").attr("onclick",aObj);
				$("#qqLoginBtn").find("a").remove();
				if(sId){
					console.log("#"+sId + " 进程检测到！");
					//clearInterval(sId);
				};
			};
		},1000);
	}
</script>
<!-- END JAVASCRIPTS -->