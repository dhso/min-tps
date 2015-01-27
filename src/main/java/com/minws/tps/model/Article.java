package com.minws.tps.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName = "articles", pkName = "id")
public class Article extends Model<Article> {
	public static final Article dao = new Article();

	public Page<Article> findAllArticles(int category_id, int pageNumber, int pageSize) {
		return Article.dao.paginate(pageNumber, pageSize, "select *", "from articles where category_id = ? order by update_dt desc", category_id);
	}
}
