package com.tfe.fournil.service;

import com.tfe.fournil.entity.*;
import com.tfe.fournil.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Order service.
 */
@Service
@Slf4j
public class OrderService {
    /**
     * The Order repository.
     */
    @Autowired
    OrderRepository orderRepository;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;


    /**
     * Add order.
     *
     * @param order the order
     */
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    /**
     * Find status waiting by delivery date desc map.
     *
     * @return the map
     */
//*****
    public Map<LocalDate, OrderByDateDTO> findStatusWaitingByDeliveryDateDesc() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.WAITING);
        return mapOrderByDeliveryDate(orders);
    }

    /**
     * Find status in progress by delivery date desc map.
     *
     * @return the map
     */
    public Map<LocalDate, OrderByDateDTO> findStatusInProgressByDeliveryDateDesc() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.IN_PROGRESS);
        return mapOrderByDeliveryDate(orders);
    }

    /**
     * Find by status list.
     *
     * @return the list
     */
    public List<OrderDTO> findByStatus() {
        List<Order> orders = orderRepository.findByStatusIn(Arrays.asList(OrderStatus.IN_PROGRESS, OrderStatus.PACKAGED, OrderStatus.DONE));
        return mapOrder(orders);
    }

    public List<OrderDTO> findByStatusWaiting() {
        List<Order> orders = orderRepository.findByStatusIn(Arrays.asList(OrderStatus.WAITING));
        return mapOrder(orders);
    }

    private List<OrderDTO> mapOrder(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getIdOrder());
            orderDTO.setCreationDate(order.getCreationDate());
            orderDTO.setDeliveryMode(order.getDeliveryMode());
            orderDTO.setPaid(order.getPaid());
            orderDTO.setUser(userService.mapToUserDTO(order.getUser()));
            orderDTO.setDeliveryDate(order.getDeliveryDate());
            orderDTO.setOrderDetailDTOs(mapToOderDetailDto(order.getOrderDetails()));
            orderDTO.setTotal(order.getTotal());
            orderDTO.setStatus(order.getStatus());
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }

    private List<OrderDetailDTO> mapToOderDetailDto(List<OrderDetail> orderDetails) {
        List<OrderDetailDTO> dtos = new ArrayList<>();
        orderDetails.forEach(orderDetail -> {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setIdOrderDetail(orderDetail.getIdOrderDetail());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());
            ProductVersion productVersion = orderDetail.getProductVersion();
            ProductVersionDTO productVersionDTO = new ProductVersionDTO();
            productVersionDTO.setId(productVersion.getId());
            productVersionDTO.setPrice(productVersion.getPrice());
            productVersionDTO.setTaxRate(productVersion.getTaxRate());
            productVersionDTO.setProduct(productVersion.getProduct());
            orderDetailDTO.setProductVersion(productVersionDTO);
            dtos.add(orderDetailDTO);
        });
        return dtos;

    }


    /**
     * Update status to in progress.
     *
     * @param orderIds the order ids
     */
    public void updateStatusToInProgress(List<Long> orderIds) {
        orderIds.forEach(id -> {
            Optional<Order> oderOptional = this.orderRepository.findById(id);
            if (oderOptional.isPresent()) {
                Order order = oderOptional.get();
                order.setStatus(OrderStatus.IN_PROGRESS);
                this.orderRepository.save(order);
            }
        });
    }

    private Map<LocalDate, OrderByDateDTO> mapOrderByDeliveryDate(List<Order> orders) {

        Map<LocalDate, OrderByDateDTO> map = new HashMap<>();
        orders.forEach(order -> {
            OrderByDateDTO orderByDate;
            if (map.containsKey(order.getDeliveryDate())) {
                orderByDate = map.get(order.getDeliveryDate());
            } else {
                orderByDate = new OrderByDateDTO();
                orderByDate.setDeliveryDate(order.getDeliveryDate());
            }
            orderByDate.addProductQuantity(order.getTotalProduct());
            orderByDate.addOrderIds(order.getIdOrder());
            orderByDate.setStatus(order.getStatus());
            order.getOrderDetails().forEach(orderByDate::addProductDetailDTO);
            map.put(order.getDeliveryDate(), orderByDate);
        });
        return map;
    }

    /**
     * Update status.
     *
     * @param id     the id
     * @param status the status
     */
    public void updateStatus(Long id, OrderStatus status) {
        Optional<Order> oderOptional = this.orderRepository.findById(id);
        if (oderOptional.isEmpty()) {
            return;
        }
        Order order = oderOptional.get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public void updateStatusPaid(Long id) {
        Optional<Order> oderOptional = this.orderRepository.findById(id);
        oderOptional.ifPresent(order -> {
            order.setPaid(true);
            orderRepository.save(order);
        });
    }

    /**
     * Find order for current user list.
     *
     * @return the list
     */
    public List<OrderDTO> findOrderForCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (currentUser.isEmpty()) {
            return new ArrayList<>();
        }
        User user = currentUser.get();
        List<Order> allByUserIdUser = this.orderRepository.findAllByUserIdUser(user.getIdUser());

        return mapOrder(allByUserIdUser);
    }
}
