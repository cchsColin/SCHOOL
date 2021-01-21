package com.company;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        numberMethods test = new numberMethods();
        System.out.println(test.isPrime(30));
        System.out.println(test.divisorSum(29));
        System.out.println(test.isStrong(27));


        System.out.println(test.printDivisors(29));
        System.out.println(test.printComposites(30));
        System.out.println(test.printPrime(30));
        System.out.println(test.printStrong(30));
        System.out.println(test.printWeak(30));

    }
}

class numberMethods{

   public boolean isPrime(int n){
       return n > 1
               && IntStream.rangeClosed(2, (int) Math.sqrt(n))
               .noneMatch(x -> (n % x == 0));
   }

   public int divisorSum(int n){
       int sum = 0;
       for(int x = 1;x<n;x++){
           if(n%x==0){
               sum+= x;
           }
       }
       return sum;
   }
   public boolean isStrong(int n){
        if(!isPrime(n)){
            if(n>divisorSum(n)) {
            return true;
            }
            else{
                return false;
            }
        }
        return false;
   }

   public String printDivisors(int n){
       String sum = "";
       for(int x = 1;x<n;x++){
           if(n%x==0){
               sum+= x+" ";
           }
       }
       return sum;
   }

   public String printPrime(int n){
       String ans = "";
       for (int i = 0; i < n; i++) {
           if(isPrime(i)){
               ans += i + " ";
           }

       }return ans;
   }

   public String printComposites(int n){
       String ans = "";
       for (int i = 2; i < n; i++) {
           if(!isPrime(i)){
               ans += i + " ";
           }

       }return ans;
   }

    public String printStrong(int n) {
        String ans = "";
        for (int i = 2; i < n; i++) {
            if(isStrong(i)){
                ans += i + " ";
            }
        }
        return ans;
    }


    public String printWeak(int n){

        String ans = "";
        for (int i = 2; i < n; i++) {
            if(!isStrong(i)){
                ans += i + " ";
            }
        }
        return ans;
    }

}