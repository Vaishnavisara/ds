/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Reference {

    public static void main(String[] args) {
        int val = 50;
        Person p1 = new Person();
        call(p1,val);
        int a =val;
        
    }

    public static void call(Person p, int i) {
        Person p2 = new Person();
        p =p2;
        p2.age =40;
        Person p3 = new Person();
        p =p3;
        p3.age = 55;
        Person p4 = new Person();
        p =p4;
        p4.age =60;
        Person p5 = new Person();
        p =p5;
        p5.age = 80;
        System.err.println(p.age);
        System.err.println(p2.age);
        System.err.println(p3.age);
        System.err.println(p4.age);
        System.err.println(p5.age);

        
    }

}

class Person {
    int age = 18;
}

