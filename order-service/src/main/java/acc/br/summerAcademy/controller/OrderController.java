package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.dtos.OrderCreatedEvent;
import acc.br.summerAcademy.service.OrderService;
import acc.br.summerAcademy.domain.model.Order;
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
	public ResponseEntity<OrderCreatedEvent> createOrder(@RequestBody Order order) {
		OrderCreatedEvent response = orderService.createOrder(order);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}

