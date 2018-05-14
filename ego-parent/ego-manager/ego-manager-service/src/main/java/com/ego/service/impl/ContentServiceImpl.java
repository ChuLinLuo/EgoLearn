package com.ego.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.ego.pojo.TbContentExample.Criteria;
import com.ego.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public EUDataGridResult getContentList(long catId, Integer page,
			Integer rows) {
		// 根据category_id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EUDataGridResult result = new EUDataGridResult(pageInfo.getTotal(),list);
		return result;
	}

	@Override
	public EgoResult saveContent(TbContent content) throws Exception {
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		return EgoResult.ok();
	}

	@Override
	public EgoResult deleteById(String ids) throws Exception {
		String[] idss = ids.split(",");
		if(idss.length>0){
			for (String id : idss) {
				contentMapper.deleteByPrimaryKey(Long.valueOf(id));
			}
		}
		return EgoResult.ok();
	}

	@Override
	public EgoResult updateContent(TbContent content) throws Exception {
		Date date = new Date();
		content.setUpdated(date);
		contentMapper.updateByPrimaryKeySelective(content);
		return EgoResult.ok();
	}

}
