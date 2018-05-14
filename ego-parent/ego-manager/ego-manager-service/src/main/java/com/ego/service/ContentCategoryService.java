package com.ego.service;

import java.util.List;

import com.ego.bean.EUTreeNode;
import com.ego.bean.EgoResult;

public interface ContentCategoryService {
	List<EUTreeNode> getContentCategoryList(long parentid) throws Exception;
	EgoResult addNode(long parentid, String name) throws Exception;
	EgoResult rename(Long id, String name) throws Exception;
	EgoResult deleteById(Long id);
}
