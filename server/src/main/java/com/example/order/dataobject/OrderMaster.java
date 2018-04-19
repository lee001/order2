package com.example.order.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;
    /** 买家名字 */
    private String buyerName;
    /** 买家电话 */
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid */
    private String buyerOpenid;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 订单状态, 默认为新下单, 0新下单 */
    private Integer orderStatus;
    /** 支付状态, 默认未支付, 0未支付 */
    private Integer payStatus;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date updateTime;

//    @Transient /** 忽略数据库找这个属性*/
//    private List<OrderDetail> orderDetailList;
}
