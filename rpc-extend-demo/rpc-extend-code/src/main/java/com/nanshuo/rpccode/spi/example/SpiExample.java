package com.nanshuo.rpccode.spi.example;

import java.util.ServiceLoader;

/**
 * spi示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class SpiExample {
    public static void main(String[] args) {
        ServiceLoader<Printer> printers = ServiceLoader.load(Printer.class);
        for (Printer printer : printers) {
            printer.print("Hello SPI!");
        }
    }
}