package com.base.java.util.algrothim;

public class KMPSuanfa {
    /**
     * KMP算法:
     * 判断字符串a是否包含字符串b,我们称a为文本串,b为模式串
     * a = bcabcabcabbcabcabcabcabd     文本串
     *     ||||||||||/
     * b = bcabcabcabc                  模式串
     *
     *
     * 伪代码:
     *  假设有文本串a和模式串b,index 从1开始
     *  初始化i = 1,j = 0, K(1) =0;
     *  while(i < b.length) {
     *      if(j == 0 || b(i) = b (j)) {
     *          i++;
     *          j++;
     *          K(i) = j;
     *      } else {
     *          j = K(j);
     *      }
     *  }
     *
     *
     *  判断匹配的伪代码如下:
     *  初始化: i = 1, j = 1;
     *  while(i <= a.length && j <= b.length) {
     *      if(j == 0 || a(i) = b(j)) {
     *          i++;
     *          j++;
     *      } else {
     *          j = k(j);
     *      }
     *  }
     *  if(j > b.length) {
     *      return true;
     *  }
     *  return false;
     */
//    public static boolean matchString(String target, String mode) {
//        String newTarget = "x" + target;
//        String newMode = "x" + mode;
//
//        int[] K = calculateK(mode);
//    }
//
//    private static int[] calculate(String mode) {
//        String newMode = "x" + mode;
//        int[] k = new int[newMode.length()];
//        int i = 1;
//        Key
//    }


}
