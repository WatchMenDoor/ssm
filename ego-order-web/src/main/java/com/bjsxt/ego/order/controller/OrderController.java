package com.bjsxt.ego.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.order.entity.CartItem;
import com.bjsxt.ego.order.service.OrderService;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.pojo.TbUser;

@Controller
public class OrderController {
	
	
	@Autowired
	private OrderService orderService;
	//进行页面调转
	@RequestMapping("/{url}")
	public String loadPage(@PathVariable String url){
		return url;
	}

	
	
	//调转到订单确认的地址
	@RequestMapping("/order/cart")
	public String orderCart(HttpServletRequest request){
		
		TbUser tbUser =(TbUser) request.getAttribute("user");
		Map<Long, CartItem> map = orderService.loadCartItemMapService(tbUser.getId());
		
		request.setAttribute("cartMap", map);
		return "ordercart";
	}
	
	
	//订单信息的保存
		@RequestMapping("/order/save")
		public String orderSave(TbOrder tbOrder,TbOrderShipping tbOrderShipping,HttpServletRequest request){
			
			TbUser tbUser =(TbUser) request.getAttribute("user");
			Map<String, String> map = orderService.saveOrderService(tbOrder, tbUser.getId(), tbOrderShipping);
			
			request.setAttribute("orderid", map.get("orderId"));
			request.setAttribute("total", map.get("total"));
			return "success";
		}
		
		
		//处理加载用户订单列表
				@RequestMapping("/order/list")
				public String orderList(HttpServletRequest request){
					
					TbUser tbUser =(TbUser) request.getAttribute("user");
					List<TbOrder> list = orderService.loadOrderList(tbUser.getId());
					
					
					request.setAttribute("orderlist",list);
					return "orders";
				}
				
				
				 
				
				//查看订单明细
				@RequestMapping("/order/detail/list/${o.orderId}t")
				public String orderDetailList(@PathVariable String orderid,Model model){
					
					
					List<TbOrderItem> list = orderService.loadOrderItemList(orderid);
					
					
					model.addAttribute("list",list);
					return "ordersdetail";
				}
}
