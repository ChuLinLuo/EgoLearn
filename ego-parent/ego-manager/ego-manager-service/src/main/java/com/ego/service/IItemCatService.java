package com.ego.service;

import java.util.List;

import com.ego.bean.EUTreeNode;

public interface IItemCatService {
	List<EUTreeNode> getItemCatList(long parentId) throws Exception;
}
