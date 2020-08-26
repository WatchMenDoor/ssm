package com.bjsxt.ego.item.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.item.entity.CartItem;
import com.bjsxt.ego.item.service.CartItemService;
import com.bjsxt.ego.rpc.pojo.TbUser;

@Controller
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	//处理将商品加入到购物车的请求
	@RequestMapping("/cart/add/{item.id}")
	public String cartAdd(@PathVariable Long itemid,HttpServletRequest request){
		TbUser tbUser = (TbUser)request.getAttribute("user");
		cartItemService.addItemToCartService(itemid, tbUser.getId());
		return "cartSuccess";
	}
	
	//处理加载购物车列表
	@RequestMapping("/cart/cart")
	public String loadCartItemList(HttpServletRequest request){
		TbUser tbUser = (TbUser)request.getAttribute("user");
		Map<Long, CartItem> carMap = cartItemService.loadCartItemListService(tbUser.getId());
		request.setAttribute("carMap", carMap);
		return "cart";
	}
	
	//更新购物车数量的请求
		@RequestMapping("/cart/update/num/{itemid}/{num}")
		@ResponseBody
		public String cartUpdateNum(@PathVariable Long itemid,@PathVariable Integer num,HttpServletRequest request){
			TbUser tbUser = (TbUser)request.getAttribute("user");
			return cartItemService.updateCartItemNumService(itemid,tbUser.getId(), num);
		}

		
		//删除购物车数量的请求
		@RequestMapping("/cart/delete/${itemid}")
		public String cartDelete(@PathVariable Long itemid,HttpServletRequest request){
			TbUser tbUser = (TbUser)request.getAttribute("user");
			 cartItemService.deleteCartItemNumService(itemid, tbUser.getId());
			 
			 return "redirect:/cart/cart.html";
		}

		//清空购物车
		@RequestMapping("delete/cart/all")
		public String cartDeleteAll(HttpServletRequest request){
			TbUser tbUser = (TbUser)request.getAttribute("user");
			 cartItemService.deleteCartItemAllService(tbUser.getId());;
			 
			 return "redirect:/cart/cart.html";
		}
}
