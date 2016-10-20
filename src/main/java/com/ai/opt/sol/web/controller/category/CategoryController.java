package com.ai.opt.sol.web.controller.category;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@RequestMapping("/tree")
	public ModelAndView treeView() {
		ModelAndView view = new ModelAndView("/serviceDefine/service_tree");
		return view;
	}

	@RequestMapping("/treeData")
	@ResponseBody
	public List<SolCategory> save(HttpSession session, String categoryId) {
		// TODO 获取目录数据

		// 测试数据
		List<SolCategory> categoryList = new ArrayList<SolCategory>();
		if (categoryId == null) {
			SolCategory category = new SolCategory();
			category.setCategoryCode("master");
			category.setCategoryId("01");
			category.setCategoryName("服务在线总目录");
			category.setParentCategoryId("-1");
			categoryList.add(category);
			return categoryList;
		}
		if ("01".equals(categoryId)) {
			SolCategory category1 = new SolCategory();
			category1.setCategoryCode("chil01");
			category1.setCategoryId("001");
			category1.setCategoryName("公共管理");
			category1.setParentCategoryId("01");
			categoryList.add(category1);
			SolCategory category2 = new SolCategory();
			category2.setCategoryCode("chil02");
			category2.setCategoryId("002");
			category2.setCategoryName("系统管理");
			category2.setParentCategoryId("01");
			categoryList.add(category2);
			return categoryList;
		}
		if ("001".equals(categoryId)) {
			SolCategory category11 = new SolCategory();
			category11.setCategoryCode("chil0101");
			category11.setCategoryId("0011");
			category11.setCategoryName("查询");
			category11.setParentCategoryId("001");
			categoryList.add(category11);
			return categoryList;
		}
		return categoryList;
	}
}
