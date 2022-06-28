package hello.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    // 웹 어플리케이션에서 /hello 로 들어오면 아래 메소드를 호출합니다.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // template 의 파일 이름을 찾아서 랜더링
    }

    @GetMapping("helloMvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "helloTemplate";
    }
    // localhost:8080/helloMvc?name=spring!!!

    // ---------------- API --------------------
    // 최근에는 거의 json 방식을 채택하고 있음. html 은 구시대적..

    @GetMapping("helloString")
    @ResponseBody // http(not html) 의 body 에 해당 내용을 직접 넣어 주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    // localhost:8080/helloString?name=spring!!!
    // 페이지에서 소스코드를 열어보면, html 구조가 아닌 hello spring!!! 이 그냥 나옴 (문자 자체)

    // 문자가 아닌 data 를 요구하는 경우..
    // json 방식으로 데이터를 만들어서 http 응답에 바로 반환합니다.
    @GetMapping("helloApi")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}
    }



}
