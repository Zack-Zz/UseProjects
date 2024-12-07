package com.github.zack.use.ast.processor;

import com.github.zack.use.ast.AutoGetter;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.Set;

/**
 * @author zack
 * @since 2024/12/7
 */
@SupportedAnnotationTypes({"*"}) // 声明支持的注解
@SupportedSourceVersion(SourceVersion.RELEASE_8)    // 声明支持的 Java 版本
public class AutoGetterProcessor extends AbstractProcessor {

    @Override
    public void init(ProcessingEnvironment processingEnv) {
        System.out.println("AutoGetterProcessor init: " + isInitialized());
        if (isInitialized()) {
            return;
        }
        super.init(processingEnv);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "AutoGetterProcessor initialized.");
    }


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "AutoGetterProcessor processing.");
        System.out.println("AutoGetterProcessor process。");

        // 遍历所有被 @AutoGetter 标注的字段
        for (Element element : roundEnv.getElementsAnnotatedWith(AutoGetter.class)) {
            if (element.getKind() == ElementKind.FIELD) { // 确保是字段
                String fieldName = element.getSimpleName().toString();
                String className = ((TypeElement) element.getEnclosingElement()).getSimpleName().toString();
                String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                String packageName = processingEnv.getElementUtils()
                        .getPackageOf(element)
                        .getQualifiedName()
                        .toString();

                System.out.println("AutoGetterProcessor className:" + className);

                try {
                    // 创建一个新类，生成 getter 方法
                    JavaFileObject builderFile = processingEnv.getFiler()
                            .createSourceFile(className + "AutoGetter");
                    try (Writer writer = builderFile.openWriter()) {
                        writer.write("package " + packageName + ";\n\n");
                        writer.write("public class " + className + "AutoGetter {\n");
                        writer.write("    private String " + fieldName + ";\n");
                        writer.write("    public String " + getterName + "() {\n");
                        writer.write("        return this." + fieldName + ";\n");
                        writer.write("    }\n");
                        writer.write("}\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
