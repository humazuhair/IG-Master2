package io.github.joxit.pizzeria.webservice;

import io.github.joxit.pizzeria.dto.PizzaDTO;
import io.github.joxit.pizzeria.service.PizzeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author Jones Magloire @Joxit
 *
 * @since 2017-11-02
 */
@RestController
@RequestMapping({"/api", "/api/"})
public class PizzaWebService {
    @Autowired
    private PizzeriaService pizzeriaService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PizzaDTO> getPizzeria(@QueryParam("type") String type) {
        if ("eager".equalsIgnoreCase(type)) {
            return pizzeriaService.getAllWithIng();
        } else if ("custom".equalsIgnoreCase(type)) {
            return pizzeriaService.getAllCustom();
        } else {
            return pizzeriaService.getAll();
        }
    }
}
