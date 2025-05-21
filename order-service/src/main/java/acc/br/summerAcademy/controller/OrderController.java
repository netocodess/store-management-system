package acc.br.summerAcademy.controller;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.dtos.OrderDTO;
import acc.br.summerAcademy.dtos.OrderRequestDTO;
import acc.br.summerAcademy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
