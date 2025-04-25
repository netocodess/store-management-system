package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.dtos.OrderCreatedEvent;
import acc.br.summerAcademy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderCreatedEvent> createOrder(@RequestBody Orders orders) {
		OrderCreatedEvent response = orderService.createOrder(orders);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}

