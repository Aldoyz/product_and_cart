package com.aldiichsan.mapper;

import com.aldiichsan.model.CartDetailsInsertModel;
import com.aldiichsan.model.CartDetailsModel;
import com.aldiichsan.model.CustomerModel;
import com.aldiichsan.model.ShoppingCartModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCartMapper {
    List<CartDetailsModel> getCartDetails(Long customerId);
    ShoppingCartModel getShoppingCart(Long customerId);
    void addItemToCart(CartDetailsInsertModel body);
    void addCustomerDetail(CustomerModel body);
    CustomerModel getCustomerDetail();
    void checkOutCart(ShoppingCartModel body);
    void updateCartDetailsStatus(Long id);
    void deleteOneItem(Long id);
}
