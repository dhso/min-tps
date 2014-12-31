<div class="listview">
	<#list page.list as article>
	<a href="#" class="list list-article">
		<div class="list-content">
			<img alt="pic" src="static/img/xp.jpg">
			<div class="data">
				<span class="list-title">${article.text}</span>
				<span class="list-content">${article.content?replace('</?[^<]+>','','r')}</span>
				<span class="list-info">${article.writer}</span>
			</div>
		</div>
	</a>
	</#list>
</div>