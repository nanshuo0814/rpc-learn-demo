package com.nanshuo.rpccode.spi.example;

/**
 * 喷墨打印机
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class InkjetPrinter implements Printer {
    @Override
    public void print(String content) {
        System.out.println("Inkjet Printer: " + content);
    }
}