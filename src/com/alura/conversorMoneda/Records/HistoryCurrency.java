package com.alura.conversorMoneda.Records;

public record HistoryCurrency(String base_code, Double amountToConvert, String target_code, Double amountConverted) {
}
