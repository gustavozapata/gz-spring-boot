package me.gustavozapata.me.dao;

import me.gustavozapata.me.model.ApiCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ApiDao {
    Collection<ApiCurrency> findAll();
}
