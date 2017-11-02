package io.github.joxit.pizzeria.service;

import io.github.joxit.pizzeria.dto.PizzaDTO;
import io.github.joxit.pizzeria.mapper.PizzaMapper;
import io.github.joxit.pizzeria.model.Stock;
import io.github.joxit.pizzeria.persistence.PizzaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jones Magloire @Joxit
 *
 * @since 2017-11-01
 */
@Service
// For lazy loading
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PizzeriaService {
    @Autowired
    private PizzaDAO pizzaDAO;
    @Autowired
    private PizzaMapper pizzaMapper;

    public List<PizzaDTO> getAll() {
        return pizzaDAO.getAll().stream().map(pizzaMapper::modelToDTO).collect(Collectors.toList());
    }

    public List<PizzaDTO> getAllWithIng() {
        return pizzaDAO.getEager().stream().map(pizzaMapper::modelToDTO).collect(Collectors.toList());
    }

    public List<PizzaDTO> getAllCustom() {
        return pizzaDAO.getCustom().stream().map(pizzaMapper::modelToDTO).collect(Collectors.toList());
    }

    public <T extends Stock> void updateStock(T stock) {

    }
}
