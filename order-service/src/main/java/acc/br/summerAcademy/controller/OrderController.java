package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.dtos.OrderDTO;
import acc.br.summerAcademy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody Orders order) {
		OrderDTO createdOrder = orderService.createOrder(order);
		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
		Orders order = orderService.getOrderById(id)
				.orElseThrow(); // Exceção já tratada no service
		OrderDTO dto = convertToDTO(order);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders); // Você pode mapear para DTOs se preferir
	}

	private OrderDTO convertToDTO(Orders order) {
		return new OrderDTO(
				order.getOrderId(),
				order.getProduct().getProductName(),
				order.getProduct().getValue(),
				order.getProduct().getDescription(),
				order.getQuantity(),
				order.getStatus(),
				order.getCreatedAt(),
				order.getUpdatedAt(),
				order.getDateTimeDeparture(),
				order.getSeller().getId()
		);
	}
}

