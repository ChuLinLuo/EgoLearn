package com.ego.service;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.pojo.TbContent;

public interface ContentService {
	EUDataGridResult getContentList(long catId, Integer page, Integer rows);
	
	EgoResult saveContent(TbContent content) throws Exception;

	EgoResult deleteById(String ids) throws Exception;

	EgoResult updateContent(TbContent content) throws Exception;
}
