package com.ego.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ego.bean.EUTreeNode;
import com.ego.bean.EgoResult;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import com.ego.pojo.TbContentCategoryExample.Criteria;
import com.ego.service.ContentCategoryService;

@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getContentCategoryList(long parentid)
			throws Exception {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentid).andStatusEqualTo(1);
		List<TbContentCategory> list = contentCategoryMapper
				.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			// 判断是否是父节点
			if (tbContentCategory.getIsParent()) {
				node.setState("closed");
			} else {
				node.setState("open");
			}
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public EgoResult addNode(long parentid, String name) throws Exception {
		Date date = new Date();
		TbContentCategory node = new TbContentCategory();
		node.setName(name);
		node.setParentId(parentid);
		node.setIsParent(false);
		node.setCreated(date);
		node.setUpdated(date);
		node.setSortOrder(1);
		// 状态。可选值:1(正常),2(删除)
		node.setStatus(1);
		// 插入新节点。需要返回主键
		contentCategoryMapper.insert(node);
		// 判断如果父节点的isparent不是true修改为true
		// 取父节点的内容
		TbContentCategory parentNode = contentCategoryMapper
				.selectByPrimaryKey(parentid);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		// 把新节点返回
		return EgoResult.ok(node);

	}

	@Override
	public EgoResult rename(Long id, String name) throws Exception {
		TbContentCategory category = new TbContentCategory();
		category.setId(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(category);
		return EgoResult.ok(category);
	}

	@Override
	public EgoResult deleteById(Long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.or().andParentIdEqualTo(id);
		TbContentCategory contentCategory = new TbContentCategory();
		//删除状态为2
		contentCategory.setId(id);
		contentCategory.setStatus(2);
		//根据parentid查子节点
		List<TbContentCategory> nodes = contentCategoryMapper.selectByExample(example);
		if(nodes!=null&&nodes.size()>0){
			for (TbContentCategory category : nodes) {
				deleteById(category.getId());
			}
			
		}
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return EgoResult.ok();
	}
}
