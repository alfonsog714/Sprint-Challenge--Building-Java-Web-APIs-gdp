package local.alfonso.gdp.controller;


import local.alfonso.gdp.GDPComp;
import local.alfonso.gdp.GDPList;
import local.alfonso.gdp.GdpApplication;
import local.alfonso.gdp.exception.ResourceNotFoundException;
import local.alfonso.gdp.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/data")
public class GDPController {
    private static final Logger logger = LoggerFactory.getLogger(GDPController.class);

//    localhost:2019/data/names
    @GetMapping(value = "/names", produces = {"application/json"})
    public ResponseEntity<?> getAllGDP(HttpServletRequest request)
    {
        logger.info(request.getRequestURI() + " accessed");

        GdpApplication.ourGdpList.gdpList.sort((g1, g2) -> g1.getName().compareToIgnoreCase(g2.getName()));
        return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

//    localhost:2019/data/economy
    @GetMapping(value = "/economy", produces = {"application/json"})
    public ResponseEntity<?> getEconomy(HttpServletRequest req)
    {
        logger.info(req.getRequestURI() + " accessed");

       Collections.sort(GdpApplication.ourGdpList.gdpList, new GDPComp());
       return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

//    localhost:2019/data/{id}
    @GetMapping(value = "/country/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCountry(HttpServletRequest req, @PathVariable long id){
        logger.info(req.getRequestURI() + " accessed");

        GDP rtnGdp;
        if(GdpApplication.ourGdpList.findGDP(g -> (g.getId() == id)) == null )
        {
            throw new ResourceNotFoundException("Country with id " + id + " not found.");
        } else
        {
            rtnGdp = GdpApplication.ourGdpList.findGDP(g -> (g.getId() == id));
        }

        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }

}
