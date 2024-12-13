> Lombok 是基于 AST（Abstract Syntax Tree，抽象语法树）和注解处理器（Annotation Processor）实现的。它通过操作编译过程中的 AST 来动态修改 Java 源代码，使得开发者无需手动编写大量样板代码（如 getter、setter、toString 等），从而提升开发效率。

## 1. Lombok 的核心实现机制
#### (1) 注解处理器（Annotation Processor）
Lombok 使用了 JSR 269 标准中定义的注解处理器。注解处理器是一种在 Java 编译阶段运行的工具，可以扫描代码中标注的注解，并基于这些注解生成或修改代码。

* 触发点：当 Java 编译器检测到 Lombok 提供的注解（如 @Getter、@Setter）时，会调用 Lombok 的注解处理器。
* 入口：javax.annotation.processing.AbstractProcessor 是 Lombok 注解处理器的核心接口。
* 作用：在代码编译时解析注解，并生成需要的样板代码。
#### (2) 抽象语法树（AST）
* Lombok 不仅使用注解处理器生成代码，还直接操作编译器的抽象语法树（AST）。
* AST 是源代码的抽象表示，Lombok 修改 AST 的节点来插入额外的字段、方法或修改类的结构。
* Lombok 通过操作 Eclipse Compiler for Java（ECJ） 和 Javac（Java 标准编译器）中的 AST 来实现其功能。

## 2. Lombok 的实现步骤
#### 扫描注解
* Lombok 的注解处理器扫描代码中使用的 Lombok 注解（如 @Getter、@Setter）。
* 获取需要处理的类和成员信息。

#### 修改 AST

* Lombok 使用不同编译器（如 Javac 或 Eclipse）的 AST API，找到对应的类、字段、方法节点。
* 根据注解的逻辑，动态地向 AST 添加 getter、setter 等方法。
#### 生成字节码

* 修改后的 AST 交由编译器继续处理，最终生成包含新增方法的字节码文件。

## 3. Lombok 基于 AST 的具体实现
#### (1) @Getter 和 @Setter
##### 注解逻辑：
   * 检查注解是否存在于类或字段上。
   * 如果是字段上的 @Getter，在 AST 中为该字段插入对应的 getXXX 方法。
   * 如果是 @Setter，插入 setXXX 方法。
   代码示例：
```

@Getter
@Setter
public class User {
private String name;
}
```
生成的 AST 修改（伪代码表示）：

```
class User {
private String name;

    // Lombok 插入的 getter 方法
    public String getName() {
        return this.name;
    }

    // Lombok 插入的 setter 方法
    public void setName(String name) {
        this.name = name;
    }
}
```
#### (2) @ToString
##### 注解逻辑：
* 检查注解是否存在于类上。
* 在 AST 中为该类插入 toString 方法。
* 根据注解的参数决定是否包含某些字段。
代码示例：

```
@ToString
public class User {
private String name;
private int age;
}
```
生成的 AST 修改：
```
class User {
private String name;
private int age;

    @Override
    public String toString() {
        return "User(name=" + name + ", age=" + age + ")";
    }
}
```
#### (3) @Builder
注解逻辑：
为类生成静态的构建器方法（builder()）。
插入一个静态内部类，内部类包含所有字段，并提供链式方法。
代码示例：

```
@Builder
public class User {
private String name;
private int age;
}
```
生成的 AST 修改：
```
class User {
private String name;
private int age;

    // Lombok 插入的 builder 方法
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String name;
        private int age;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(name, age);
        }
    }
}
```

## 4. Lombok 的编译器适配
   Lombok 针对不同的编译器实现了不同的 AST 操作逻辑：

### (1) Javac
Lombok 使用了 com.sun.tools.javac.tree 包下的 Tree 和 TreeMaker API 来操作 Javac 的 AST。
通过获取编译器上下文（Context），修改 AST 的节点结构。
### (2) Eclipse
对于 Eclipse Compiler（ECJ），Lombok 使用了 Eclipse 提供的 AST API 来操作其抽象语法树。
## 5. Lombok 的优缺点
###   优点
* 减少样板代码：
  *   大幅减少 getter、setter、toString 等冗余代码。
* 提升开发效率：
  * 开发者可以专注于业务逻辑，而非重复性代码。
* 编译时生成：
  * Lombok 修改 AST 是在编译时完成的，不会对运行时性能造成额外开销。
###   缺点
* 编译器依赖：
  * Lombok 深度依赖于编译器的 AST API，可能在编译器版本变化时需要额外适配。
* IDE 支持问题：
  * Lombok 需要专门为 IDE（如 IntelliJ IDEA、Eclipse）编写插件来支持其功能显示。
* 隐式代码：
  * Lombok 生成的代码是隐式的，不直接可见，可能增加调试难度。
## 6. 总结
#### 实现机制：
* Lombok 基于 注解处理器（Annotation Processor） 和 AST 操作 实现动态代码生成。
#### 核心功能：
* 通过注解扫描和 AST 修改，自动生成样板代码，如 getter、setter、toString 等。
#### 编译器适配：
* 针对 Javac 和 Eclipse Compiler 实现了不同的 AST 操作逻辑。
#### 优雅解决样板代码问题：
* Lombok 大幅减少了样板代码的数量，但同时也增加了一些隐式代码的复杂性。
   
如果需要实现类似 Lombok 的功能，可以学习使用 JSR 269 注解处理器和编译器 AST API 进行开发！如果有具体需求，可以进一步探讨。