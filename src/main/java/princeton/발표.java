package princeton;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

public class 발표 {

/*
제네릭이란?

 - 클래스 선언에 타입 매개변수가 쓰이면 이를 제네릭 클래스라 한다.(이펙티브 자바)

 - 다양한 타입의 객체들을 다루는 메소드나 클래스에 컴파일 시의 타입 체크를 해주는 기능

 - 클래스나 메소드에서 사용할 내부 데이터 타입을 외부에서 지정하는 기법
*/

// 예시

    static class Box<? extends Object> {

    }

//    그렇다면 이러한 제네릭이 왜 필요할까?

//    우리가 만약 어떤 자료구조를 만들어 배포하려고 한다.

//    자료구조에서 String, Integer, Long, Double, Boolean... 등등의 많은 타입을 지원해야한다.

//    만약 여기서 제네릭이 없었다면? -> 각각의 자료형에 대한 클래스를 하나하나 만들어줘야한다.

public static void main(String[] args) {

    new Box<String>();
    new Box<Integer>();
    new Box<Long>();
    new Box<Double>();
    new Box<Number>();
    new Box<Boolean>();

    Box.printT("String");
    Box.printT(587429L);
    Box.printT(true);

    ArrayList<String> strings = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    ArrayList<Boolean> booleans = new ArrayList<>();
    ArrayList<Box<Box<Box<Box<String>>>>> boxes = new ArrayList<>();

//    제네릭의 장점

//    1. 제네릭을 사용하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.

//    2. 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환해줄 필요가 없다.

//    3. 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있고, 형변환이 없어지므로 가독성이 좋아진다.

//    extends, super, ?

//    제네릭에는 경계를 제한하거나 타입 제한을 완전히 풀 수 있다.

//    상한 경계 제네릭\
    ResponseEntity<?>
}

}
