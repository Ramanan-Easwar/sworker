package org.worker.fib;

public class Factorial {

    public static Long fact(Long number) {
        if(number > 1)
            return number * fact(number - 1);
        return 1L;
    }


}
