package com.example.securitydemo.common.utils;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 21:16
 */
public class LombokFluentBuilderMethodsPlugin extends PluginAdapter {
    public LombokFluentBuilderMethodsPlugin() {
    }
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addLombok(topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addLombok(topLevelClass);
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addLombok(topLevelClass);
        return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
    }
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        Method fluentMethod = new Method();
        fluentMethod.setVisibility(JavaVisibility.PUBLIC);
        fluentMethod.setReturnType(topLevelClass.getType());
        fluentMethod.setName("with" + method.getName().substring(3));
        fluentMethod.getParameters().addAll(method.getParameters());
        this.context.getCommentGenerator().addGeneralMethodComment(fluentMethod, introspectedTable);
        StringBuilder sb = (new StringBuilder()).append("this.").append(method.getName()).append('(').append(introspectedColumn.getJavaProperty()).append(");");
        fluentMethod.addBodyLine(sb.toString());
        fluentMethod.addBodyLine("return this;");
        topLevelClass.addMethod(fluentMethod);
        return false;
    }
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return true;
    }

    private void addLombok(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
}