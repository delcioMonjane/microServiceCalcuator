package rest.restapi.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.restapi.services.RestService;

@RestController
@AllArgsConstructor
public class ApiController {
    RestService restService;

    @GetMapping(value = "/sum", produces = "application/json")
    public ResponseEntity<ResultDTO> sum(@RequestParam double a, @RequestParam double b) {
        double result = this.restService.sum(a, b);
        return new ResponseEntity<>(new ResultDTO(result), HttpStatus.OK);
    }

    @GetMapping(value = "/sub", produces = "application/json")
    public ResponseEntity<ResultDTO> sub(@RequestParam double a, @RequestParam double b) {
        ResultDTO resultDTO = new ResultDTO(this.restService.sub(a, b));
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/mul", produces = "application/json")
    public ResponseEntity<ResultDTO> mul(@RequestParam double a, @RequestParam double b) {
        ResultDTO resultDTO = new ResultDTO(this.restService.mul(a, b));
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/div", produces = "application/json")
    public ResponseEntity<ResultDTO> div(@RequestParam double a, @RequestParam double b) {
        ResultDTO resultDTO = new ResultDTO(this.restService.div(a, b));
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
