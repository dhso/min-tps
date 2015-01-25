$(document).ready(function() {
	var themes = [
	    {
	        value: 'default',
	        text: 'Default'
	    },
	    {
	        value: 'gray',
	        text: 'Gray'
	    },
	    {
	        value: 'metro',
	        text: 'Metro'
	    },
	    {
	        value: 'bootstrap',
	        text: 'Bootstrap'
	    },
	    {
	        value: 'black',
	        text: 'Black'
	    }
	];
	$('#cb-theme').combobox({
	    data: themes,
	    editable: false,
	    panelHeight: 'auto',
	    onChange: onChangeTheme,
	    onLoadSuccess: function () {
	        $(this).combobox('setValue', 'default');
	    }
	});

	$('#myCalendar').calendar({
		formatter:formatDay,
		border:false,
		width:200,
		height:200,
		firstDay:1,
		weeks:['日','一','二','三','四','五','六'],
		months:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
	});
});

//更换主题
function onChangeTheme(theme) {
    var link = $('head').find('link:first');
    link.attr('href', 'static/easyui/themes/' + theme + '/easyui.css');
}

//我的日历
var d1 = Math.floor((Math.random()*30)+1);
var d2 = Math.floor((Math.random()*30)+1);
function formatDay(date){
    var m = date.getMonth()+1;
    var d = date.getDate();
    var opts = $(this).calendar('options');
    if (opts.month == m && d == d1){
        return '<div class="icon-ok md">' + d + '</div>';
    } else if (opts.month == m && d == d2){
        return '<div class="icon-search md">' + d + '</div>';
    }
    return d;
}