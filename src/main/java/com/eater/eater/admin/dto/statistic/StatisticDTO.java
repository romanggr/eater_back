package com.eater.eater.admin.dto.statistic;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatisticDTO {
    private long ordersPerDay;
    private double averageOrderValue;
    private double appEarningsPerDay;
    private double cashFlowPerDay;
    private long courierQuantity;
    private long clientQuantity;
    private long restaurantQuantity;
}
