package com.vai.vmcapi.utils;

import java.util.UUID;

public class RandomStringGenerator {
    public static String generateUniqueString() {
        String uuid = UUID.randomUUID().toString().replace("-", ""); // Chuyển UUID thành chuỗi hex không có dấu "-"
        return uuid.substring(0, 12).toUpperCase(); // Lấy 8 ký tự đầu tiên
    }

    public static void main(String[] args) {
        System.out.println(generateUniqueString());
    }
}
