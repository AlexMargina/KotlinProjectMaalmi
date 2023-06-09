/*
Попробуем улучшить наш «Загрузчик Котиков».
Сделаем программу более разговорчивой.
Для этого добавьте функции downloadCat() новый параметр — функцию onStart(). Функция onStart() не содержит аргументов и ничего не возвращает,
а при запуске выводит строку: «Загрузчик Котиков начинает работу».
Бывают случаи, когда мы не готовы к компромиссу, а хотим всенепременно получить котика.
Добавьте новый параметр для функции downloadCat() — allowErrors типа Boolean. Измените «Загрузчик Котиков» таким образом, чтобы при значении переменной allowErrors равной false, функция getCatFromInternet() вызывалась до тех пор, пока не вернёт котика.
При значении переменной равной true, программа должна работать как раньше.
*/

import kotlin.random.Random

fun main() {
    val catDownloader = CatDownloader()

    catDownloader.downloadCat(
        onSuccess = { cat -> println("Кот ${cat.name} загружен успешно") },
        onError = { error -> println(error) },
        onStart = { println("Загрузчик Котиков начинает работу") },
        allowErrors = false
    )
}

class CatDownloader {

    // передаём в аргументы две функции onSuccess и onError
    fun downloadCat(
        onSuccess: (CatV1) -> Unit,
        onError: (String) -> Unit,
        onStart: () -> Unit,
        allowErrors: Boolean
    ) {
        onStart.run { (println("Загрузчик Котиков начинает работу")) }
        var cat: CatV1? = getCatFromInternet()
        if (allowErrors == false) {
            while (cat == null) {
                cat = getCatFromInternet()
            }
        }



        if (cat != null) {
            onSuccess.invoke(cat)
        } else {
            onError("Упс, что-то пошло не так при загрузке котеек.")
        }

    }

    private fun getCatFromInternet(): CatV1? {
        return if (Random.nextBoolean()) CatV1("Борис") else null
    }


    class CatV1 {
        val name: String

        constructor(name: String) {
            this.name = name
        }
    }
}


/*
В Kotlin все функции — функции первого класса. Они могут:
Храниться в переменных и структурах данных.
Передаваться в качестве аргументов.
Возвращаться из других функций высшего порядка.
Можно работать с функциями любым способом, который возможен для других нефункциональных значений.
Kotlin как строго типизированный язык программирования использует семейство функциональных типов для представления функций и предоставляет набор специализированных языковых конструкций, таких как лямбда-выражение.
Функция высшего порядка, на английском top-level function, — это функция, которая принимает функции как параметры или возвращает функцию в качестве результата.
Из Java мы помним, что в метод можно передать только объекты и примитивы, но как функция может принимать другую функцию.

Kotlin позволяет передавать функции как аргументы. Мы можем переписать нашу функцию по загрузке фотографий котиков таким образом:
// передаем в аргументы функцию onSuccess
fun downloadCat  (   onSuccess: () -> Unit  ) {       // загружает фотографии котиков из интернета   }

Наш аргумент состоит из трёх частей:
имя функции onSuccess. Это название нашего параметра. Оно может быть любым, но, как и с любой переменной, хорошо бы давать осмысленное название.
Как и в любой переменной в Kotlin, после двоеточия : нужно указать тип аргумента. В нашем случае это функция, а как мы знаем, функция может что-то принимать и что-то возвращать.
Аргументы функции (). В круглых скобках можно перечислить через запятую список аргументов, которые должна принимать наша функция. В нашем случае скобки пустые, это значит, что функция ничего не принимает на вход.
Возвращаемый тип Unit. После стрелочки -> нужно указать тип, который возвращает функция. В случае с обычными функциям и можно опускать Unit, если функция ничего не возвращает, но если мы хотим передать функцию в аргументы, то нужно явно указать возвращаемый тип Unit.

*/

/*class CatDownloader {
    fun downloadCat(onSuccess: (Cat) -> Unit, onError: (String) -> Unit) {

    }

    class CatDownloader {

        fun downloadCat(
            onSuccess: (Cat) -> Unit,
            onError: (String) -> Unit
        ) {
            val cat: Cat? = getCatFromInternet()

            if (cat != null) {
                onSuccess.invoke(cat) // вызываем функцию с помощью метода invoke() и передаём в нее объект cat
            } else {
                onError("Упс, что-то пошло не так при загрузке котеек.") // вызываем функцию, используя просто круглые скобки (), в которые передаём строку с сообщением
            }

        }

        // добавим немного случайности и имитируем ситуацию, когда фотография котика не может быть загружена
        private fun getCatFromInternet(): Cat? {
            return if (Random.nextBoolean()) Cat("Борис") else null
        }
    }

}
class Cat(val name: String)



//Да, мы действительно можем хранить функцию в переменной как лямбда-выражение:
fun main() {
    val catDownloader = CatDownloader()
    // переменная объявляется по тому же принципу, что и передача функции в параметры другой функции.
    // сама функция объявляется в фигурных скобах { }
    val onSuccess: (Cat) -> Unit = { cat -> println("Кот ${cat.name} загружен успешно") }
    val onError: (String) -> Unit = { error -> println(error) }

    catDownloader.downloadCat(onSuccess = onSuccess, onError = onError)
}


//А раз мы можем функцию хранить в переменной, значит, можем и возвращать её из другой функции:
fun main2() {
    val catDownloader = CatDownloader()
    val onSuccess: (Cat) -> Unit = doOnSuccess()
    val onError: (String) -> Unit = doOnError()
    catDownloader.downloadCat(onSuccess = onSuccess, onError = onError)
}
// явно указываем тип возвращаемой функции "(Cat) -> Unit"
private fun doOnSuccess(): (Cat) -> Unit = { cat -> println("Кот ${cat.name} загружен успешно") }

// Kotlin сам определит тип возвращаемой функции, но в этом случае внутри лямбда выражения необходимо явно указать тип аргументов "error: String"
private fun doOnError() = { error: String -> println(error) }
>>>>>>> origin/master*/
