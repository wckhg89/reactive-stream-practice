# 리엑티브 스트림

## 서론

리액티브 프로그래밍에 대해 얼마전 새롭게 공부를 시작했습니다. 대학생 시절 객체지향이라는 패러다임을 받아들이기 까지 꾀나 긴 시간이 걸렸습니다.
리액티브 프로그래밍 또한 새로운(?) 패러다임이라고 생각합니다. 객체지향 프로그래밍을 공부하면서 경험한 바로는 객체지향이라는 패러다임이 원하는(의도하는)
프로그래밍 스타일을 이해한 후에 (~~아직도 객체지향 패러다임을 완벽히 이해하지는 못했지만~~) 문법을 학습하니 더 빠르게 와닿았던 기억이 납니다.
개인적으로 생각하기에 리액티브 프로그래밍에 대해 기본적인 문법(사용법?)을 알기전에 이 패러다임에 대해서 이해하고 그 이후에 문법(사용법)을 공부해야 더 빠르게 와닿을 것 같다고 생각을 해서 정리를 해보고자 합니다.

이번 글에서 다루고자 하는 항목은 아래와 같습니다.

- 리액티브 프로그래밍은 무엇인가

- 옵저버 패턴에 대해서

- 리액티브 스트림 인터페이스에 대해서

- 리액티브 스트림의 구현체들

순으로 알아보려 합니다. 아직까지 함수형 프로그래밍에 대한 개념이 부족하여 이 부분은 생략하고(후에 공부하면 추가하도록 하고) 이번에는 리액티브 프로그래밍에 초점을 두어 정리를 해보겠습니다.

## 본론

### 리액티브 프로그래밍은 무엇인가

