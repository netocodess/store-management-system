package acc.br.orders.controller;

import acc.br.orders.dtos.OrderDTO;
import acc.br.orders.dtos.OrderRequestDTO;
import acc.br.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequest) {
		OrderDTO createdOrder = orderService.createOrder(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
		OrderDTO dto = orderService.getOrderByIdDTO(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> dtos = orderService.getAllOrdersDTO();
		return ResponseEntity.ok(dtos);
	}
}
