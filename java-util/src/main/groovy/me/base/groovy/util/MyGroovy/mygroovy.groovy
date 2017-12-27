import me.base.groovy.util.MyGroovy.Person

public class HelloGroovy {
    public static void main(String[] args) {
        System.out.println("Hello Groovy");
    }
}

System.out.println("Hello Groovy")
print("Hello Groovy")
print "Hello Groovy"

//java是强类型语言,使用各种不同的类型去定义变量,Groovy还可以通过关键字def定义变量,它会根据上下文去推断这个变量的类型,需要强调一点groovy里面声明一切都是对象
def a = 10
def b = 10L
def c = 'Hello Groovy'
def d = 1.0
def e = 1.0f

println(a.class)
println(b.class)
println(c.class)
println(d.class)
println(e.class)


//Groovy数组
/*Map支持初始化的时候进行赋值,他的赋值是,[key:value.....],支持java的map的所有操作,当我们没有为map指定类型的时候,它会根据上下文去推断map的类型*/
map = [A:1, B:2, C:3] as Map<String, Integer>
map.put("D", 4)
map.remove("A")
println(map)
i = map.get("C")
println(i)


def person = new Person()
person.age = 30;
person.name = "Hello"
println(person)

def person2 = new Person(name:"xiaoming", age: 12)
println(person2)
println(person2.getName())

int i = 3
def s1 = "i's value is: ${i}"
def  s2 = "i's value is ${i}"
i++


//FileBuilder
/***
 * FileTreeBuilder is a builder for generating a file directory structure from a specification. For example, to create the following tree.
 *
 * src/
 * |-----main
 * |      |-----groovy
 * |               |----Foo.groovy
 * |-----test
 * |      |----groovy
 * |              |-----FooTest.groovy
 * |
 */
tmpDir = File.createTempFile()
def fileTreeBuilder = new FileTreeBuilder(tmpDir)
fileTreeBuilder.dir('src') {
    dir('main') {
        dir('groovy') {
            file('Foo.groovy', 'println "Hello')
        }
    }
    dir('test') {
        dir('groovy') {
            file("FooTest.groovy", 'class FooTest extends GroovyTestCase{}')
        }
    }
}

assert s1 == "i's value is: 3" // 急切地计算，一创建时就求值
assert s2 == "i's value is: 4" // 拖延式计算，考虑新值


//Lambdas
Runnable run = {println("run")}


//primitives and wrappers
//Beacuse Groovy usees Objects for everything, it autowraps references to primitives, Beacuse of this, it does not follow java's behavior of widening taking priority over boxing,
//Here's an example using int

void m(long l) {
    println("in m(long)")
}

void m(Integer i) {
    println "in m(Integer)"
}

def ant = new AntBuilder()
ant.echo('hello from Ant!')