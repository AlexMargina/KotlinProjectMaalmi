/*
В данном пособии приводятся несколько задач обучающих языку КОТЛИН.
Задачи и теория взяты с курса  Яндекс.Практикум (2023 год)

Оглавление:
1. Циклы (Cycle.kt)
2. Массивы (Arrays.kt)
3. Классы (ClassKotlin.kt)
4. Private constructor и companion object
5. ENUM CLASS (EnumWeekDay.kt)
6. Функциональные интерфейсы и лямбды (DiceRollerV2)


 Дополнительно:
kotlin/libraries/stdlib/ -> https://github.com/JetBrains/kotlin/tree/30788566012c571aa1d3590912468d1ebe59983d/libraries/stdlib/common/src/kotlin
*/



/*
Итоги спринта №5

История Kotlin
•	Помимо ООП, в программировании выделяют ещё четыре парадигмы: императивное программирование, функциональное программирование, логическое программирование и программирование с ограничениями.
•	Параллельное программирование — это особый подход к созданию программ, который позволяет одновременно выполнять их код на нескольких вычислительных узлах.
•	Kotlin создавался как язык для JVM.
•	Первая версия языка вышла в 2016 году после шести лет разработки.
•	Kotlin полностью бесплатный и имеет открытый код.
•	В Kotlin количество кода, необходимого для написания Android-приложений, на 40% меньше, чем в Java.
Переменные
•	Все типы данных и типы переменных в Kotlin — объекты-наследники класса Any.
•	Объекты в синтаксисе Kotlin мы пишем с большой буквы.
•	Тип переменной в Kotlin указывается с помощью : после её названия.
•	В Kotlin два типа переменных: изменяемые (мутабельные) var и неизменяемые (иммутабельные) val.
        •	При приведении типов на уменьшение, например, Double к Int, теряются знаки после запятой. Вся дробная часть числа будет отброшена без округления целой части.
•	В Kotlin есть заранее определённые операции для работы с Boolean: or, and, not.
•	Символы в Kotlin представлены типом Char. Символьные литералы заключаются в одинарные кавычки: '1'.
•	В Kotlin доступны операции инкремента и декремента: префиксные: ++a и постфиксные: a++.
Строковые шаблоны
•	В названии переменной мы не можем использовать точки. Как только в переменной встречается точка, пробел или любой другой знак, который не может там быть, Kotlin считает, что название переменной кончилось.
•	В Kotlin придумали строковые шаблоны — участки кода, которые выполняются, а полученный результат встраивается в строку.
•	Шаблон начинается со знака доллара $ и состоит либо из простого имени, либо из произвольного выражения в фигурных скобках.
Операторы if и when
•	Для выполнения операций сравнения в Kotlin мы используем операторы из Java: =, !=, >, <, >=, <=.
•	В Kotlin можно проверять тип объекта. Делается это с помощью оператора is.
•	if возвращает значение, что позволило отказаться от тернарного оператора.
•	Разумеется, внутри каждой ветви может быть более одной строки кода. В таком случае мы обрамляем выражение фигурными скобками.
•	Если мы не используем условный оператор if в качестве выражения, то else можно опустить.
•	when сравнивает аргумент, заключённый в скобки со всеми указанными значениями, пока не выполнится какое-либо из условий ветвей, после чего выполнится код, расположенный справа от стрелки ->.
•	Используя конструкцию when, мы можем применять оператор in, который позволяет проверить вхождение в диапазон.
Циклы
•	Назначение циклов в Kotlin ничем не отличается от Java, но синтаксис у них разный. В Java есть два вида циклов: for и while. Kotlin в этом не отличается от Java.
•	Циклы while в Kotlin не имеют никакого отличия от конструкций в Java.
•	Тело циклов while и do-while выполняется до тех пор, пока их условие выполняется. Разница между ними заключается во времени проверки условия.
•	Kotlin поддерживает привычные операторы break и continue в циклах.
Энди

Классы
•	Классы в Kotlin создаются почти как в Java. Просто пишем class и далее название.
•	В отличие от Java, можно не писать фигурные скобки. Зачем, если класс пустой.
•	Как и в Java, у классов есть конструкторы. Как и в Java, пустые по умолчанию.
•	В Kotlin решили, что писать название класса для объявления конструктора ещё раз — лишняя работа.
•	В Kotlin слово constructor опционально, и его можно не писать.
•	Слово constructor можно использовать уже внутри класса, когда нам нужно несколько конструкторов. Такие конструкторы называются вторичными (на английском secondary).
•	В Kotlin основной конструктор, который указывается при объявлении класса, не может содержать внутри себя код.
•	Единственный способ выполнить код при создании класса с первичным конструктором — добавить блок кода init.
•	В Kotlin код пишется только во вторичные конструкторы.
•	Свойства класса в Kotlin автоматически генерируют get и set методы для каждой переменной.
•	В Kotlin методы можно размещать там же, где и в Java — в теле класса.
•	В Kotlin можно даже не писать фигурные скобки у методов, а сразу указывать через знак =, какое значение будет возвращать этот метод.
•	Дефолтные параметры — значения по умолчанию.
•	Именованные параметры — заранее указанные данные в нужном порядке.
•	В Kotlin все типы бывают либо nullable, либо non-nullable — типы, которые могут быть пустыми, и те, в которых всегда содержится значение.
•	Типы в Kotlin по умолчанию non-nullable.
•	В рабочей практике классы можно разделить на три типа: классы, описывающие какую-то логику, классы с данными, на английском Data Classes, и утилитарные классы.
•	Классы с логикой — «рабочая лошадка». На учёбе, а затем на работе вы будете использовать их везде и постоянно.
•	Классы с данными хранят в себе только состояние какого-то объекта, их методы обычно не несут логики, а лишь помогают манипулировать состоянием такого класса.
•	Функция copy() используется для копирования объекта, что позволяет изменить только некоторые его свойства, оставив остальные неизменными.
•	Утилитарные классы обычно не имеют в себе никакого состояния. Более того, часто они даже не решают какую-то единственную проблему, а скорее содержат несколько простых решений для часто решаемых проблем.
•	Singleton — это паттерн проектирования, который гарантирует, что у класса есть только один экземпляр, и предоставляет к нему глобальную точку доступа.
•	Синглтоны нарушают принцип единой ответственности. В разработке принцип единой ответственности, на английском Single Responsibility Principle, указывает классу на возможность иметь только одну обязанность.
•	Объект всегда существует только в одном экземпляре.
•	Kotlin позволяет создавать анонимные объекты.
•	Перечисление, или enum — это набор именованных констант, который представляет собой ограниченный список идентификаторов.
•	Анонимные классы в Kotlin решают те же задачи, что и в Java, но имеют другой синтаксис.
•	При создании анонимного класса мы можем создать для него параметры внутри класса.
•	В Kotlin анонимные классы могут реализовывать больше одного интерфейса.
•	В Kotlin тоже есть функциональные интерфейсы. Для объявления функционального интерфейса в Kotlin есть специальное ключевое слово fun как функция.
 */

// Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//println("Program arguments: ${args.joinToString()}")

fun main (args: Array<String>) {
    //println("ЦИКЛЫ")
    //Cycle.cycle (5)
    //calculate(100)
    //makeClassKotlinAnimal("cat1", "Steewe", 5)
    //makeAnimal()
    //println ("Запускаем для проверки putMoney(Money.coins_10)")
    println()
    //PiggyBank.putMoney(Money.coins_10)
    //PiggyBank.putMoney(Money.coins_50)
    //PiggyBank.putMoney(Money.coins_100)
    //PiggyBank.putMoney(Money.bill_100)
    //PiggyBank.putMoney(Money.bill_500)
    //PiggyBank.putMoney((Money.bill_1000))

    //println(removeFromArrayKotlin(6))
    minusFromArrayKotlin(6)
    println(findFromArrayKotlin        (5))
    indexFromArrayKotlin(9)


    // Try adding program arguments via Run/Debug configuration.

}
