package com.aldiichsan.service.impl;

import com.aldiichsan.exception.ApiExceptionHandling;
import com.aldiichsan.mapper.ProductMapper;
import com.aldiichsan.mapper.ShoppingCartMapper;
import com.aldiichsan.model.*;
import com.aldiichsan.service.ShoppingCartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service("shoppingCartServiceMybatis")
public class ShoppingCarterviceImpl implements ShoppingCartService {

    private static final Logger log = LogManager.getLogger(ShoppingCarterviceImpl.class);

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ApiExceptionHandling apiExceptionHandling;

    @Override
    public CheckOutCartModel getCartDetails(Long customerId)  {
        try {
            List<CartDetailsModel> carts = shoppingCartMapper.getCartDetails(customerId);

            CheckOutCartModel checkOut = new CheckOutCartModel();
            checkOut.setShoppingCartId(carts.get(0).getShoppingCartId());

            CustomerModel customer = new CustomerModel();
            customer.setId(carts.get(0).getCustomerId());
            customer.setName(carts.get(0).getCustomerName());
            customer.setAddress(carts.get(0).getAddress());

            checkOut.setCustomer(customer);

            List<CheckOutProductModel> products = new ArrayList<>();
            Long overallTotal = 0L;
            for (CartDetailsModel c : carts) {
                CheckOutProductModel product = new CheckOutProductModel();
                product.setCartDetailsId(c.getCartDetailsId());
                product.setId(c.getProductId());
                product.setName(c.getProductName());

                ProductTypeModel type = new ProductTypeModel();
                type.setId(c.getTypeId());
                type.setType(c.getType());

                product.setType(type);
                product.setPrice(c.getPrice());
                product.setQuantity(c.getQuantity());
                product.setTotal(c.getTotal());
                product.setStatus(c.getStatus());

                products.add(product);
                overallTotal = overallTotal + c.getTotal();
            }
            checkOut.setProducts(products);
            checkOut.setOverallTotal(overallTotal);
            return checkOut;
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
            return null;
        }
    }

    @Override
    public void addItemToCart(CartDetailsInsertModel body) {
        try {
            CustomerModel customer;
            shoppingCartMapper.addCustomerDetail(body.getCustomer());
            customer = shoppingCartMapper.getCustomerDetail();

            ProductModel product = productMapper.findById(body.getProductId());
            if (product == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for id : "+body.getProductId());
            }
            Long total = product.getPrice() * body.getQuantity();
            body.setTotal(total);

            ShoppingCartModel shoppingCart = new ShoppingCartModel();
            shoppingCart.setCustomerId(customer.getId());
            shoppingCartMapper.checkOutCart(shoppingCart);

            shoppingCart = shoppingCartMapper.getShoppingCart(
                    body.getCustomer().getId() != null
                            ? body.getCustomer().getId()
                            : customer.getId()
            );
            if (shoppingCart == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cart Details not found for customer id : "+customer.getId()
                );
            }
            body.setShoppingCartId(shoppingCart.getId());
            body.setStatusId(1L);

            shoppingCartMapper.addItemToCart(body);
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
        }
    }

    @Override
    public void checkOutItem(CheckOutCartModel body) {
        try {
            List<CheckOutProductModel> products = body.getProducts();
            for (CheckOutProductModel p : products) {
                shoppingCartMapper.updateCartDetailsStatus(p.getCartDetailsId());
            }

            ShoppingCartModel cart = new ShoppingCartModel();
            cart.setId(body.getShoppingCartId());
            cart.setCustomerId(body.getCustomer().getId());
            cart.setOverallTotal(body.getOverallTotal());

            shoppingCartMapper.checkOutCart(cart);
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
        }
    }

    @Override
    public void deleteOneItem(Long id) {
        try {
            shoppingCartMapper.deleteOneItem(id);
        } catch (Exception e) {
            apiExceptionHandling.ExceptionHandling(e);
        }
    }
}
