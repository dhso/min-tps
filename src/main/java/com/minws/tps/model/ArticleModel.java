package com.minws.tps.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.minws.frame.kit.StringUtils;

@SuppressWarnings({ "serial", "rawtypes" })
public class ArticleModel extends Model<Model> {

	public static Page<Record> getArticleList(int pageNumber, int pageSize, String writer) {
		String select = "select cct.tid,cct.id,cmt.text,cmt.open,cmt.writer,cmt.update_dt,cct.content";
		String sqlExceptSelect = "from codepad_content_tab cct left join codepad_menu_tree cmt on cmt.id = cct.tid where open = '1'";
		if (StringUtils.isNotEmpty(writer)) {
			sqlExceptSelect = "from codepad_content_tab cct left join codepad_menu_tree cmt on cmt.id = cct.tid where writer = ?  or open = '1'";
		}
		Page<Record> articles = Db.paginate(pageNumber, pageSize, select, sqlExceptSelect, writer);
		return articles;
	}

}
