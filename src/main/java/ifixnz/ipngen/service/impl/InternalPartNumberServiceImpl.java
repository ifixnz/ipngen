package ifixnz.ipngen.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import ifixnz.ipngen.service.InternalPartNumberService;

@Service
public class InternalPartNumberServiceImpl implements InternalPartNumberService {

    private Map<String, String> categoryShortCodes;
    private String defaultCategoryCode;
    private String codeMask;

    public InternalPartNumberServiceImpl() {
        this.categoryShortCodes = new ConcurrentHashMap<>(32);
        this.categoryShortCodes.putAll(Map.of(
                "Screen", "SC",
                "Charging Port", "CP",
                "Buttons", "BTN",
                "Camera", "CAM",
                "Speaker", "SPKR",
                "Back Cover", "BC",
                "Full Screen Assembly", "FSC",
                "Touch Screen Digitizer", "TSD",
                "Front Camera", "FCAM",
                "Rear Camera", "RCAM"));
        this.categoryShortCodes.putAll(Map.of(
                "Camera Lens", "CAML",
                "Accessories", "ACC",
                "Screen Protector", "SP",
                "Digitizer", "DIGI",
                "LCD", "LCD",
                "Screen with Digitizer", "SWD"));
        this.defaultCategoryCode = "MISC";
        this.codeMask = "%s%06d";
    }

    @Override
    public String simplePartNumber(int partID, String categoryName) {
        String code = this.categoryShortCodes.containsKey(categoryName) ? this.categoryShortCodes.get(categoryName) : this.defaultCategoryCode;
        return String.format(this.codeMask, code, partID);
    }
}
