package br.com.rgsystems.dscomerce.dto;

import br.com.rgsystems.dscomerce.entities.Order;
import br.com.rgsystems.dscomerce.entities.OrderItem;
import br.com.rgsystems.dscomerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO clientDTO;
    private PaymentDTO paymentDTO;

    @NotEmpty(message = "Deve ter ao menos um item")
    private List<OrderItemDTO> itemDTOS = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO clientDTO, PaymentDTO paymentDTO) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.clientDTO = clientDTO;
        this.paymentDTO = paymentDTO;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        clientDTO = new ClientDTO(entity.getClient());
        paymentDTO = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item : entity.getItems()){
            OrderItemDTO items = new OrderItemDTO(item);
            itemDTOS.add(items);
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public List<OrderItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public Double getTotal(){
        double sum = 0.0;

        for (OrderItemDTO itemDTO : itemDTOS){
            sum += itemDTO.getSubTotal();
        }

        return sum;
    }
}
