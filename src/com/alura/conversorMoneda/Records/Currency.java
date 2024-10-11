package com.alura.conversorMoneda.Records;

import java.util.ArrayList;

public record Currency(String base_code, String target_code,
                       double conversion_rate, double conversion_result, ArrayList<ArrayList<String>> supported_codes) {
}
