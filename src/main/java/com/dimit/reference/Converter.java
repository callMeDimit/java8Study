package com.dimit.reference;
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}