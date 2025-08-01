package com.bepcothu.ambros.service.order;

import com.bepcothu.ambros.model.order.Order;
import com.bepcothu.ambros.model.orderApiPayload.OrderReq;
import com.bepcothu.ambros.model.orderApiPayload.OrderResp;

public interface OrderService {

  Order createOrder(OrderReq orderReq);

  OrderResp getOrderById(Long orderId);
}
