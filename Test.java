import java.util.function.Function;

/**
* 描述：
* 创建日期：2022年09月29 19:20:
* @author gong zhao 
**/
public class Test {
    public void test(){
        Function<Integer, String> triangle = arg -> arg*3 + "";
        System.out.println(triangle.apply(3));
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}
