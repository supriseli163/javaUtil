/**
 * groovy 一切皆对象,事实上groovy对于对象是什么类型并不关心,一个变量的类型在运行中可以随时改变
 * 一切根据需要而定,如果你赋给它boolean,那么不管它原来是什么类型,它接受boolean值之后就会自动把类型转变boolean.
 */
def var = 'hello' + 'world' + ',groovy'
println(var)
println(var.class)
var = 1001
println var.class

/**
 *GString
 */
def str1 = "编程语言"
def str2 = "Groovy"
println "$str1:$str2"
println "$str1:$str2"

/**
 * GString applied to SQL Statements
 * GString are nice because it can be used as a simple templating engine, For example, we can apply it to compose queries.
 */
def people = [[name : 'John Doe', age:10], [name: 'Jane Smith', age: 12], [name: 'Robert Johnson', age: 15]]
people.each {person ->
    println "inset into person_table(name, age) values ('${person.name}', ${person.age})"
}


/**
 * Multiline Groovy GString
 * Groovy GString can also by applied to multiline Strings. To declare multiline, it should start with triple double
 * quoutes and should end with the same, Below is an example:
 */
def name = "John Doe"
def age = 10
def theString = """
Hi ${name}
Your age is ${age}
Bye!
"""
println theString

/**
 * Map集合
 * Map是"键-值"对的集合,在groovy中,键不一定是string,可以是任何对象(实际上Groovy中的Map就是java.util.LinkedHashMap)
 *
 */
// 1.定义一个map
def map = ['name':'john', 'age':14, 'sex':'body']
println map
// 2.添加项:
map = map + ['weight':25]   //添加john的体重
map.put('length', 1.27)     //添加john的身高
map.father= 'Keller'
println map
// 3.两种方式检索值
println map['father']
println map.length

/**
 * 闭包(Closure)
 * 闭包是用符号括起来的代码块,他可以被单独运行或调用,也可以被命名.类似'匿名类'或内联函数的概念.
 * 闭包中最常见的应用是对集合进行迭代
 *
 * 闭包类似于方法,需要定义参数和需要执行的语句,它也可以通过名称调用,然而闭包对象可以作为参数传递,而在java中.
 * 要做到这一点并不容易,其次,闭包也可以不命名,而方法不可以
 */
def map2 = ['name':'john', 'age':14, 'sex':'boy']
map2.forEach(
        {key, value ->
            println "$key:$value"   //key,value两个参数用于接受每个元素的键/值
        })
map.each {println it}
map2.each({ println it.getKey()+"-->"+it.getValue() })

/**
 * http://blog.csdn.net/happy_horse/article/details/52674146
 * 类: Groovy和Java类一样,完全可以用标准java bean的语法定义
 */


class Person {
    def name
    def age
    String toString() { //注意方法的类型string,因为我们要覆盖的方法为string类型
        "$name,$age"
    }
}

// 我们可以使用默认构造方法实例化Person类:
def person1 = new me.base.groovy.util.MyGroovy.Person()
person1.name = 'kk'
person1.age = 20
println person1
// 也可以用groovy的风格做同样的事情
def person2 = new me.base.groovy.util.MyGroovy.Person([name: 'gg', age: 22])    //[]号可以省略
println person2

/**
 * 枚举
 *
 */
enum Day {
    SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
}

/**
 * 动态性
 * grovy 所有的对象都有一个元类metaClass,可以通过metaclass属性访问该元类
 * 通过元类,可以为这个对象增加方法
 */
def msg = "hello"
println msg.getMetaClass()
String.metaClass.up = { delegate.toUpperCase() }
println msg.up()

// 通过元类,我们还可以检索对象所拥有的方法和属性
msg.metaClass.metaMethods.each {println '####' + it.name}
msg.metaClass.properties.each {println it.name}