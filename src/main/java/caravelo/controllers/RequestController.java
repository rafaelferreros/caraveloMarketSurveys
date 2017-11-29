package caravelo.controllers;

import caravelo.entities.Provider;
import caravelo.entities.Requester;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ResponseEntity<Requester> requestSurveys(@RequestBody Requester requester) {
        return new ResponseEntity<Requester>(requester, HttpStatus.OK);
    }


}