리액티브 프로그래밍이 무엇인지를 검색해보니 아래와 같은 정의를 찾아볼 수 있었습니다. (![참고_헝_링크](https://gist.github.com/casamia918/93b8db69beb9ee06b92a96b2a234d48e))

```
Reactive programming 은 비동기 데이터 스트림으로 프로그래밍을 하는 겁니다.
Reactive programming is programming with asynchronous data streams.
```

정의에서 보는것 처럼 리액티브 프로그래밍에 대해서 검색해보면 항상 함께 등장하는 키워드들이 있습니다. ``함수형 프로그래밍``, ``비동기``, ``논블록킹 IO`` 등 여러가지 키워드들이 함께 섞여서 나와 리액티브 프로그래밍이 상당히 어렵게 다가오고 쉽게 이해되지 않는것 같다는 생각을 했습니다.

따라서 이번글에서는 이러한 키워드들이 왜 리액티브 프로그래밍이라는 개념에 함께 등장하는지 그리고 조금더 리액티브 프로그래밍에 집중을 해보고자 합니다.

앞서 정의한 ``비동기 데이터 스트림으로 프로그래밍을 한다.``를 조금 더 생각해보면, ``실시간으로 반응을 하는 프로그래밍`` 이라고 생각할 수 있습니다.
다음 영화 검색창에 단어를 하나씩 입력할 때마다 관련 검색어들이 자동완성으로 바로바로 제시되는 것을 예로 들 수 있을것 같습니다.(실제로 RxJS를 통해서 구현하셨다고 알고있습니다. **갓존**)

먼저 함수형 프로그래밍이라는 키워드가 왜 리액티브 프로그래밍의 개념에 같이 나타나는지 생각해보면 ``실시간으로 반응을 하는 프로그래밍``을 하기 위해서는 명령헝 프로그래밍(하나의 메소드에서 여러 분기를 주어 로직을 처리하는) 보다는 함수형(map, filter, reduce 등) 프로그래밍으로 작성을 하는 것이 많은 부분에서 효율적일 것입니다. 그래서 리액티브 프로그래밍을 설명할때 함수형 프로그래밍 이라는 키워드가 함께 따라다니는 것이라고 생각합니다.

또한 실시간으로 반응하는 프로그래밍을 위해서는 어떠한 사용자 인풋이 들어오면 이를 처리하는데 여러 스레드들이 비동기적으로 처리를 해주어야만 실시간으로 반응을 할 수 있을 것입니다.
이러한 이유로 ``비동기``, ``논블록킹 IO``라는 키워드들이 함께 등장하는 것이라고 생각합니다.

사용자가 입력을 줄때마다 즉각적으로 반응을 하려면, 프로그램이 지속적으로 값을 관찰(Observe)해야하고, 값에 변화가 일어날 때마다 특정 연산이 수행되어야 합니다.
이러한 패턴이 ``옵저버 패턴``입니다.(![참고링크](https://m.blog.naver.com/PostView.nhn?blogId=jdub7138&logNo=220983291803&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)])


#### 옵저버 패턴

그럼 옵저버 패턴에 대해 간단히 예제 코드를 보면서 설명을 해보겠습니다. (개발자는 코드를 보면서 이야기를 해야합니다. - **갓워런**)
예제는 자바 Util 클래스의 ``Observable``, ``Observer``를 이용해서 작성했습니다.

코드를 보기전에 앞으로 ``구독(Subscribe)``과 ``발행(Publish)``이라는 단어를 많이 사용할 것입니다.
앞서 옵저버 패턴을 설명드리기 전에 말씀드렸던 것 처럼 옵저버 패턴이 이러한 단어와 매우 밀접하기 때문입니다.

먼저 Observable 클래스는 Observer 클래스들을 관리하는 클래스입니다.
Observable 클래스를 통해서 Observer 클래스들을 구독시킬(?) 수 있고 구독중인 Observer들에게 소식을(이벤트를) 발행할 수 있습니다.

그리고 Observer 클래스는 구독중인 객체에서 발행을 해주는 이벤트(소식)을 받아서 이에 따른 비지니스 로직을 수 행 할 수 있습니다.

그럼 코드를 한번 보겠습니다.

``` java

// 소식을 발행하는 발행자에 해당합니다.
@NoArgsConstructor
public class GuppyPublisher extends Observable {

    private static final Logger logger = LoggerFactory.getLogger(GuppyPublisher.class);

    private String name = "Honggu Kang";
    private String company = "ZUM internet";
    private String status = "tired";
    private int age = 29;
    private List<String> skills = Lists.newArrayList("java", "spring", "javascript");


    public void changeJob () {
        this.company = "Kakao corp";
        this.status = "happy";

        this.infoChanged("guppy have a new job.");
    }

    public void newYear () {
        this.age += 1;
        this.status = "sad";

        this.infoChanged("guppy ate 떡국");
    }

    public void addSkill () {
        skills.add("webpack");
        infoChanged("guppy learn new skill");

        skills.add("reactJS");
        infoChanged("guppy learn new skill");

        skills.add("reactive-stream");
        infoChanged("[END] guppy learn new skill");
    }


    // 구독자들에게 소식(이벤트)를 발행합니다.
    public void infoChanged (String msg) {
        super.setChanged();
        // 구독자들에게 소식(이벤트)를 발행합니다.
        super.notifyObservers(msg);
    }

    // 구독자(Observer 객체)들을 추가합니다.
    public void addSubscriber (Observer subscriber) {
        super.addObserver(subscriber);
    }

    public void aboutGuppy () {
        logger.info("name : {}", this.name);
        logger.info("company : {}", this.company);
        logger.info("status : {}", this.status);
        logger.info("age : {}", this.age);
    }

}


```


``` java

// 소식을 구독하는 구독자에 해당합니다.
public class JohnSubscriber implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(JohnSubscriber.class);

    // 소식(이벤트)이 발행되면 이에 따른 비지니스 로직을 수행합니다.
    @Override
    public void update(Observable o, Object info) {
        logger.info("oh..? - {}", info);

        if (info instanceof String) {
            boolean isEnd = this.parsingInfoForEndSignal((String) info);

            if (isEnd) {
                logger.info("Wow!");
            }
        }


        if (o instanceof GuppyPublisher) {
            ((GuppyPublisher) o).aboutGuppy();
        }

        logger.info("=========== 9분선 =============");
    }

    private boolean parsingInfoForEndSignal (String info) {
        String regex = "^(\\[([a-zA-Z|\\s]*)(\\/[a-zA-Z]*)?\\])?(.*)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(info);

        if (matcher.find()) {
            String endSignal = matcher.group(2); // 두번째 괄호

            if ("END".equals(endSignal)) {
                return true;
            }
        }

        return false;
    }
}

```

코드를 본것 처럼 실시간으로 반응하는 프로그래밍(리액티브 프로그래밍)을 위해서는 옵저버 패턴이 상당히 유용한 패턴이라는 것을 알 수 있습니다.
그러나 자바 Util 클래스에는 몇가지 한계점이 있습니다.

구독자(Observe 객체)는 발행자(Observable 객체)의 상태를 알 수 없습니다.

- 예제 코드에서 보는 것 처럼 발행자가 던저주는 메시지를 해석해서 발행자의 상태값을 추축해야 합니다.

- 또한 발행자에게서 어떠한 오류가 낫을때 이를 알수 없기때문에 이에 적합한 예외를 처리하기 어렵습니다.

이러한 한계점을 개선하고자 그리고 리액티브 프로그래밍을 하기 위해 표준을 만들고자 나온 것이 Reactive-stream 이라는 인터페이스 입니다.


#### 리액티브 스트림

리액티브 스트림은 리액티브 프로그래밍에 대한 표준을 정의한 인터페이스입니다. (![참고링크](http://www.reactive-streams.org/))
리액티브 스트림의 스팩에는 4가지 인터페이스 스팩이 나옵니다.

- Subscriber

- Publisher

- Subscription

- Processor

예제 코드를 통해서 리액티브 스트림의 스팩에 대해 조금 살펴보겠습니다.


``` java

public class StackSubscriber implements Subscriber {

    private static final Logger logger = LoggerFactory.getLogger(StackSubscriber.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;

        subscription.request(1);
    }

    @Override
    public void onNext(Object o) {
        logger.info("onNext - {}", o);

        subscription.request(10);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("onError - {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        logger.info("COMPLETE");
    }

}

```

먼저 Subscriber 인터페이스는 앞서 살펴본 자바 Util 클래스의 Observer 클래스와 유사한 역할을 담당합니다.
상속받은 메소드들을 보면 ``onNext()``메소드가 Observer 클래스의 ``update()`` 메소드와 유사한 역할을 함을 알 수 있습니다.
추가로 ``onError()``, ``onComplete()`` 메소드들을 통해서 자바 Util 클래스에서 처리하기 힘들었던 발행자의 상태에 대해서도 쉽게 파악이 가능합니다.


```java

public class StackPublisher implements Publisher {

    private static final Logger logger = LoggerFactory.getLogger(StackPublisher.class);

    private Stack<Integer> stack;

    public StackPublisher() {
        this.stack = new Stack<>();

    }

    @Override
    public void subscribe(Subscriber subscriber) {
        initStack();

        subscriber.onSubscribe(new Subscription() {
            public void request(long l) {

                logger.info("Request : {}", l);

                if(l < 0) {
                    notifyException();
                    return;
                }

                for(int i = 1 ; i <= l ; i++) {
                    if(stack.empty()) {
                        notifyComplete();

                        return;
                    }

                    notifyNextEvent();
                }
            }

            private void notifyNextEvent() {
                subscriber.onNext(stack.pop());
            }

            private void notifyComplete() {
                subscriber.onComplete();
            }

            private void notifyException() {
                subscriber.onError(new Exception("  0 이상의 숫자를 넣어야 합니다"));
            }

            public void cancel() {

            }
        });
    }

    private void initStack() {
        for(int i = 0; i < 10 ; i++){
            stack.push(i);
        }
    }
}

```

그리고 Publisher 인터페이스는 자바 Util의 Observable 클래스와 유사한 역할을 담당합니다.
``subscribe()`` 메소드를 통해서 Subscriber들을 등록하고 해당 객체들을 관리합니다.

마지막으로 Subscription 인터페이스는 구독자(Subscriber) 클래스와 발행자(Publisher) 사이를 연결해주는 인터페이스입니다.

#### 리액티브 스트림 구현체들

코드를 통해서 옵저버 패턴과 리액티브 스트림 인터페이스에 대해서 아주아주 간략히 알아보았습니다.
자바 진영에서는 이런 리액티스 스트림 인터페이스 표준을 지켜서 구현한 구현체들이 많이 있습니다. 어떤 구현체들이 있는지 간략히 살펴 보겠습니다.






## 결론






## 리액티브 프로그래밍


### 서론

- 리액티브 프로그래밍은 종종 동시성 프로그래밍이나 고성능 처리 등과 떼어놓기 어려울 정도로 같이 언급되긴 하지만, 사실 이들은 **원론적으로 완전히 다르다.**

- 또 리액티브 프로그래밍은 함수형 반응형 프로그래밍 (Functional Reactive Programming, FRP)과 같이 언급되기도 하고 같은 것으로 여겨지기도 한다.

### 무엇인가

- 리액티브 프로그래밍은 지능정 라우팅과 이벤트 소비에 대한 그리고 이들 모두를 합쳐 행동을 변화시킨다는 마이크로 아키텍쳐 스타일이다.

- 리액티브 프로그래밍이 주는 반항은 마이크로서비스의 부흥 그리고 멀티코어 프로세서의 대중화와 (필연적으로) 동시에 이루어졌다.

```

리액티브 프로그래밍을 뒷반침하는 기본적인 개념은 어떤 값에 지속적으로 대응할 수 있는 어떤 데이터 타입들이 있다는 것이다.
이런 지속적으로 변화하는 값들과 연관된 연산 작업들은 연산 자체가 지속적으로 변화하는 값을 갖고 있게 된다.

```

- 함수형 반응형 프로그래밍 (FRP)은 고성능, 동시성 비동기 처리 그리고 넌블락킹 IO 등의 개념과 매우 친밀하다.

- **하지만, FRP가 그런 것들과 아무런 관계 없는 것이 아닌가라는 의심을 갖고 시작하는 것이 도움이 될 수 있다.**

- 분명히 리액티브 모델을 사용하면 그러한 고려사항들이 (종종 호출자의 입장에서는 당연스러울 정도로 자연스럽게 처리되기도 한다.

- 하지만 리액티브 프로그래밍의 진정한 혜택은 이러한 고려사항들을 효과적이고 효율적으로 처리할 수 있다는 점에서 전적으로 구현에 관련된 문제라고 생각할 수 있다.


### 리엑티브 사용 사례

#### 외부 서비스 호출 (External Service Call)

- 요즘의 많은 백앤드 서비스들은 REST 방식(즉, HTTP 기반으로 동작한다.)이며 그래서 기반 프로토콜은 기본적으로 **응답을 대기(Blocking)해야 하고 동기적(Synchronous)으로 수행된다.**

- 이런 서비스들은 또 다른 서비스들을 호출하도록 구현되며, 그래서 수많은 서비스들이 첫 번째 호출의 결과와 관련있기 때문이다.

- 수많은 입출력(IO)이 수행되는 과정에서, 만약 어떤 요청을 전송하기 전에 다른 호출이 완료되기를 기다려야 한다면, 서비스 사용자는 응답 데이터가 만들어지기도 전에 좌절하여 포기할 수도 있다.

- 그렇기 때문에 외부 서비스를 호출하는 경우, 특히 그 서비스호출들이 복잡하게 연결되어 있는 경우야 말로 최적화 하기 좋은 지점이 된다.

- 함수형 리액티브 프로그래밍 (FRP)은 이러한 동작들을 수행하는 로직을 조합할 수 있도록 보장하며 그래서 개발자들은 서비스 호출하는 코드를 쉽게 작성할 수 있다.

### 높은 동시성 메시지 소비자 (Highly Concurrent Message Consumers)

- 메시지 프로세싱은, 특히 고도의 동시 처리를 수행한다는 점에서 엔터프라이즈 어플리케이션에서 흔히 사용된다.

- 리액티브 프레임워크들은 마이크로 벤치마크를 측정하여 JVM 내에서 1초당 수없이 많은 메시지를 처리할 수 있는지 보여주기를 좋아한다

### Java 에서의 리액티브 프로그래밍

#### Reactive Stream

- Reactive Stream은 매우 낮은 수준의 계약으로, 몇 안되는 Java 인터페이스(와 Technology Compatibility Kit)로 표현되지만, 다른 언어에도 적용될 수 있다.

- 이 인터페이스들은 명시적인 배압(back pressure)과 함께 배포자(Publisher)와 구독자(Subscriber)를 위한 기본적인 빌딩 블럭을 표현하는데,

- 상호 정보교환을 하는 라이브러리들을 위한 일반적인 언어를 만든다. Reactive Stream은 JDK 버전 9의 java.util.concurrent.Flow에 통합되었다.


#### RxJava

- 넷플릭스는 꽤 오랜기간동안 리액티브 패턴을 사용해왔으며, 이후 Netflix/RxJava라는 도구를 오픈소스 라이선스 하에 배포하였다.(나중에 이것은 "Reactive/RxJava로 재명명되었다.)

- 넷플릭스는 RxJava 위에 Groovy 기반으로 수많은 프로그래밍을 수행했었는데, 이것은 Java에도 사용될 수 있도록 열려 있으며 람다(Lambda)를 사용하면 Java 8과도 매우 잘 들어맞게 되어 있다.

- Reactive Stream에 대한 연결체도 있다. RxJava는 David Karnok의 Reactive 세대 분류 체계에 따르면 2세대 라이브러리에 속한다.

#### Reactor

- Reactor는 Pivotal의 오픈소스팀(이 팀에서 스프링을 만들었다)이 만든 Java 프레임워크이다.

- 이 프레임워크는 Reactive Stream 상에서 직접 빌드하는데, 그래서 브릿지가 필요치 않다.

- Reactor IO 프로젝트는 Netty나 Aeron과 같은 저수준 네트워크 런타임에 대한 래퍼를 제공한다.

- Reactor는 David Karnok의 Reactive 세대 분류 체계에 따르면 4세대 라이브러리에 속한다.


#### Spring Framework 5.0




### 키워드 (내가이해한거 위주로)


- 함수형 프로그래밍
    - filter, map ,reduce

- 리액티브 프로그래밍

    - 비동기, 옵저버, 콜백

- FRP

    - RX (비동기 함수형




## 참고

http://wiki.sys4u.co.kr/pages/viewpage.action?pageId=7766896


https://m.blog.naver.com/PostView.nhn?blogId=jdub7138&logNo=220983291803&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F

https://gist.github.com/casamia918/93b8db69beb9ee06b92a96b2a234d48e

https://medium.com/@LIP/rxjava-29cfb3ceb4ca