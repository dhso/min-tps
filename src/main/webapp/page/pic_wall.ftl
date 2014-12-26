<#assign head_title = "首页">
<#include "/page/common/head.ftl" encoding="UTF-8">
<!--Background Image-->
<img src="http://7sbmku.com1.z0.glb.clouddn.com/background/f6219c4b99eea6885f69a0c686e5d9d4.jpg" id="background"/>
<!--Background Image-->
<!--WRAPPTER START-->
<div class="wrapepr floral-bg">
<#include "/page/common/love_head.ftl" encoding="UTF-8">
      <div class="clearfix"></div>
    <!--header start-->
    <div class="content">
        <div class="inner">
            <#include "/page/common/love_navigation.ftl" encoding="UTF-8">
            <div class="clearfix"></div>
            <!--content start-->
            <div class="innter padding40px content-borders">
                <div class="clearfix"></div>
                <div class="pretty-photos">
				    <#list picList as keyUrl>
						     <a href="http://7sbmku.com2.z0.glb.qiniucdn.com/${keyUrl}?imageView2/2/h/600/q/80" rel="prettyPhoto[wedding]">
					        	<img src="http://7sbmku.com2.z0.glb.qiniucdn.com/${keyUrl}?imageView2/1/w/100/q/36" width="100" height="100" alt="我们的结婚照" style="padding:5px;"/>
					        </a>
				    </#list>
				   </div>
            </div>
            <!--content end-->
        </div>
     <!--FOOTER START-->
    <div class="footer relative">
        <div class="footer-leftside"></div>
        <div class="followus resize">
            <p>啦啦啦</p>
                <ul>
                    <li><a href="#" rel="tooltip" data-original-title="Facebook"><img src="static/page/images/fb.png" alt=""></a></li>
                    <li><a href="#" rel="tooltip" data-original-title="Flickr"><img src="static/page/images/flickr.png" alt=""></a></li>
                    <li><a href="#" rel="tooltip" data-original-title="Vimeo"><img src="static/page/images/vimeo.png" alt=""></a></li>
                    <li><a href="#" rel="tooltip" data-original-title="Twitter"><img src="static/page/images/twitter.png" alt=""></a></li>
                    <li><a href="#" rel="tooltip" data-original-title="Google Plus"><img src="static/page/images/g%2b.png" alt=""></a></li>
                    <li><a href="#" rel="tooltip" data-original-title="Skype"><img src="static/page/images/skype.png" alt=""></a></li>
                </ul>
            <div class="clearfix"></div>
        </div>
        <div class="footer-rightside"></div>
    </div>
    <!--FOOTER START-->
    </div>
    <div class="clearfix"></div>
</div>
<#include "/page/common/love_send_wish.ftl" encoding="UTF-8">
<#include "/page/common/foot.ftl" encoding="UTF-8">
