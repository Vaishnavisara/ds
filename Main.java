public class Main{
    public static  void main (String [] args){
      System.out.println(override.add(5,5)) ;
    System.out.println(override.add(5,5.0)) ;
    }
}
class override{
    static int add(int a,int b){
        return (a+b);
    }
    static double add(int a,double b){
        return (a+b);
    }
}
