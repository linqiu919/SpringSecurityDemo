package com.example.securitydemo.common.utils;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenxinbao
 * @company 杭州震墨科技有限公司
 * @version 1.0
 * @date 2022/3/28 17:06
 * 自定义generator生成配置类
 */
public class MysqlCommentGenerator implements CommentGenerator {
    private Properties properties = new Properties();
    private Properties systemProperties = System.getProperties();
    private boolean suppressDate = false;
    private boolean suppressAllComments = false;
    private String currentDateStr = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date());
    private String author;

    public MysqlCommentGenerator() {
        this.author = this.systemProperties.getProperty("user.name");
    }
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
        this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
        if (properties.getProperty("author") != null && !"".equalsIgnoreCase(properties.getProperty("author"))) {
            this.author = properties.getProperty("author");
        }

    }
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            if (introspectedColumn.getRemarks() != null && !"".equals(introspectedColumn.getRemarks())) {
                sb.append(introspectedColumn.getRemarks());
            } else {
                sb.append(introspectedColumn.getActualColumnName());
            }

            field.addJavaDocLine(sb.toString().replace("\n", " "));
            field.addJavaDocLine(" */");
        }
    }
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            field.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            field.addJavaDocLine(sb.toString().replace("\n", " "));
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    }
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    }
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {
    }
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            innerEnum.addJavaDocLine("/**");
            sb.append(" * ");
            sb.append(introspectedTable.getFullyQualifiedTable());
            innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
            innerEnum.addJavaDocLine(" */");
        }
    }
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments) {
            method.addJavaDocLine("/**");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString().replace("\n", " "));
            sb.setLength(0);
            sb.append(" * @return ");
            sb.append(introspectedColumn.getActualColumnName());
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString().replace("\n", " "));
            method.addJavaDocLine(" */");
        }
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (!this.suppressAllComments) {
            method.addJavaDocLine("/**");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString().replace("\n", " "));
            Parameter parm = (Parameter)method.getParameters().get(0);
            sb.setLength(0);
            sb.append(" * @param ");
            sb.append(parm.getName());
            sb.append(" ");
            sb.append(introspectedColumn.getRemarks());
            method.addJavaDocLine(sb.toString().replace("\n", " "));
            method.addJavaDocLine(" */");
        }
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (!this.suppressAllComments) {
            StringBuilder sb = new StringBuilder();
            method.addJavaDocLine("/**");
            sb.append(" * ");
            if (method.isConstructor()) {
                sb.append(" 构造查询条件");
            }

            String method_name = method.getName();
            if ("setOrderByClause".equals(method_name)) {
                sb.append(" 设置排序字段");
            } else if ("setDistinct".equals(method_name)) {
                sb.append(" 设置过滤重复数据");
            } else if ("getOredCriteria".equals(method_name)) {
                sb.append(" 获取当前的查询条件实例");
            } else if ("isDistinct".equals(method_name)) {
                sb.append(" 是否过滤重复数据");
            } else if ("getOrderByClause".equals(method_name)) {
                sb.append(" 获取排序字段");
            } else if ("createCriteria".equals(method_name)) {
                sb.append(" 创建一个查询条件");
            } else if ("createCriteriaInternal".equals(method_name)) {
                sb.append(" 内部构建查询条件对象");
            } else if ("clear".equals(method_name)) {
                sb.append(" 清除查询条件");
            } else if ("countByExample".equals(method_name)) {
                sb.append(" 根据指定的条件获取数据库记录数");
            } else if ("deleteByExample".equals(method_name)) {
                sb.append(" 根据指定的条件删除数据库符合条件的记录");
            } else if ("deleteByPrimaryKey".equals(method_name)) {
                sb.append(" 根据主键删除数据库的记录");
            } else if ("insert".equals(method_name)) {
                sb.append(" 插入数据库记录");
            } else if ("insertSelective".equals(method_name)) {
                sb.append(" 动态字段,写入数据库记录");
            } else if ("selectByExample".equals(method_name)) {
                sb.append(" 根据指定的条件查询符合条件的数据库记录");
            } else if ("selectByPrimaryKey".equals(method_name)) {
                sb.append(" 根据指定主键获取一条数据库记录");
            } else if ("updateByExampleSelective".equals(method_name)) {
                sb.append(" 动态根据指定的条件来更新符合条件的数据库记录");
            } else if ("updateByExample".equals(method_name)) {
                sb.append(" 根据指定的条件来更新符合条件的数据库记录");
            } else if ("updateByPrimaryKeySelective".equals(method_name)) {
                sb.append(" 动态字段,根据主键来更新符合条件的数据库记录");
            } else if ("updateByPrimaryKey".equals(method_name)) {
                sb.append(" 根据主键来更新符合条件的数据库记录");
            } else if ("setGroupBy".equals(method_name)) {
                sb.append(" 设置分组字段");
            } else if ("getGroupBy".equals(method_name)) {
                sb.append(" 获取分组字段");
            } else if ("or".equals(method_name)) {
                sb.append(" 新增一个查询条件");
            } else if ("setLimitStart".equals(method_name)) {
                sb.append(" 设置分页开始数字");
            } else if ("getLimitStart".equals(method_name)) {
                sb.append(" 获取分页开始数字");
            } else if ("setCount".equals(method_name)) {
                sb.append(" 设置分页获取数据个数");
            } else if ("getCount".equals(method_name)) {
                sb.append(" 获取分页获取数据个数");
            } else if ("insertBatch".equals(method_name)) {
                sb.append(" 批量写入数据库记录");
            } else if ("updateBatchByPrimaryKeySelective".equals(method_name)) {
                sb.append(" 批量根据主键更新记录");
            } else if ("updateBatchByPrimaryKey".equals(method_name)) {
                sb.append(" 批量根据主键更新记录");
            } else {
                sb.append(" 设置属性并返回对象");
            }

            method.addJavaDocLine(sb.toString());
            List<Parameter> parameterList = method.getParameters();
            if (!parameterList.isEmpty()) {
                method.addJavaDocLine(" *");
                if ("or".equals(method_name)) {
                    sb.append(" 增加或者的查询条件,用于构建或者查询");
                }
            } else if ("or".equals(method_name)) {
                sb.append(" 创建一个新的或者查询条件");
            }

            for(Iterator var7 = parameterList.iterator(); var7.hasNext(); method.addJavaDocLine(sb.toString())) {
                Parameter parameter = (Parameter)var7.next();
                sb.setLength(0);
                sb.append(" * @param ");
                String paramterName = parameter.getName();
                sb.append(paramterName);
                if ("orderByClause".equals(paramterName)) {
                    sb.append(" 排序字段");
                } else if ("distinct".equals(paramterName)) {
                    sb.append(" 是否过滤重复数据");
                } else if ("criteria".equals(paramterName)) {
                    sb.append(" 过滤条件实例");
                } else if ("example".equals(paramterName)) {
                    sb.append(" 查询条件");
                } else if ("id".equals(paramterName)) {
                    sb.append(" 主键");
                } else if ("list".equals(paramterName)) {
                    sb.append(" 数据集合");
                } else if ("record".equals(paramterName)) {
                    sb.append(" 一条数据记录");
                } else {
                    sb.append(" ").append(paramterName);
                }
            }

            FullyQualifiedJavaType returnType = method.getReturnType();
            if (EmptyUtils.isNotNull(returnType)) {
                String returnName = returnType.getShortName();
                if (!"void".equals(returnName)) {
                    sb.setLength(0);
                    sb.append(" * @return ");
                    sb.append(returnName);
                    if ("getOrderByClause".equals(method_name)) {
                        sb.append(" 排序字段");
                    } else if ("getOredCriteria".equals(method_name)) {
                        sb.append(" 当前的查询条件实例");
                    } else if ("countByExample".equals(method_name)) {
                        sb.append(" 满足指定条件的数据库记录数");
                    } else if ("deleteByExample".equals(method_name)) {
                        sb.append(" 指定条件删除数据库符合条件的记录个数");
                    } else if ("deleteByPrimaryKey".equals(method_name)) {
                        sb.append(" 根据主键删除数据库的记录数");
                    } else if ("insertBatch".equals(method_name)) {
                        sb.append(" 批量写入数据库记录成功个数");
                    } else if (!"insert".equals(method_name) && !"insertSelective".equals(method_name)) {
                        if ("selectByExample".equals(method_name)) {
                            sb.append(" 根据指定的条件查询符合条件的数据库记录");
                        } else if ("selectByPrimaryKey".equals(method_name)) {
                            sb.append(" 根据指定主键获取一条数据库记录");
                        } else if (!"updateByExampleSelective".equals(method_name) && !"updateBatchByPrimaryKeySelective".equals(method_name) && !"updateByExample".equals(method_name) && !"updateBatchByPrimaryKey".equals(method_name) && !"updateByPrimaryKey".equals(method_name) && !"updateByPrimaryKeySelective".equals(method_name)) {
                            if ("isDistinct".equals(method_name)) {
                                sb.append(" 是否过滤重复数据");
                            }
                        } else {
                            sb.append(" 更新符合条件的数据库记录成功个数");
                        }
                    } else {
                        sb.append(" 插入数据库记录成功个数");
                    }

                    method.addJavaDocLine(sb.toString());
                }
            }

            method.addJavaDocLine(" */");
        }
    }
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        compilationUnit.addFileCommentLine("/**");
        compilationUnit.addFileCommentLine("* " + compilationUnit.getType().getShortName() + ".java");
        compilationUnit.addFileCommentLine("*");
        compilationUnit.addFileCommentLine("* @author " + this.author);
        compilationUnit.addFileCommentLine("* @date " + this.currentDateStr);
        compilationUnit.addFileCommentLine("* @company 杭州震墨科技有限公司");
        compilationUnit.addFileCommentLine("*/");
    }

    public void addJavaFileComment(CompilationUnit compilationUnit, IntrospectedTable introspectedTable, Boolean flag) {
        this.addJavaFileComment(compilationUnit);
    }
    @Override
    public void addComment(XmlElement xmlElement) {
    }
    @Override
    public void addRootComment(XmlElement xmlElement) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
    }
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
    }
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
    }
    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
    }

    protected String getDateString() {
        String result = null;
        if (!this.suppressDate) {
            result = this.currentDateStr;
        }

        return result;
    }

    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append("@mbg.generated");
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }

        String s = this.getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }

        javaElement.addJavaDocLine(sb.toString());
    }
}
