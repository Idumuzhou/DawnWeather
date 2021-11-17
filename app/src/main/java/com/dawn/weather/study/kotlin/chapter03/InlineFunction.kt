package com.dawn.weather.study.kotlin.chapter03

/**
 *  @author: LXL
 *  @description: 内联函数  inline
 *  内联函数才能完全消除Lambda表达式所带来的运行时开销。
 *  @date: 2021/11/12 14:41
 */
class InlineFunction {
    /**
     * public static int num1AndNum2(int num1, int num2, Function operation) {
        int result = (int) operation.invoke(num1, num2);
            return result;
        }

        public static void main() {
        int num1 = 100;
        int num2 = 80;
        int result = num1AndNum2(num1, num2, new Function() {
        @Override
        public Integer invoke(Integer n1, Integer n2) {
            return n1 + n2;
                }
            });
        }
     */
    //内联函数才能完全消除Lambda表达式所带来的运行时开销。
    inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
        return operation(num1, num2)
    }

    //noinline与crossinline
}

