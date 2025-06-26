package br.com.rgsystems.dscomerce.services;

import br.com.rgsystems.dscomerce.dto.OrderDTO;
import br.com.rgsystems.dscomerce.entities.Order;
import br.com.rgsystems.dscomerce.repositories.OrderRepository;
import br.com.rgsystems.dscomerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }
}
