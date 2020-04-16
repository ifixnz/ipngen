package ifixnz.ipngen.controller;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ifixnz.ipngen.service.InternalPartNumberService;

@RestController
public class InternalPartNumberController {

    private InternalPartNumberService internalPartNumberService;

    @Autowired
    public InternalPartNumberController(InternalPartNumberService internalPartNumberService) {
        this.internalPartNumberService = requireNonNull(internalPartNumberService);
    }

    @PostMapping(value = "/ipn/new")
    public ResponseEntity<Object> generateNewInternalPartNumber(@RequestBody Map<String, Object> part) {
        String internalPartNumber = null;
        if (part.containsKey("internalPartNumber") && part.get("internalPartNumber") != null && String.valueOf(part.get("internalPartNumber")).length() > 1) {
            internalPartNumber = String.valueOf(part.get("internalPartNumber"));
        } else {
            String categoryName = null;
            int partID = -1;
            if (part.containsKey("@id")) {
                try {
                    partID = Integer.parseInt(String.valueOf(part.get("@id")).replace("/api/parts/", ""));
                } catch (NumberFormatException e) {
                    partID = -2;
                    e.printStackTrace();
                }
            }
            if (part.containsKey("category")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> cate = (Map<String, Object>) part.get("category");
                if (cate.containsKey("name")) {
                    categoryName = String.valueOf(cate.get("name"));
                }
            }
            // TODO if partID < 0 throw expection
            internalPartNumber = internalPartNumberService.simplePartNumber(partID, categoryName);
        }
        Map<String, Object> resp = Map.of("@id", part.get("@id"),
                "name", part.get("name"),
                "categoryPath", part.get("categoryPath"),
                "internalPartNumber", internalPartNumber);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}
