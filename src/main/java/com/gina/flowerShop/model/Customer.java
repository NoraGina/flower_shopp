package com.gina.flowerShop.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends User{
   // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   // private Set<ShippingAddress> shippingAddresses;

   // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
   // private Set<OrderCustomer> orderCustomerSet;

    public Customer() {
    }

   // public Customer(Long id, String fullName, String username, String password, Set<Role> roles,
    //                Set<ShippingAddress> shippingAddresses) {
     //   super(id, fullName, username, password, roles);
    //    this.shippingAddresses = shippingAddresses;
   // }

    /*public Set<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    public void setShippingAddresses(Set<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public Set<OrderCustomer> getOrderCustomerSet() {
        return orderCustomerSet;
    }

    public void setOrderCustomerSet(Set<OrderCustomer> orderCustomerSet) {
        this.orderCustomerSet = orderCustomerSet;
    }*/

    @Override
    public String toString() {
        return "Customer{" +

                "} " + super.toString();
    }

}
