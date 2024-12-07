package com.github.zack.use.ast.processor;

import com.github.zack.use.ast.AutoGetter;

import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author zack
 * @since 2024/12/7
 */
@SupportedAnnotationTypes({"com.github.zack.use.ast.AutoGetter"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ASTProcessor extends AbstractProcessor {
    private Trees trees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(javax.annotation.processing.ProcessingEnvironment processingEnv) {
        System.out.println("ASTProcessor init.");
        super.init(processingEnv);
        this.trees = Trees.instance(processingEnv);
        Context context = ((com.sun.tools.javac.processing.JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("ASTProcessor process.");
        for (Element element : roundEnv.getElementsAnnotatedWith(AutoGetter.class)) {
            if (element.getKind().isField()) {
                JCTree tree = (JCTree) trees.getTree(element);
                if (tree instanceof JCTree.JCVariableDecl) {
                    JCTree.JCVariableDecl varDecl = (JCTree.JCVariableDecl) tree;

                    System.out.println("Found field: " + tree);
                    System.out.println("Found field type: " +  varDecl.vartype);

                    // 创建 Getter 方法
                    JCTree.JCMethodDecl getterMethod = treeMaker.MethodDef(
                            treeMaker.Modifiers(com.sun.tools.javac.code.Flags.PUBLIC), // 修正为 Flags.PUBLIC
                            names.fromString("get" + capitalize(varDecl.getName().toString())), // 方法名
                            varDecl.vartype, // 返回值类型，直接使用 varDecl.vartype
                            com.sun.tools.javac.util.List.nil(), // 泛型参数
                            com.sun.tools.javac.util.List.nil(), // 方法参数
                            com.sun.tools.javac.util.List.nil(), // 异常
                            treeMaker.Block(0, com.sun.tools.javac.util.List.of(
                                    treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")),
                                            varDecl.name)) // 返回语句
                            )),
                            null // 默认值
                    );

                    // 将方法插入到类中
                    JCTree.JCClassDecl classDecl = (JCTree.JCClassDecl) trees.getTree(element.getEnclosingElement());
                    classDecl.defs = classDecl.defs.append(getterMethod);
                }
            }
        }
        return true;
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
