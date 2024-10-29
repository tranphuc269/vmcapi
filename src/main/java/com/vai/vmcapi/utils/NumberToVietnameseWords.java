package com.vai.vmcapi.utils;

import java.text.DecimalFormat;

public class NumberToVietnameseWords {

    private static final String[] units = { "", "Một", "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Tám", "Chín" };
    private static final String[] tens = { "", "", "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Tám", "Chín" };

    public static String convertToVietnameseWords(long number) {
        if (number == 0) {
            return "không đồng";
        }
        StringBuilder sb = new StringBuilder();
        if (number < 0) {
            sb.append("âm ");
            number = -number;
        }
        String[] unitsArray = { "đồng", "nghìn", "triệu", "tỷ" };
        int unitIndex = 0;
        while (number > 0) {
            int group = (int) (number % 1000);
            if (group != 0) {
                String groupWords = convertGroupToWords(group);
                sb.insert(0, groupWords + " " + unitsArray[unitIndex] + " ");
            }
            unitIndex++;
            number /= 1000;
        }
        return sb.toString().trim();
    }

    private static String convertGroupToWords(int number) {
        StringBuilder sb = new StringBuilder();
        int hundreds = number / 100;
        int tens = (number % 100) / 10;
        int ones = number % 10;

        if (hundreds > 0) {
            sb.append(units[hundreds]).append(" trăm ");
            if (tens == 0 && ones > 0) {
                sb.append("linh ");
            }
        }
        if (tens > 1) {
            sb.append(NumberToVietnameseWords.tens[tens]).append(" mươi ");
            if (ones == 1) {
                sb.append("mốt ");
            } else if (ones == 5) {
                sb.append("lăm ");
            } else if (ones > 0) {
                sb.append(units[ones]).append(" ");
            }
        } else if (tens == 1) {
            sb.append("mười ");
            if (ones > 0) {
                sb.append(units[ones]).append(" ");
            }
        } else if (ones > 0) {
            sb.append(units[ones]).append(" ");
        }
        return sb.toString().trim();
    }
}
