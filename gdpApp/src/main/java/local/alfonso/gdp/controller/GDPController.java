package local.alfonso.gdp.controller;


import local.alfonso.gdp.GdpApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


}
