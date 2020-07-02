package com.example.test;

public class Demo2 {
    private static final String code ="S";

    public static void main(String[] args) {
      String s1 = "a"+"b";
      String s2 = "ab";
      String s3 = new String("ab");
      String  a = "a";
      String  b = "b";
      String s4 = a+b;

      System.out.println(s1==s2);  //true
      System.out.println(s1.equals(s2));  //true
      System.out.println(s1==s3); //false
      System.out.println(s1.equals(s3)); //true
      System.out.println(s1==s4); //false
      System.out.println(s1.equals(s4)); //true
      System.out.println(s2==s3); //false
      System.out.println(s2.equals(s3)); //true
      System.out.println(s2==s4); //false
      System.out.println(s2.equals(s4));//true
      System.out.println(s3==s4);//fasle
      System.out.println(s3.equals(s4));//true
    }
}
