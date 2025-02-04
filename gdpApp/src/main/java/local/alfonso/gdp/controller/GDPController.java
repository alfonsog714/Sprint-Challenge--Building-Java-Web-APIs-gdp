package local.alfonso.gdp.controller;


import local.alfonso.gdp.GDPComp;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequestMapping("/data")
public class GDPController {
    private static final Logger logger = LoggerFactory.getLogger(GDPController.class);

    //    localhost:2019/data/names
    @GetMapping(value = "/names", produces = {"application/json"})
    public ResponseEntity<?> getAllGDP(HttpServletRequest request) {
        logger.info(request.getRequestURI() + " accessed");

        if (GdpApplication.ourGdpList.gdpList.size() == 0) {
            throw new ResourceNotFoundException("The list of countries was not found, or is empty");
        } else {
            GdpApplication.ourGdpList.gdpList.sort((g1, g2) -> g1.getName().compareToIgnoreCase(g2.getName()));
        }

        return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    //    localhost:2019/data/economy
    @GetMapping(value = "/economy", produces = {"application/json"})
    public ResponseEntity<?> getEconomy(HttpServletRequest req) {
        logger.info(req.getRequestURI() + " accessed");

        if (GdpApplication.ourGdpList.gdpList.size() == 0) {
            throw new ResourceNotFoundException("The list of countries was not found, or is empty");
        } else {
            Collections.sort(GdpApplication.ourGdpList.gdpList, new GDPComp());
        }
        return new ResponseEntity<>(GdpApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    //    localhost:2019/data/country/{id}
    @GetMapping(value = "/country/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCountry(HttpServletRequest req, @PathVariable long id) {
        logger.info(req.getRequestURI() + " accessed");

        GDP rtnGdp;
        if (GdpApplication.ourGdpList.findGDP(g -> (g.getId() == id)) == null) {
            throw new ResourceNotFoundException("Country with id " + id + " not found.");
        } else {
            rtnGdp = GdpApplication.ourGdpList.findGDP(g -> (g.getId() == id));
        }

        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }


    //    localhost:2019/data/economy/table
    @GetMapping(value = "/economy/table")
    public ModelAndView displayGDPTable(HttpServletRequest req) {
        logger.info(req.getRequestURI() + " accessed");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("GDP");

        Collections.sort(GdpApplication.ourGdpList.gdpList, new GDPComp());

        mav.addObject("gdpList", GdpApplication.ourGdpList.gdpList);

        return mav;
    }

    //    localhost:2019/data/country/stats/median
    @GetMapping(value = "/country/stats/median", produces = {"application/json"})
    public ResponseEntity<?> getMedian(HttpServletRequest req) {
        logger.info(req.getRequestURI() + " accessed");

        GdpApplication.ourGdpList.gdpList.sort((g1, g2) -> (int) (g1.getGdp() - g2.getGdp()));

        GDP rtnGdp = GdpApplication.ourGdpList.gdpList.get((GdpApplication.ourGdpList.gdpList.size() / 2) + 1);
        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }

    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> getTotal(HttpServletRequest req)
    {
        logger.info(req.getRequestURI() + " accessed");
        long sum = 0;
        GDP rtnGdp = new GDP();
        if (GdpApplication.ourGdpList.gdpList.size() == 0) {
            throw new ResourceNotFoundException("The list of countries was not found, or is empty");
        } else {
            for (GDP g : GdpApplication.ourGdpList.gdpList) {
                sum += g.getGdp();
            }
            rtnGdp.setName("Total");
            rtnGdp.setGdp(sum);
        }
        return new ResponseEntity<>(rtnGdp, HttpStatus.OK);
    }
}
