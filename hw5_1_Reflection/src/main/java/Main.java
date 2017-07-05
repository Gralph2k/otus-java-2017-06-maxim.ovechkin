import TestFramework.TestFramework;

/**
 * Created by maxim.ovechkin on 04.07.2017.
 * Написать свой тестовый фреймворк. Поддержать свои аннотации @Test, @Before, @After. Запускать вызовом
 статического метода с (1) массивом классов с тестами, (2) именем package в котором надо найти и запустить
 тесты.
 */
public class Main {
    public static void main(String[] args) {

        TestFramework.run("testingClass");
    }
}
