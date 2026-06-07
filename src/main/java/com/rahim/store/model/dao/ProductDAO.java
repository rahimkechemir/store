package com.rahim.store.model.dao;

import com.rahim.store.model.LocalUser;
import com.rahim.store.model.Product;
import com.rahim.store.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductDAO extends ListCrudRepository<Product ,  Long> {
}
