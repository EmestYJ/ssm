package com.lagou.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: Emest
 * @date: 2021/5/8$ 15:30$
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        // "abbaca", [b,x]
        /*String str = "abbaca";
        char[] chars = {'b', 'x'};
        System.out.println(specialStr(str, chars));*/
        // "abc%","zxab%c%%",[%, #]
        /*String s0 = "abc%";
        String s1 = "zx#ab%c%%";
        char[] chars = {'%', '#'};
        System.out.println(getMostStr(s0, s1, chars));*/
        // System.out.println(sumZu("word", 2));
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] StrArr = str.toCharArray();// 把字符串转为字符数组toCharArray

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        if (!(StrArr == null || StrArr.length == 0))// 先判断字符数组是否为空
            for (int i = 0; i < StrArr.length; i++)
                if (null != map.get(StrArr[i]))
                    // 若不为空，说明已经存在相同字符，则Value值在原来的基础上加1
                    map.put(StrArr[i], map.get(StrArr[i]) + 1);
                else
                    map.put(StrArr[i], 1);
        // 找map中Value的最大值maxValue，类似于选择排序，寻找最大值的过程：
        // 先任取一个Value值定义为最大值，然后与之比较
        int maxValue = map.get(StrArr[0]);
        char ch = StrArr[0];
        for (int j = 0; j < StrArr.length; j++)
            if (maxValue < map.get(StrArr[j])) {
                maxValue = map.get(StrArr[j]);
                ch = StrArr[j];
            }
        System.out.println(ch);// ababcada
    }
    public static String getMostStr (String s0, String s1, char[] cList) {
        // write code here
        String str1 = getStr(s0, s1, cList);
        String str2 = getStr(s1, s0, cList);
        int len1 = str1.length();
        int len2 = str2.length();
        return len1>len2?str1:str2;
    }

    public static String getStr (String s0, String s1, char[] cList) {
        // write code here
        if(null == s0 || null == s1) return null;
        if("".equals(s0) || "".equals(s1)) return null;
        int len1 = s0.length();
        int len2 = s1.length();
        int start = 0;

        int[] topLine = new int[len1];
        int[] currentLine = new int[len1];

        int maxLen = 0;

        int pos = 0;
        char ch = ' ';
        for(int i=0; i<len2; ++i) {
            ch = s1.charAt(i);
            for(int j=0; j<len1; ++j) {
                if(isBaoHan(s0.charAt(j), cList)) {
                    start = j;
                }else if(ch == s0.charAt(j) || isBaoHan(ch, cList)) {
                    if(0 == j) {
                        currentLine[j] = 1;
                    } else if(!isBaoHan(s0.charAt(j), cList)) {
                        currentLine[j] = topLine[j-1] + 1;
                    }
                    if(currentLine[j] > maxLen) {
                        maxLen = currentLine[j];
                        pos = j;
                    }
                }
            }
            for(int k=0; k<len1; ++k) {
                topLine[k] = currentLine[k];
                currentLine[k] = 0;
            }
        }
        for(int i= pos+1; i<len1; ++i) {
            if(isBaoHan(s0.charAt(i), cList)) ++pos;
        }
        return s0.substring(start, pos+1);
    }
    public static boolean isBaoHan(char ch, char[] cList) {
        for(char c : cList) {
            if(c == ch) return true;
        }
        return false;
    }

    public static int specialStr (String inputStr, char[] cList) {
        // write code here
        int start = -1;
        int end = -1;
        boolean fistIn = true;
        int len_Max = 0;
        for(int i=0; i<inputStr.length(); ++i) {
            if(!isBaoHan(inputStr.charAt(i), cList)){
                if(fistIn) {
                    start = i;
                    fistIn = false;
                } else {
                    end = i;
                }
                if(start < end) {
                    int len = maxHuiWen(inputStr.substring(start, end + 1));
                    len_Max = Math.max(len, len_Max);
                }
            } else {
                fistIn = true;
                start = -1;
                end = -1;
            }
        }
        return len_Max;
    }

    public static int maxHuiWen(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int ans = 0;
        for(int k=0; k<n; ++k) {
            for(int i=0; i+k<n; ++i) {
                int j = i+k;
                if(0 == k) {
                    dp[i][j] = true;
                } else if(1 == k) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);
                }
                if (dp[i][j] && k + 1 > ans) {
                    ans = k + 1;
                }
            }
        }
        return ans;
    }

    public static boolean isHuiWen(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length-1;
        while(left < right) {
            if(chars[left] != chars[right]) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }

    public static int sumZu(String s, int m) {
        int n = s.length();
        int sum = 0;
        if(n < m) return 0;
        for(int i=0; i<n; ++i) {
            if(n-i == m) {
                sum += 1;
                break;
            } else {
                sum += jiechen(n-i-1)/(jiechen(m-1)*jiechen(n-m-i));
            }
        }
        return sum;
    }
    public static int jiechen(int n) {
        if(0 == n || 1 == n) {
            return 1;
        } else {
            return jiechen(n-1)*n;
        }
    }

    private static int findTheLongestSubstring(String s) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int state = 0;
        for(int i=0; i<s.length(); ++i) {
            if(s.charAt(i) == '1') state ^= 0x00001;
            if(s.charAt(i) == '3') state ^= 0x00010;
            if(s.charAt(i) == '4') state ^= 0x00100;
            if(s.charAt(i) == '5') state ^= 0x01000;
            if(s.charAt(i) == '6') state ^= 0x10000;
            if(map.containsKey(state)) {
                maxLen = Math.max(maxLen, i-map.get(state));
            } else {
                map.put(state, i);
            }
        }

        return maxLen;
    }
}
